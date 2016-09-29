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