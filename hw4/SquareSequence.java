import java.util.ArrayList;

/**
 * A square sequence
 * @author linxiaofan
 *
 */
public class SquareSequence implements NumberSequence {
	private int currentIndex = 0;
	private ArrayList<Long> numberList;
	
	/**
	 * Construct a square sequence
	 */
	public SquareSequence() {
		numberList = new ArrayList<Long>();
	}
	
	/**
	 * Get the next value in this sequence
	 * @return the next value
	 */
	public long next() {
		long result = currentIndex * currentIndex;
		numberList.add(result);
		currentIndex++;
	
		return result;
	}
	/**
	public double average(int n) {
		long sum = 0;
		for(int i = 0; i < n; i++) {
			sum += this.next();
		}
		return ((double) sum) / n;
	}
	**/
	

	

}
