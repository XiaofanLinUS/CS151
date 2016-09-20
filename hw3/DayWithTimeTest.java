import org.junit.*;
import static org.junit.Assert.*;

/* More test cases may be added at any time */

public class DayWithTimeTest
{
   @Test public void testAddWholeDays()
   {
      DayWithTime d = new DayWithTime(2016, 9, 1, 12, 30);
      DayWithTime d2 = d.plusDays(30);
      assertEquals("2016-10-01 12:30", d2.toString());
   }
   
   @Test public void testAddMinutes()
   {
      DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
      DayWithTime d2 = d.plusMinutes(720);
      assertEquals("2016-09-02 07:30", d2.toString());
      
      d2 = d.plusMinutes(1440 * 365 + 720);
      assertEquals("2017-09-02 07:30", d2.toString());	      
   }
   
   @Test public void testSubtractMinutes()
   {
      DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
      DayWithTime d2 = d.plusMinutes(-720);
      assertEquals("2016-09-01 07:30", d2.toString());
      
      d2 = d.plusMinutes(-1440 * 365 - 720);
      assertEquals("2015-09-02 07:30", d2.toString());	      	   
   }
   
   @Test public void testMinutesFrom()
   {
	   DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
	   DayWithTime d2 = new DayWithTime(2017, 9, 2, 7, 30);
	   assertEquals(365 * 1440 + 720, d2.minutesFrom(d));
	   assertEquals(-365 * 1440 - 720, d.minutesFrom(d2));
   }
   
   @Test public void testDaysFrom()
   {
	   DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
	   DayWithTime d2 = new DayWithTime(2017, 9, 1, 23, 30);
	   assertEquals(365, d2.daysFrom(d));
	   d2 = new DayWithTime(2017, 9, 2, 07, 29);
	   assertEquals(365, d2.daysFrom(d));
	   d2 = d2.plusMinutes(1);
	   assertEquals(366, d2.daysFrom(d));
   }   
   
   @Test public void testNegativeDaysFrom()
   {
	   DayWithTime d = new DayWithTime(2016, 9, 1, 19, 30);
	   DayWithTime d2 = new DayWithTime(2017, 9, 1, 23, 30);
	   assertEquals(-365, d.daysFrom(d2));
	   d2 = new DayWithTime(2017, 9, 2, 07, 29);
	   assertEquals(-365, d.daysFrom(d2));
	   d2 = d2.plusMinutes(1);
	   assertEquals(-366, d.daysFrom(d2));
   }
   
   @Test public void testGetters()
   {
	   DayWithTime d = new DayWithTime(2016, 9, 30, 19, 30).plusMinutes(721);
	   assertEquals(2016, d.getYear());
	   assertEquals(10, d.getMonthValue());
	   assertEquals(1, d.getDayOfMonth());
	   assertEquals(7, d.getHours());
	   assertEquals(31, d.getMinutes());
   }   
}
