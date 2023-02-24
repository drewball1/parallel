
public class Cupcake {
	private boolean flag = false;
	private boolean cupcake = true;
	
	public void allSeen() {
		this.flag = true;
	}
	
	public boolean flagStatus() {
		return flag;
	}
	
	public void eatCupcake(){
		setCupcake(false);
	}
	public void placeCupcake() {
		setCupcake(true);
	}

	public boolean checkPlate() {
		return cupcake;
	}

	public void setCupcake(boolean cupcake) {
		this.cupcake = cupcake;
	}
}
