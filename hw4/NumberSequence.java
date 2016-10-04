import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;

public interface NumberSequence
{

	/**
	 * Get a sequence with given numbers inside
	 * @param args given numbers
	 * @return the sequence
	 */
	static NumberSequence of(long... args) {
		NumberSequence aSequence = new UsualSequence(args);
		return aSequence;
	}
	
	/**
	 * Get the next value in this sequence
	 * @return the next value
	 */
	long next();
	/**
	 * Return true if this sequence has a next value
	 * @return true if this sequence has a next value
	 */
	default boolean hasNext() { return true; }
	/**
	 * Calculate the sequence's average in size n
	 * @param n the size
	 * @return the sequence's average
	 */
	default double average(int n) {
		int i = 0;
		double sum = 0;
		while(i < n && hasNext()) {
			sum += next();
			i++;
		}
		
		if(i == 0) {
			return 0;
		}else {
			return sum / i;
		}
	}
	/**
	 * Calculate the sequence's average
	 * @return the sequence's average
	 */
	default double average() {
		int i = 0;
		double sum = 0;
		while(hasNext()) {
			sum += next();
			i++;
		}
		if(i == 0) {
			return 0;
		}else {
			return sum / i;
		}
	}
	
	/**
	 * Transform this sequnce to an array in size n
	 * @param n the size
	 * @return the array
	 */
	default long[] toArray(int n) {
		long[] result = new long[n];
		int i = 0;
		while (i < n && hasNext()) {
			result[i] = next();
			i++;
		}
		System.out.println(i);
		if(i < n) {
			int size = i;
			long[] theResult = new long[size];
			for(i = 0; i < size; i++) {
				theResult[i] = result[i];
			}
			return theResult;
		}
		return result; 
	}
	
	/**
	 * Create an infinite sequence with a given seed and operator
	 * @param seed the seed
	 * @param f the operator 
	 * @return the sequence
	 */
	static NumberSequence iterate(long seed, LongUnaryOperator f) {
		return new InfiniteSequence(seed, f);
	}
	
	/**
	 * Create an infinite random sequence with a given seed
	 * @param seed the seed
	 * @return the infinite random sequence
	 */
	static NumberSequence random(long seed) {
		long m = (long) Math.pow(2, 31);
		long a = 1103515245;
		long c = 12345;
		LongUnaryOperator f = x -> (x*a + c) % m;
		return iterate(seed, f);
	}
	
	/**
	 * Get a sequence that is filtered by a predicate p
	 * @param p the predicate
	 * @return the sequence
	 */
	default NumberSequence filter(LongPredicate p) {
		NumberSequence filteredSequence = new FilteredSequence(this, p);
		return filteredSequence;
	}

}