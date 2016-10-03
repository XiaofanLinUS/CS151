/**
 * A usual sequence with given numbers in it
 * @author linxiaofan
 *
 */
public class UsualSequence implements NumberSequence {
	long[] numberList;
	int currentIndex;
	boolean next;
	/**
	 * Construct a sequence with numbers in it
	 * @param args numbers
	 */
	public UsualSequence(long...args) {
		numberList = args;
		next = true;
		currentIndex = 0;
	}
	/**
	 * Get the next value in this sequence
	 * @return the next value
	 */
	public long next() {
		if (!hasNext()) {
			return -1;
		}
		long currentElement = numberList[currentIndex];
		currentIndex++;
		if (currentIndex == numberList.length) {
			next = false;
		}
		return currentElement;
	}
	
	/**
	 * Return true if this sequence has a next value
	 * @return true if this sequence has a next value
	 */
	public boolean hasNext() { 
		return next; 
	}
/**	
	public double average(int n) {
		int size, sum = 0;
		if (n > numberList.length) {
			size = numberList.length;
		}else {
			size = n;
		}
		for (int i = 0; i <= size - 1; i++) {
			sum += numberList[i];
		}
		return ((double) sum) / size;
	}
	
	public double average() {
		int size = numberList.length, sum = 0;
		
		for (int i = 0; i <= size - 1; i++) {
			sum += numberList[i];
		}
		return ((double) sum) / size;
	}

	public long[] toArray(int n) {
		if(n >= numberList.length) {
			return numberList.clone();
		}
		long[] anArray = new long[n];
		for(int i = 0; i < n; i++) {
			anArray[i] = numberList[i];
		}
		return anArray;
	}
	**/
}
