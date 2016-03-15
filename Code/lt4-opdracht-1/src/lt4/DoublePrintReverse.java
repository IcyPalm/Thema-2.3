package lt4;

import java.lang.Thread;
import java.lang.Runnable;

public class DoublePrintReverse {
	public static void main(String[] args) {
		new DoublePrintReverse();
	}

	public DoublePrintReverse() {
		for (int i = 1; i <= 4; i++) {
			new Thread(new PrintTask(i)).start();
		}
	}

	private class PrintTask implements Runnable {
		private int number;

		public Printer(int number) {
			this.number = number;
		}

		public void run() {
			System.out.print(number);
			System.out.println(number);
		}
	}
}
