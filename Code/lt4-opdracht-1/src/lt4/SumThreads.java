package lt4;

import java.util.Collection;
import java.util.ArrayList;
import java.lang.Thread;
import java.lang.Runnable;

public class SumThreads {
	public static void main(String[] args) {
		new SumThreads(1000);
	}

	private IntRef sum = new IntRef(0);
	private Collection<Thread> threads;

	public SumThreads(int threadCount) {
		System.out.print("Unsynchronized: ");
		System.out.println(this.runUnsynchronized(threadCount));

		System.out.print("Synchronized: ");
		System.out.println(this.runSynchronized(threadCount));
	}

	/**
	 * Create a collection of workers with a particular sum reference.
	 *
	 * @param count Amount of workers to create.
	 * @param initialValue Sum reference.
	 * @return `count` Threads.
	 */
	private Collection<Thread> makeThreads(int count, IntRef initialValue) {
		Collection<Thread> threads = new ArrayList<Thread>(count);
		for (int i = 0; i < count; i++) {
			threads.add(new Thread(
				new SumWorker(initialValue)
			));
		}
		return threads;
	}

	/**
	 * Run and wait for a collection of threads.
	 *
	 * @param threads Thread instances to run.
	 */
	private void run(Collection<Thread> threads) {
		threads.parallelStream()
			.forEach(Thread::start);

		try {
			for (Thread t : threads) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Run 1000 workers incrementing an unsynchronized int reference.
	 *
	 * @return The resulting sum.
	 */
	private int runUnsynchronized(int threadCount) {
		IntRef sum = new IntRef(0);
		Collection<Thread> threads = this.makeThreads(threadCount, sum);

		this.run(threads);

		return sum.get();
	}

	/**
	 * Run 1000 workers incrementing a synchronized int reference.
	 *
	 * @return The resulting sum.
	 */
	private int runSynchronized(int threadCount) {
		IntRef sum = new IntRefSynchronized(0);
		Collection<Thread> threads = this.makeThreads(threadCount, sum);

		this.run(threads);

		return sum.get();
	}

	/**
	 * Worker class to increment a value in a webscale multithreaded way.
	 */
	private class SumWorker implements Runnable {
		/**
		 * Reference to the value to increment.
		 */
		private IntRef value;

		/**
		 * Create a new worker.
		 *
		 * @param value Reference to increment.
		 */
		public SumWorker(IntRef value) {
			this.value = value;
		}

		/**
		 * Increment the value.
		 */
		public void run() {
			this.value.increment();
		}
	}

	/**
	 * Wrapper to pass int values by reference.
	 */
	private class IntRef {
		/**
		 * Current value.
		 */
		private int value;

		/**
		 * Create a new int reference.
		 *
		 * @param value Initial value.
		 */
		public IntRef(int value) {
			this.set(value);
		}

		/**
		 * Get the current value of the reference.
		 *
		 * @return The value.
		 */
		public int get() {
			return this.value;
		}

		/**
		 * Set the value of the reference.
		 *
		 * @param value The new value.
		 */
		public void set(int value) {
			this.value = value;
		}

		/**
		 * Increment the referenced value by one.
		 */
		public void increment() {
			this.set(this.get() + 1);
		}
	}

	/**
	 * Synchronized wrapper to pass int values by reference.
	 */
	private class IntRefSynchronized extends IntRef {
		/**
		 * Create a new synchronized int reference.
		 *
		 * @param value Initial value.
		 */
		public IntRefSynchronized(int value) {
			super(value);
		}

		/**
		 * Increment the referenced value by one.
		 */
		public synchronized void increment() {
			this.set(this.get() + 1);
		}
	}
}
