import java.util.concurrent.atomic.*;
public class PresentChain {
	
	private Node head;
	private Node tail;
	private Node tail2;
	
	PresentChain(){
		this.head = new Node(new Present(-1));
		this.tail = new Node(new Present(999999));
		this.tail2 = new Node(new Present(999998));
		this.head.next = new AtomicMarkableReference<Node>(tail,false);
		this.tail.next = new AtomicMarkableReference<Node>(tail2,false);
		this.tail2.next = null;
	}
	
//	public T get(boolean[] marked);
//	public boolean compareAndSet(T expectedReference, T newReference, boolean expectedMark,boolean newMark);
//	public attemptMark(t expectedReference, boolean newMark);
	
	public boolean isEmpty() {
		if(head.next.getReference() == tail) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean add(Present item) {
		int key = item.tagNum;
		while(true) {
			Window window = find(head,key);
			Node pred = window.pred, curr = window.curr;
			if(curr.key == key) {
				return false;
			} else {
				Node node = new Node(item);
				node.next = new AtomicMarkableReference<Node>(curr,false);
				if(pred.next.compareAndSet(curr,node,false,false)) {
					return true;
				}
			}
		}
	}
	
	public boolean remove(int item) {
		int key = item;
		boolean snip;
		while (true) {
			Window window = find(head, key);
			Node pred = window.pred, curr = window.curr;
			if(curr.key != key) {
				return false;
			} else {
				Node succ = curr.next.getReference();
				snip = curr.next.compareAndSet(succ, succ, false, true);
				if(!snip) continue;
				pred.next.compareAndSet(curr, succ, false, false);
				return true;
			}
		}
	}
	
	public boolean contains(Present item) {
		boolean[] marked = {false};
		int key = item.tagNum;
		Node curr = head;
		while(curr.key < key) {
			curr = curr.next.getReference();
			Node succ = curr.next.get(marked);
		}
		return (curr.key == key && !marked[0]);
	}
	
	public class Window {
		public Node pred, curr;
		Window(Node myPred, Node myCurr){
			pred = myPred;
			curr = myCurr;
		}
	}

	public Window find(Node head, int key) {
		Node pred = null, curr = null, succ = null;
		boolean[] marked = {false};
		boolean snip;
		//int retcnt = 0;
		retry: while (true) {
			//retcnt++;
			pred = head;
			curr = pred.next.getReference();
			if(curr == tail) {
				return new Window(pred,curr);
			}
			//while(true){
			while(succ == null || curr.next.isMarked() == true) {
				//retcnt++;
				succ = curr.next.get(marked);
				while (marked[0]) {
					snip = pred.next.compareAndSet(curr,succ, false, false);
					if (!snip) continue retry;
					curr = succ;
					succ = curr.next.get(marked);
				}
				if(curr.key >= key) {
					return new Window(pred,curr);
				}
			}
		}
	}
}
