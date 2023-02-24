
public class Room {
	private Boolean isOccupied = false;
	private int counter = 0;
	private int total = 0;
	private Boolean flag = false;
	private final int numGuests = 100;
	
	
	public void allSeen() {
		this.flag = true;
	}
	
	public boolean flagStatus() {
		return flag;
	}
	
	public int getCounter(){
		return counter;
	}
	
	public void addCounter(){
		counter++;
		if(counter == numGuests){
			allSeen();
		}
	}
	
	public Boolean isOccupied() {
		return isOccupied;
	}
	
	public void setOccupied(Boolean value) {
		isOccupied = value;
	}
	
	public void occupy() {
		setOccupied(true);
	}
	
	public void vacant() {
		setOccupied(false);
	}
	
	public int getNumGuests() {
		return numGuests;
	}
	public int getTotal() {
		return total;
	}
	
	public void addTotal() {
		total++;
	}
}
