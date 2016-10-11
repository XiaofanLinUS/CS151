import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class NumberSequenceTest3
{
   @Test public void filterTest3()
   {
      Random gen = new Random(42);
      NumberSequence randomSequence = () -> gen.nextLong();
      // NumberSequence is a functional interface!
      NumberSequence positiveRandoms = randomSequence.filter(n -> n > 0);
      assertEquals(5694868678511409995L, positiveRandoms.next());
   }
}
