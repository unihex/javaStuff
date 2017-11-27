package randomCode.randomProjects.battleship;

import java.util.ArrayList;

import com.google.common.collect.Lists;

public class Grid {
	private int gridLength;
	
	private GridSquare[][] grid;
	
	private ArrayList<String> shipNames;
	
	private char[] directions;
	
	public Grid(int gridLength, int numberOfShips, ArrayList<Integer> shipLengths) {
		this.gridLength = gridLength;
		this.grid = new GridSquare[gridLength][gridLength];
		
		shipNames = Lists.newArrayList("Aegis", "Buster", "Corinth", "David", "Excelsior");
		directions = new char[]{'l', 'r', 'u', 'd'};
		
		makeSquares();
		
		ArrayList<GridSquare> validSquares = new ArrayList<>();
		
		for (int i = 0; i < numberOfShips; i++) {
			validSquares.clear();
			makeShip(shipLengths.get(i), validSquares);
		}
	}

	//Makes the ships
	//The main issue here is making sure ships don't overlap
	private void makeShip(int shipLength, ArrayList<GridSquare> validSquares) {
		int randomIndex = getRandomIndex(shipNames.size());
		String shipName = shipNames.get(randomIndex);
		
		int originX;
		int originY;
		
		//Gets a random square and checks to see if it has a ship
		//If it does, try again
		//If it doesn't, check to see if a ship can grow (in a random direction) from there
		//If a ship can't grow fully (in that random direction), choose a new starting square and try again
		//If it can, build!
		do {
				
			do {
				
				validSquares.clear();
				
				originX = getRandomIndex(gridLength);
				originY = getRandomIndex(gridLength);
					
			} while (grid[originX][originY].hasShip() == true);
				
			validSquares.add(grid[originX][originY]);
				
		} while (growShip(originX, originY, shipLength, validSquares) == false);
			
			
		Ship currentShip = new Ship(shipName, shipLength);
			
		//shipMap.put(currentShip.getName(), currentShip);
			
		for (int j = 0; j < validSquares.size(); j++) {
				validSquares.get(j).setShip(currentShip);
		}
			
		//Ships have unique names
		shipNames.remove(randomIndex);
			
	}
		
	
	//From a given starting square, attempts to grow a ship in a random direction
	private boolean growShip(int originX, int originY, int shipLength, ArrayList<GridSquare> validSquares) {
		int randomIndex = getRandomIndex(directions.length);
		char direction = directions[randomIndex];
		
		if (direction == 'l') {
			return growLeftOrRight(originX, originY, shipLength, validSquares, 1);
			
		} else if (direction == 'r') {
			return growLeftOrRight(originX, originY, shipLength, validSquares, -1);
			
		} else if (direction == 'u') {
			return growUpOrDown(originX, originY, shipLength, validSquares, -1);
			
		} else {
			return growUpOrDown(originX, originY, shipLength, validSquares, 1);
		}
		
	}

	private boolean growLeftOrRight(int originX, int originY, int shipLength, ArrayList<GridSquare> validSquares, int multiplier) {
		for (int i = 1; i < shipLength; i++) {
			try {
				if (grid[originX + (i * multiplier)][originY].hasShip() == true) {
					return false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			validSquares.add(grid[originX + (i * multiplier)][originY]);
		}
		
		return true;
	}
	
	private boolean growUpOrDown(int originX, int originY, int shipLength, ArrayList<GridSquare> validSquares, int multiplier) {
		for (int i = 1; i < shipLength; i++) {
			try {
				if (grid[originX][originY + (i * multiplier)].hasShip() == true) {
					return false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			validSquares.add(grid[originX][originY + (i * multiplier)]);
		}
		
		return true;
	}

	private int getRandomIndex(int collectionSize) {
		return (int) (Math.random() * collectionSize);
	}

	private void makeSquares() {
		for(int x = 0; x < gridLength; x++) {
			for(int y = 0; y < gridLength; y++) {
				grid[x][y] = new GridSquare(x, y);
				
			}
		}	
	}
	
	//Prints the grid
	public void printGrid() {
		for(int i = 0; i < gridLength; i++) {
			for(int j = 0; j < gridLength; j++){
				System.out.println(grid[i][j].toString());
			}
			System.out.println();
		}
	}

	public GridSquare getGridSquare(int xpos, int ypos) {
		return grid[xpos][ypos];
	}
}
