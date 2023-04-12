import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class BirthdayPresents {
	public static void main(String[] args) {
		
		PresentChain presentChain = new PresentChain();
		ExecutorService application = Executors.newFixedThreadPool(4);
		Integer totalThanks = 0;
		Integer totalPres = 0;
		try {
			
			Stack<Integer> bag = new Stack<Integer>();
			
			for(int i=1; i<=500000;i++) {
				bag.push(i);
			}
			Collections.shuffle(bag);
			
			for (int i = 1; i <= 4; i++) {
				application.execute(new Servant(presentChain, i, bag, totalThanks, totalPres));

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		try {
			application.awaitTermination(15, TimeUnit.SECONDS);
			application.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
