import org.junit.*;
import static org.junit.Assert.*;

public class LengthTest
{   
   @Test public void sumTest()
   {
      Length a = new Length(3, 11);
      Length b = new Length(4, 10);
      Length c = a.sum(b);
      assertEquals(8, c.getFeet());
      assertEquals(9, c.getInches());      
   }

   @Test public void differenceTest1()
   {
      Length a = new Length(4, 11);
      Length b = new Length(3, 10);
      Length c = a.difference(b);
      assertEquals(1, c.getFeet());
      assertEquals(1, c.getInches());      
   }

   @Test public void differenceTest2()
   {
      Length a = new Length(4, 10);
      Length b = new Length(3, 11);
      Length c = a.difference(b);
      assertEquals(0, c.getFeet());
      assertEquals(11, c.getInches());      
   }
   
   @Test public void differenceTest3()
   {
      Length a = new Length(4, 11);
      Length b = new Length(4, 10);
      Length c = a.difference(b);
      assertEquals(0, c.getFeet());
      assertEquals(1, c.getInches());      
   }
   
   @Test public void differenceTest4()
   {
      Length a = new Length(4, 10);
      Length b = new Length(4, 11);
      Length c = a.difference(b);
      assertEquals(0, c.getFeet());
      assertEquals(1, c.getInches());      
   }
   
   @Test public void differenceTest5()
   {
      Length a = new Length(3, 11);
      Length b = new Length(4, 10);
      Length c = a.difference(b);
      assertEquals(0, c.getFeet());
      assertEquals(11, c.getInches());      
   }
   
   @Test public void differenceTest6()
   {
      Length a = new Length(3, 10);
      Length b = new Length(4, 11);
      Length c = a.difference(b);
      assertEquals(1, c.getFeet());
      assertEquals(1, c.getInches());      
   }
}
