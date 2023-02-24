import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Vase {

	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(100);
		Room room = new Room();
		ReentrantLock accessLock = new ReentrantLock();
		Condition enterRoom = accessLock.newCondition();
		
		try {
			for (int i = 1; i <= 100; i++) {
				application.execute(new Guest2(room, i, accessLock,enterRoom));

			}
			

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		application.shutdown();
		try {
			application.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println("All Done. Everyone has seen the room? " + room.flagStatus());
			System.out.println("Total room entries: " + room.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

}
