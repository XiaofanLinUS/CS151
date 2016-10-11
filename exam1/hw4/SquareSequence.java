

public class SquareSequence implements NumberSequence
{
   private long i;
   
   public long next()
   {
      long result = i * i;
      i++;
      return result;
   }
}
