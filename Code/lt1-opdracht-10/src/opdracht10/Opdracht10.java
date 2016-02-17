package opdracht10;

public class Opdracht10 {
	public static void main(String[] args) {
		int[] one = {1,2,3};
		int[] two = {4,5,6};
		try {
			int[] answer = new Opdracht10().araySum(one,two);
			for (int i = 0; i < answer.length; i++) {
				System.out.print(answer[i]+" ");
			}
			
		} catch (ArraySizeException e) {
			System.err.println(e.getMessage());
			
		}
	}

	private int[] araySum(int[] one, int[] two) throws ArraySizeException {
		if(one.length!=two.length){
			throw new ArraySizeException(one.length,two.length);
		}
		int[] three = new int[one.length];
		for (int i = 0; i < three.length; i++) {
			three[i] = one[i] + two[i];
		}
		return three;
		
	}
}
