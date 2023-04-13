package Problem2;
import java.security.SecureRandom;
import java.util.Random;

public class Sensor implements Runnable {
	
	private int id;
	private int cnt = 0;
	private int max = 100;
	private int min = -70;
	private DataTable table;
//	private SecureRandom rand = new SecureRandom();
	
	Sensor(DataTable table, int id){
		this.table = table;
		this.id = id;
	}

	
	private int getTemp(){
//		int temp = (int)(rand.nextInt(171) - min);
//		System.out.println(temp);
		int temp = (int) Math.floor(Math.random() *(max - min + 1) + min);
		//System.out.println("Sensor " + id + "read a temperature of " + temp + "degrees at minute " + cnt + ".");
		return temp;
	}
	
	private void recordTemp(int temp,int cnt,int id,DataTable table) {
		table.data[cnt][id-1] = temp;
		return;
	}
	
	@Override
	public void run()
	{
		try
		{
			while(cnt < 60) {
				int temp = getTemp();
				recordTemp(temp, cnt, id, table);
				cnt++;	
			}
		}
		catch ( Exception exception ) 
		{
		   exception.printStackTrace();
		} // end catch
	} // end method run
}
