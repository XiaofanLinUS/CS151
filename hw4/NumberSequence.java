import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;

public interface NumberSequence
{


	static NumberSequence of(long... args) {
		NumberSequence aSequence = new UsualSequence(args);
		return aSequence;
	}
	
	long next();
	default boolean hasNext() { return true; }
	default double average(int n) { return -1; }
	default double average() { return -1; }
	default long[] toArray(int i) { return null; }
	static NumberSequence iterate(long seed, LongUnaryOperator f) {
		return new InfiniteSequence(seed, f);
	}
	
	static NumberSequence random(long seed) {
		long m = (long) Math.pow(2, 31);
		long a = 1103515245;
		long c = 12345;
		LongUnaryOperator f = x -> (x*a + c) % m;
		return iterate(seed, f);
	}
	
	default NumberSequence filter(LongPredicate p) {
		NumberSequence filteredSequence = new FilteredSequence(this, p);
		return filteredSequence;
	}

}