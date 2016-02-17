package opdracht10;

public class ArraySizeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4411595993869254091L;
	int one,two;
	
	public ArraySizeException(int one, int two) {
		super("Two strings need to be the same length, given lengths are: "+one+", "+two);
		this.one = one;
		this.two = two;
	}
	
	
	
}
