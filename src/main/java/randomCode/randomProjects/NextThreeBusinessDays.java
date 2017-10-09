package randomCode.randomProjects;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class NextThreeBusinessDays {
	
	
	//A component for a program that runs only on business days (Monday through Friday)
	//Needs to get the date for the day that is three business days in the future
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
		
		List<String> strDateList = Lists.newArrayList("10/09/2017", "10/10/2017", "10/11/2017", "10/12/2017", "10/13/2017");
		List<LocalDate> dateList = new ArrayList<>();
		
		for (String str : strDateList) {
			LocalDate d = LocalDate.parse(str, formatter).plusDays(3);
			DayOfWeek day = d.getDayOfWeek();
			
			if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || day == DayOfWeek.MONDAY) {
				d = d.plusDays(2);
			}
			dateList.add(d);
		}
		
		for (LocalDate d: dateList) {
			System.out.println(d.format(formatter));
		}
 
	}

}
