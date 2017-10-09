package randomCode.randomProjects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;



public class DuplicateElementsInList {

	public static void main(String[] args) {
		List<String> strList = Lists.newArrayList("A", "B", "C", "D");
		
		System.out.println(dupElements(strList, 4));
	
	}
	
	public static List<String> dupElements(List<String> strList, int numberOfDuplicates) {
		return strList.stream()
				.flatMap(element -> Stream.generate( () -> element).limit(numberOfDuplicates))
				.collect(Collectors.toList());
	}
}

