package randomCode.randomProjects.battleship;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class BattleshipGame {
	private static Grid grid;
	
	private static int gridLength;
	private static int numberOfShips;
	private static ArrayList<Integer> shipLengths;
	
	private static int shipsDestroyed;
	

	public static void main(String[] args) {
		gridLength = 3;
		numberOfShips = 2;
		
		shipLengths = Lists.newArrayList(2,2,3,3,4);
			
		grid = new Grid(gridLength, numberOfShips, shipLengths);
		
		System.out.printf("Welcome to Battleship. There are %d ships in the water.\n", numberOfShips);
		Scanner kb = new Scanner(System.in);
		
		//For debugging
		grid.printGrid();
		
		int guessCount = 0;
		while (shipsDestroyed < numberOfShips) {
			System.out.printf("Please make a guess. Choose a X Coordinate from A to %s. Choose a Y Coordinate from 0 to %d: ", 
					(char) (gridLength + 64), gridLength-1);
			String guess = kb.nextLine();
			
			doesGuessHit(guess.toLowerCase());
			guessCount++;
		}
		
		System.out.printf("\nYou destroyed all %d ships. It took %d guesses\n", numberOfShips, guessCount);
		
		kb.close();
		//grid.printGrid();
	}


	private static void doesGuessHit(String guess) {
		int xpos = guess.charAt(0) - 'a';
		int ypos = Integer.parseInt(guess.substring(1));
		
		GridSquare targetedSquare = grid.getGridSquare(xpos, ypos);
		
		if(targetedSquare.hasShip()) {
			Ship targetedShip = targetedSquare.getShip();
			
			System.out.printf("\nHit! Ship %s has been damaged\n\n", targetedShip.getName());
			targetedShip.takeDamage(1);
			
			if (targetedShip.getHealth() == 0) {
				System.out.printf("Ship %s has been destroyed!\n\n", targetedShip.getName());
				shipsDestroyed++;		
			}
			
			targetedSquare.setShip(null);
		}
		else {
			System.out.println("\nMiss! You didn't hit anything. Try again\n");
		}
	}
}
