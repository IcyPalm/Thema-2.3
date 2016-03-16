package lt4;

import java.lang.Thread;
import java.lang.Runnable;
import java.lang.InterruptedException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class DoublePrintReverse {
	public static void main(String[] args) {
		new DoublePrintReverse();
	}

	public DoublePrintReverse() {
		for (int i = 1; i <= 4; i++) {
			new Thread(new PrintTask(i)).start();
		}
	}

	private static class PrintTask implements Runnable {
		private static int currentNumber = 4;
		private static Lock lock = new ReentrantLock();
		private static Condition condition = lock.newCondition();
		private int number;

		public PrintTask(int number) {
			this.number = number;
		}

		private void print() {
			System.out.print(number);
			System.out.println(number);
		}

		public void run() {
			lock.lock();
			try {
				while (this.number != currentNumber) {
					condition.await();
				}

				this.print();

				currentNumber--;
				condition.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
