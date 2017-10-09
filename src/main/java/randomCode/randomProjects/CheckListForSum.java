package randomCode.randomProjects;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class CheckListForSum {
	
	public static void main(String[] args) {
		List<Integer> intList = Lists.newArrayList(60, 17, 48, 5, 74, 9, 3, 79, 35, 27);
		System.out.println(checkListForSum(intList, 26));
	}
	
	public static boolean checkListForSum(List<Integer> intList, int target) {
		boolean result = false;
		
		System.out.println(intList);
		Collections.sort(intList);
		System.out.println(intList);
		
		int leftBound = 0;
		int rightBound = intList.size() - 1;
		
		while (leftBound < rightBound) {
			int leftNumber = intList.get(leftBound);
			int rightNumber = intList.get(rightBound);
			int sum = leftNumber + rightNumber;
			
			String line = String.format("The left number is: %d, and the right number is: %d. Their sum is: %d", leftNumber, rightNumber, sum);
			System.out.println(line);
			
			if (sum < target) {
				leftBound++;
			} else if (sum > target) {
				rightBound--;
			} else {
				result = true;
				break;
			}		
		}
		
		return result;
	}
}
