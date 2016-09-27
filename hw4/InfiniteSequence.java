import java.util.ArrayList;
import java.util.function.LongUnaryOperator;

public class InfiniteSequence implements NumberSequence {
	LongUnaryOperator f;
	ArrayList<Long> numberList;
	int currentIndex;
	public InfiniteSequence(long aSeed, LongUnaryOperator anF) {
		numberList = new ArrayList<Long>();
		numberList.add(aSeed);
		f = anF;
		currentIndex = 0;
	}
	public long next() {
		numberList.add(f.applyAsLong(numberList.get(currentIndex)));
		currentIndex++;
		return numberList.get(currentIndex - 1);
	}

}
