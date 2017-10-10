package randomCode.randomProjects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

public class NumberOfIslands {
	static List<List<Integer>> matrix;
	
	public static void main(String[] args) {
		
		List<Integer> rowOne = 		Lists.newArrayList(0, 0, 0, 0, 1);
		List<Integer> rowTwo = 		Lists.newArrayList(0, 0, 1, 1, 0);
		List<Integer> rowThree = 	Lists.newArrayList(0, 1, 1, 1, 0, 1);
		List<Integer> rowFour = 	Lists.newArrayList(0, 0, 0, 0, 0);
		List<Integer> rowFive = 	Lists.newArrayList(0, 0, 1, 1, 1);
		
		matrix = Lists.newArrayList(rowOne, rowTwo, rowThree, rowFour, rowFive);
		printMatrix();
		
		System.out.println("How many islands are there?");
		System.out.println(String.format("There are %d Islands!", getNumberOfIslands()));
	}

	private static int getNumberOfIslands() {
		int result = 0;
		
		Set<Pair<Integer, Integer>> vistorsLog = new HashSet<>();
		
		for (int i = 0; i < matrix.size(); i++) {
			List<Integer> row = matrix.get(i);
			
			for (int j = 0; j < row.size(); j++) {
				int cell = row.get(j);
				
				if (cell == 1) {
					result += touchLand(new ImmutablePair<Integer, Integer>(j, i), vistorsLog);
				}
			}
		}
		
		return result;
	}
	
	//Piece of land are represent by 1
	
	//If the piece of land is visited already, then it is not a new island
	//If the piece of land is not visited, but any of its neighbors are, then it is not a new island
	//If the piece of land is not visited, and all of its neigbors are also not visited, then it is a new island
	private static int touchLand(ImmutablePair<Integer, Integer> currentLand, Set<Pair<Integer, Integer>> vistorsLog) {
		boolean isNewIsland;
	
		if (vistorsLog.contains(currentLand) | checkIfAnyNeighborIsVisited(currentLand, vistorsLog)) {
			isNewIsland = false;
		} else {
			isNewIsland = true;
		}
		
		int result = isNewIsland ? 1 : 0;
		return result;
	}

	private static boolean checkIfAnyNeighborIsVisited(ImmutablePair<Integer, Integer> currentLand, Set<Pair<Integer, Integer>> vistorsLog) {
		boolean result = false;
		
		List<Pair<Integer, Integer>> neighbors = getNeighbors(currentLand);
		
		for (Pair<Integer, Integer> neighbor : neighbors) {
			if (vistorsLog.contains(neighbor)) {
				result = true;
			}
			
			visitNeighbor(neighbor, vistorsLog);
		}
		
		vistorsLog.add(currentLand);
		
		return result;
	}

	private static void visitNeighbor(Pair<Integer, Integer> neighbor, Set<Pair<Integer, Integer>> vistorsLog) {
		try {
			if (matrix.get(neighbor.getRight()).get(neighbor.getLeft()) == 1) {
				vistorsLog.add(neighbor);
			}
		} catch (IndexOutOfBoundsException e) {
				
			
		}
		
	}

	private static List<Pair<Integer, Integer>> getNeighbors(ImmutablePair<Integer, Integer> currentLand) {
		Pair<Integer,Integer> north = new ImmutablePair<>(currentLand.getLeft(), currentLand.getRight() + 1);
		Pair<Integer,Integer> east = new ImmutablePair<>(currentLand.getLeft() + 1, currentLand.getRight());
		Pair<Integer,Integer> west = new ImmutablePair<>(currentLand.getLeft() - 1, currentLand.getRight());
		Pair<Integer,Integer> south = new ImmutablePair<>(currentLand.getLeft(), currentLand.getRight() - 1);
		
		
		return Lists.newArrayList(north, east, west, south);
	}

	private static void printMatrix() {
		for (List<Integer> intList : matrix) {
			System.out.println(intList);
		}
		
	}

}
