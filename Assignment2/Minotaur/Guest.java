
//import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Guest implements Runnable{
	private ReentrantLock accessLock;
	private Condition enterMaze;
	private boolean iveEaten = false;
	final int numGuests = 100;
	private int platesSeen = 0;
	private static Cupcake shared;
	private int id;
	
	public Guest(Cupcake sharedloc, int i, ReentrantLock accessLock, Condition enterMaze) {
		shared = sharedloc;
		id = i;
		this.accessLock = accessLock;
		this.enterMaze = enterMaze;
	}
	
	public void dessertCheck(){
		accessLock.lock();

		try {
			while(!accessLock.isHeldByCurrentThread()) {
				Thread.sleep(10);
			}
			if(!iveEaten && shared.checkPlate()) {
				//eat cupcake
				this.iveEaten = true;
				shared.eatCupcake();	
				System.out.println("Thread " + id + ": Yum ");
			}
			if(id == 1) {
				if(!shared.checkPlate()) {
					//seen an empty plate
					platesSeen++;
					System.out.println("Thread " + id + ": Ive seen " + platesSeen + " plates!");
					//put a cupcake
					shared.placeCupcake();
				}
				if(platesSeen == numGuests) {
					
					shared.allSeen();
					System.out.println("Thread " + id + ": That's a wrap! Everyone has eaten, Mr. Birthday Boy!");
				}				
			
			}
			//Include this if you need to check whether the other threads are still going, (they are)
//			if(iveEaten && id != 1) {
//				System.out.println("Thread: " + id + " I've already eaten.");
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//alert a waiting thread and unlock
			enterMaze.signal();
			
			accessLock.unlock();
		}
	}
	
	@Override
	public void run() {
		while(!shared.flagStatus()) {
			try {
				while(accessLock.isLocked()) {
					Thread.sleep(10);	
				}
				//go into the maze
				dessertCheck();
				Thread.sleep(10);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
