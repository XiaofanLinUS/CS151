import static org.junit.Assert.assertEquals;

public class Tester {
	public static void main(String[] args) {
		DayWithTime wow = new DayWithTime(1997, 05, 31, 0, 0);
		DayWithTime five = wow.plusDays(5);
		DayWithTime haha = wow.plusMinutes(3640);
		System.out.println(five);
		
		   DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
		   DayWithTime d2 = new DayWithTime(2017, 9, 1, 23, 30);
		   System.out.println(d.daysFrom(d2));
		   d2 = new DayWithTime(2017, 9, 2, 07, 29);
		   d2 = d2.plusMinutes(1);

	}
}
