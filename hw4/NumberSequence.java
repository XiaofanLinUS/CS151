import java.util.function.LongUnaryOperator;

public interface NumberSequence
{


	static NumberSequence of(long... args) {
		NumberSequence aSequence = new UsualSequence(args);
		return aSequence;
	}
	
	long next();
	default boolean hasNext() { return true; }
	default double average(int n) { return 0; }
	default double average() { return 0; }
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
}