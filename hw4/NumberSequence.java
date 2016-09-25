public interface NumberSequence
{
   long next();
   default boolean hasNext() { return true; }
}