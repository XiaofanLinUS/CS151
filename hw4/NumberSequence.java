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
}