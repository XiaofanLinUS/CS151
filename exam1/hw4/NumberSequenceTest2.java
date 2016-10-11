import org.junit.Test;
import static org.junit.Assert.*;

public class NumberSequenceTest2
{
   @Test public void averageTest2()
   {
      NumberSequence sequence = NumberSequence.of(1, 4, 9, 16);
      assertEquals((1 + 4 + 9 + 16) / 4.0, sequence.average(5), 1E-12);
   }
   
   @Test public void filterTest2()
   {
      NumberSequence oddSquares2 = NumberSequence.of(0, 1, 4, 9).filter(n -> n % 2 != 0);
      assertEquals(1, oddSquares2.next());
      assertEquals(9, oddSquares2.next());
      assertFalse(oddSquares2.hasNext());
   }
}
