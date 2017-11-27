package randomCode.randomProjects.battleship;

public class GridSquare {
	
	private int x;
	private int y;
	
	private Ship ship;
	
	public GridSquare(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.ship = null;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public boolean hasShip() {
		boolean result;
		
		if (ship == null) {
			result = false;
			
		} else {
			result = true;		
		}
		
		return result;
	}
	
	public String getShipName() {
		String result;
		
		if (this.hasShip()) {
			result = ship.getName();
		} else {
			result = "No Ship!";
		}
		
		return result;
	}
	
	public String toString() {
		return String.format("Location: (%s, %d) \tHas Ship: %s \tShip Name: %s ", (char) (this.x + 65), this.y, hasShip(), getShipName());	
	}

}
