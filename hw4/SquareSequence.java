public class SquareSequence implements NumberSequence {
	private int current = 0;
	public long next() {
		long result = current * current;
		current++;
		return result;
	}
	
	public static void main(String[] args) {
		SquareSequence n = new SquareSequence();
	}
}
