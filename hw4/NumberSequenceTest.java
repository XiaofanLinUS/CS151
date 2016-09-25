import org.junit.Test;
import static org.junit.Assert.*;

public class NumberSequenceTest
{
   @Test public void squareSequenceTest()
   {
      NumberSequence sequence = new SquareSequence();
      assertEquals(0, sequence.next());
      assertEquals(1, sequence.next());
      assertEquals(4, sequence.next());
      assertEquals(9, sequence.next());
      assertEquals(16, sequence.next());
      assertEquals(25, sequence.next());
      assertEquals(36, sequence.next());
      assertEquals(true, sequence.hasNext());
      for (int i = 0; i < 1000; i++) sequence.next();
      assertEquals(1007 * 1007, sequence.next());
   }
   
   @Test public void ofTest()
   {
      NumberSequence sequence = NumberSequence.of(1, 7, 2, 9);
      assertEquals(1, sequence.next());
      assertEquals(7, sequence.next());
      assertEquals(2, sequence.next());
      assertEquals(9, sequence.next());
      assertEquals(false, sequence.hasNext());      
   }
   
   @Test public void averageTest()
   {
      NumberSequence sequence = NumberSequence.of(1, 7, 2, 9);
      assertEquals((1 + 7 + 2 + 9) / 4.0, sequence.average(), 1E-12);
      sequence = new SquareSequence();
      assertEquals((0 + 1 + 4 + 9 + 16) / 5.0, sequence.average(5), 1E-12);
   }
   
   @Test public void toArrayTest()
   {
      NumberSequence sequence = NumberSequence.of(1, 7, 2, 9);
      assertArrayEquals(new long[] { 1,  7, 2, 9 }, sequence.toArray(4));
      sequence = NumberSequence.of(1, 7, 2, 9);
      assertArrayEquals(new long[] { 1,  7 }, sequence.toArray(2));
      sequence = NumberSequence.of(1, 7, 2, 9);
      assertArrayEquals(new long[] { 1,  7, 2, 9 }, sequence.toArray(6));      
   }
   
   @Test public void filterTest()
   {
      NumberSequence oddSquares = new SquareSequence().filter(n -> n % 2 != 0);
      assertEquals(1, oddSquares.next());
      assertEquals(9, oddSquares.next());
      assertEquals(25, oddSquares.next());
      assertEquals(49, oddSquares.next());      
   }
   
   @Test public void iterateTest()
   {
      NumberSequence sequence = NumberSequence.iterate(1, n -> 2 * n);
      assertEquals(1, sequence.next());
      assertEquals(2, sequence.next());
      assertEquals(4, sequence.next());
      assertEquals(8, sequence.next());            
   }
   
   @Test public void randomTest()
   {
      NumberSequence sequence = NumberSequence.random(42);
      assertEquals(42, sequence.next());
      assertEquals(1250496027, sequence.next());
      assertEquals(1116302264, sequence.next());
      assertEquals(1000676753, sequence.next());                  
   }
}
