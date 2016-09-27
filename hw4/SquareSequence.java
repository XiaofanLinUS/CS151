import java.util.ArrayList;
import java.util.function.LongPredicate;

public class SquareSequence implements NumberSequence {
	private int currentIndex = 0;
	private ArrayList<Long> numberList;
	private LongPredicate P;
	public SquareSequence() {
		numberList = new ArrayList<Long>();
	}
	
	public long next() {
		long result;
		if(P == null) {
			result = currentIndex * currentIndex;
			numberList.add(result);
			currentIndex++;
		}else {
			result = currentIndex * currentIndex;
			currentIndex++;
			while(!P.test(result)) {
			result = currentIndex * currentIndex;
			currentIndex++;
			}
			numberList.add(result);
		}
		return result;
	}
	
	public double average(int n) {
		long sum = 0;
		for(int i = 0; i < n; i++) {
			sum += this.next();
		}
		return ((double) sum) / n;
	}
	
	public static void main(String[] args) {
	      NumberSequence oddSquares = new SquareSequence().filter(n -> n % 2 != 0);
	      System.out.println(oddSquares.next());
	      System.out.println(oddSquares.next());
	      System.out.println(oddSquares.next());
	}

	public NumberSequence filter(LongPredicate p) {
		P = p;
		return this;
	}
}
