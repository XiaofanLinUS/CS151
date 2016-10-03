import java.util.ArrayList;
import java.util.function.LongUnaryOperator;

/**
 * This class is used to generate a infinite sequence
 * @author linxiaofan
 *
 */
public class InfiniteSequence implements NumberSequence {
	LongUnaryOperator f;
	ArrayList<Long> numberList;
	int currentIndex;
	/**
	 * Construct a infinite sequence with a given LongUnaryOperator and a seed
	 * @param aSeed the seed
	 * @param anF the LongUnaryOperator 
	 */
	public InfiniteSequence(long aSeed, LongUnaryOperator anF) {
		numberList = new ArrayList<Long>();
		numberList.add(aSeed);
		f = anF;
		currentIndex = 0;
	}
	/**
	 * Get the next value in this sequence
	 * @return the next value
	 */
	public long next() {
		numberList.add(f.applyAsLong(numberList.get(currentIndex)));
		currentIndex++;
		return numberList.get(currentIndex - 1);
	}

}
