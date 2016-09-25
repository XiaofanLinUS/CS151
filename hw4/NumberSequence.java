public interface NumberSequence
{

	long[] aSequcen = null;
	static NumberSequence of(long... args) {
		NumberSequence aS = new NumberSequence() {
			
			@Override
			public long next() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		aS.aSequcen = args;
		return null;
	}
	
	long next() {
	   
	}
	default boolean hasNext() { return true; }
}