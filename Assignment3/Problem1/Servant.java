import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Servant implements Runnable
{
	
	private PresentChain chain;
	private int id;
	private int cnt = 0;
	private Stack<Integer> bag;
	private int myGrabbed =0;
	private int myThanks = 0;
	private int lastSuccess = 0;
	private Integer totalPres;
	private Integer totalThanks;
	public Servant( PresentChain shared, int id, Stack<Integer> bag,int totalThanks,int totalPres)
	{
		this.chain = shared;
		this.id = id;
		this.bag = bag;
		this.totalThanks = totalThanks;
		this.totalPres = totalPres;
	} 
	
	@Override
	public void run()
	{
		try
		{
			while(!bag.empty()) {
				if(cnt%2 == 0) {
					addToChain(bag, chain);
					cnt++;
				}
				else {
					writeThankYou(chain, lastSuccess);
					cnt++;
				}
			}
			while(!chain.isEmpty()) {
				writeThankYou(chain, lastSuccess);
			}
			System.out.println("Servant " + id + ": Total grabbed: " + myGrabbed + " Total thanks: " + myThanks);
			totalThanks += myThanks;
			totalPres += myGrabbed;
			
			
		}
		catch ( Exception exception ) 
		{
		   exception.printStackTrace();
		} // end catch
	} // end method run
	
	public void addToChain(Stack<Integer> bag, PresentChain chain) {
			Present grabbed = new Present(bag.pop());
			chain.add(grabbed);
			System.out.println("Servant " + id + " grabbed a present!");
			myGrabbed++;
	}
	
	public boolean writeThankYou(PresentChain chain, int lastSuccess) {
		boolean flag = false;
		for(int i=lastSuccess; i<=500000; i++) {
			flag = chain.remove(i);
			if(flag) {
				System.out.println("Servant " + id + " wrote a thank you card!");
				myThanks++;
				lastSuccess = i;
				return flag;
			}
		
		}
		return flag;
	}
}