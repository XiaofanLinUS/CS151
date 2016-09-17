public class DayTester
{
   public static void main(String[] args)
   {
      Day today = new Day(2001, 2, 3); // February 3, 2001
      Day later = today.plusDays(999);
      System.out.println(later.getYear() 
            + "-" + later.getMonthValue() 
            + "-" + later.getDayOfMonth()); 
      System.out.println(later.daysFrom(today)); // Prints 999
   }
}
