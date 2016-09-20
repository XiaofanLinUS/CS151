import static org.junit.Assert.assertEquals;

public class Tester {
	public static void main(String[] args) {
		   DayWithTime d = new DayWithTime(2016, 9, 30, 19, 30).plusMinutes(721);
		   //assertEquals(2016, d.getYear());
		   //assertEquals(10, d.getMonthValue());
		   //assertEquals(1, d.getDayOfMonth());
		   //assertEquals(7, d.getHours());
		   //assertEquals(31, d.getMinutes());
		   System.out.println(d.getYear());
		   System.out.println(d.getMonthValue());
		   System.out.println(d.getDayOfMonth());
		   System.out.println(d.getHours());
		   System.out.println(d.getMinutes());
	}
}
