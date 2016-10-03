import java.util.ArrayList;
import java.util.function.LongPredicate;

/**
 * This class is used to create a Sequence that is filtered by a predicate
 * @author linxiaofan
 *
 */
public class FilteredSequence implements NumberSequence {
	private NumberSequence numberSequence;
	private LongPredicate predicate;
	private ArrayList<Long> numberList;
	/**
	 * Construct a fitered sequence with a given predicate and a sequence
	 * @param aNumberSequence the sequence
	 * @param aPredicate the predicate	
	 */
	public FilteredSequence(NumberSequence aNumberSequence,LongPredicate aPredicate) {
		numberSequence = aNumberSequence;
		predicate = aPredicate;
		numberList = new ArrayList<Long>();
	}
	
	/**
	 * Get the next value in this sequence
	 * @return the next value
	 */
	public long next() {
		boolean passed;
		long val;
		do {
			val = numberSequence.next();
			passed = predicate.test(val);
		} while(!passed);
		numberList.add(val);
		return val;
	}
	
	/**
	 * Return true if this sequence has a next value
	 * @return true if this sequence has a next value
	 */
	public boolean hasNext() {
		return numberSequence.hasNext();
	}

}
