package lt4;

import java.lang.Thread;
import java.lang.Runnable;

public class DoublePrint {
	public static void main(String[] args) {
		new DoublePrint();
	}

	public DoublePrint() {
		for (int i = 1; i <= 4; i++) {
			new Thread(new Printer(i)).start();
		}
	}

	private class Printer implements Runnable {
		private int number;
		public Printer(int number) {
			this.number = number;
		}
		public void run() {
			synchronized (System.out) {
				System.out.print(number);
				System.out.println(number);
			}
		}
	}
}
