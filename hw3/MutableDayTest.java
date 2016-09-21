import org.junit.*;
import static org.junit.Assert.*;

/* More test cases may be added at any time */

public class MutableDayTest 
{
	@Test public void testMutatingPlusDays()
	{
		MutableDay d = new MutableDay(2016, 9, 16);
		d.plusDays(350);
		assertEquals("2017-09-01", d.toString());
		d.plusDays(-361);
		assertEquals("2016-09-05", d.toString());
	}
	
	@Test public void testDaysFromNotMutating()
	{
		MutableDay d = new MutableDay(2016, 9, 16);
		MutableDay d2 = new MutableDay(2017, 9, 1);
		int n = d2.daysFrom(d);
		assertEquals(350, n);
		assertEquals("2016-09-16", d.toString());
		assertEquals("2017-09-01", d2.toString());
	}
	
	@Test public void testAccessors()
	{
		MutableDay d = new MutableDay(2016, 9, 16);
		d.plusDays(350);
		assertEquals(2017, d.getYear());
		assertEquals(9, d.getMonthValue());
		assertEquals(1, d.getDayOfMonth());		
	}
	
	@Test public void testFebruary()
	{
		// http://horstmann.com/oodp3/slides/ch03/index.html#(18)
		MutableDay deadline = new MutableDay(2001, 1, 31);
		deadline.setMonthValue(2); 
		deadline.setDayOfMonth(28);
		assertEquals("2001-02-28", deadline.toString());
	}
	
	@Test(expected=IllegalStateException.class) public void testFebruaryException()
	{
		// http://horstmann.com/oodp3/slides/ch03/index.html#(18)
		MutableDay deadline = new MutableDay(2001, 1, 31);
		deadline.setMonthValue(2);  
		int day = deadline.getDayOfMonth();
	}

	@Test public void testMarch()
	{
		MutableDay deadline = new MutableDay(2001, 2, 28);
		deadline.setDayOfMonth(31); 
		deadline.setMonthValue(3);
		assertEquals("2001-03-31", deadline.toString());
	}
	
	@Test(expected=IllegalStateException.class) public void testMarchException()
	{
		// http://horstmann.com/oodp3/slides/ch03/index.html#(18)
		MutableDay deadline = new MutableDay(2001, 2, 28);
		deadline.setDayOfMonth(31); 
		int month = deadline.getMonthValue();
	}	
}
