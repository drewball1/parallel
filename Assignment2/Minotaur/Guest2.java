import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Guest2 implements Runnable{
	//private ReentrantLock accessLock;
	//private Condition enterRoom;
	private boolean iveSeen = false;
	private Room room;
	private int id;
	private final int numGuests;
	
	public Guest2(Room room, int i, ReentrantLock accessLock, Condition enterMaze) {
		this.room = room;
		id = i;
		//this.accessLock = accessLock;
		//this.enterRoom = enterMaze;
		this.numGuests = room.getNumGuests();
	}
	
	public void goToRoom() {
		try {
			while(room.isOccupied()) {
				//wait for room to open
				Thread.sleep(1);
			}
			//Enter the room
			room.occupy();
			//Only print this out while were waiting for everyone to see it so it doesn't overload the console
			if(room.getCounter() < numGuests) {
				System.out.println("Guest " + id + " is in the room!");
			}
			if(!iveSeen) {
				//if you havent seen it yet, increment the counter for seeing the room and change your status
				room.addCounter();
				System.out.println("Counter: " + room.getCounter());
				iveSeen = true;
			}
			//add to total room entries
			room.addTotal();
			//leave room
			room.vacant();	
			//take time to exit the room. It would be silly if you walked to the threshold and then went immediately back in.
			Thread.sleep(10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void run() {
		while(!room.flagStatus()) {
				//go into the room
				goToRoom();

		}
	}
}
