import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;

public interface NumberSequence
{
   long next();
   default boolean hasNext() { return true; }

   static NumberSequence of(long... args) 
   {
      class FiniteSequence implements NumberSequence
      {
         private int n = 0;
         @Override public boolean hasNext()
         {
            return n < args.length;
         }
         @Override public long next()
         {
            n++;
            return args[n - 1];
         }
      }
      
      return new FiniteSequence();
   }
   
   default double average(int n) 
   {
      int i = 0;
      double sum = 0;
      while (i < n && hasNext())
      {
         sum += next();
         i++;
      }
      return i == 0 ? 0 : sum / i;
   }
   
   default double average() 
   {
      int i = 0;
      double sum = 0;
      while (hasNext())
      {
         sum += next();
         i++;
      }
      return i == 0 ? 0 : sum / i;
   }
   
   default long[] toArray(int n)
   {
      long[] result = new long[n];
      int i = 0;
      double sum = 0;
      while (i < n && hasNext())
      {
         result[i] = next();
         i++;
      }
      if (i < n) return Arrays.copyOf(result, i);
      else return result;
   }
   
   class FilteredSequence implements NumberSequence
   {
      private NumberSequence originalSequence;
      private LongPredicate p;
      private long nextElement;
      private boolean hasMoreElements;
      
      private void advanceToNext()
      {
         boolean done = false;
         while (!done)
         {
            if (originalSequence.hasNext())
            {
               long value = originalSequence.next();
               if (p.test(value))
               {
                  nextElement = value;
                  done = true;
               }
            }                  
            else
            {
               hasMoreElements = false;
               done = true;
            }
         }
      }
      
      public FilteredSequence(NumberSequence sequence, LongPredicate p)      
      {
         originalSequence = sequence;         
         this.p = p;
         advanceToNext();
      }
      
      @Override public boolean hasNext()
      {            
         return hasMoreElements;
      }
      
      @Override public long next()
      {
         long result = nextElement;
         advanceToNext();
         return result;
      }
   }   
      
   default NumberSequence filter(LongPredicate p)
   {  
      return new FilteredSequence(this, p);
   }
   
   static NumberSequence iterate(long seed, LongUnaryOperator f)
   {
      class IteratedSequence implements NumberSequence
      {
         long current = seed;
         @Override public long next()
         {
            long result = current;
            current = f.applyAsLong(current);
            return result;
         }
      }
      
      return new IteratedSequence();
   }
   
   static NumberSequence random(long seed)
   {
      return iterate(seed, n -> (n * 1103515245 + 12345) & 0x7FFFFFFF);
   }   
}
