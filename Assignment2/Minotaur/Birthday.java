import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Birthday {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService application = Executors.newFixedThreadPool(100);
		Cupcake sharedLocation = new Cupcake();
		ReentrantLock accessLock = new ReentrantLock();
		Condition enterMaze = accessLock.newCondition();
		try {
			for (int i = 1; i <= 100; i++) {
				application.execute(new Guest(sharedLocation, i, accessLock,enterMaze));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		application.shutdown();
		try {
			application.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println("All Done. Flag set to " + sharedLocation.flagStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}