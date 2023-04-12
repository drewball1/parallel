import java.util.concurrent.atomic.*;

//import PresentChain.Present;

public class Node {
	Present item;
	int key;
	AtomicMarkableReference<Node> next;// = new AtomicMarkableReference<Node>(new Node(null),false);
	Node(Present item){
		this.item = item;
	}
	public void setNext(AtomicMarkableReference<Node> next) {
		this.next = next;
	}
	
	
}
