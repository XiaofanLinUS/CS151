import java.util.ArrayList;
import java.util.function.LongPredicate;

public class FilteredSequence implements NumberSequence {
	private NumberSequence numberSequence;
	private LongPredicate predicate;
	private ArrayList<Long> numberList;
	public FilteredSequence(NumberSequence aNumberSequence,LongPredicate aPredicate) {
		numberSequence = aNumberSequence;
		predicate = aPredicate;
		numberList = new ArrayList<Long>();
	}
	
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
	
	public boolean hasNext() {
		return numberSequence.hasNext();
	}

}
