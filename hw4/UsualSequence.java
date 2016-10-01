public class UsualSequence implements NumberSequence {
	long[] numberList;
	int currentIndex;
	boolean next;
	
	public UsualSequence(long...args) {
		numberList = args;
		next = true;
		currentIndex = 0;
	}
	
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