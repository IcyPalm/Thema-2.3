package getalrij;

import java.util.function.BiPredicate;
import java.util.OptionalDouble;
import java.util.stream.LongStream;
import java.util.Random;

public class SnelheidOefening {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SnelheidOefening speed = new SnelheidOefening(200, 100000);
		speed.init();

		printResult("Linear        - zitErinA", speed.bench(GetalRij::zitErinA));
		printResult("Linear        - zitErinB", speed.bench(GetalRij::zitErinB));
		printResult("Linear Sorted - zitErinC", speed.benchSorted(GetalRij::zitErinC));
		printResult("Binary Sorted - zitErinD", speed.benchSorted(GetalRij::zitErinD));

		System.out.println("Done");
	}

	private static void printResult(String name, long time) {
		System.out.print(name + ": ");
		System.out.println(((double) time / 1000 / 1000) + "ms");
	}

	private long seed = new Random().nextLong();
	private int listSize = 100000;
	private int sampleSize = 1;
	private GetalRij list;
	private GetalRij sortedList;

  /**
	 * Create a sort algorithm benchmark with the specified sample and list size.
	 */
	public SnelheidOefening(int sampleSize, int listSize) {
		this.sampleSize = sampleSize;
		this.listSize = listSize;
	}
	/**
	 * Create a sort algorithm benchmark with the default sample and list size.
	 */
	public SnelheidOefening() {
	}

	public void init() {
		this.list = new GetalRij(this.listSize, 2 * this.listSize);
		this.sortedList = new GetalRij(this.listSize, 2 * this.listSize);
		this.sortedList.sorteer();
	}

	public long benchSorted(BiPredicate<GetalRij, Integer> contains) {
		return this.bench(this.sortedList, contains);
	}

	public long bench(BiPredicate<GetalRij, Integer> contains) {
		return this.bench(this.list, contains);
	}

	public long bench(GetalRij list, BiPredicate<GetalRij, Integer> contains) {
		LongStream.Builder samples = LongStream.builder();
		// Reuse seed for fair comparison.
		Random random = new Random(this.seed);
		for (int i = 0; i < this.sampleSize; i++) {
			int getal = random.nextInt(2 * this.listSize);
			long start = System.nanoTime();
			contains.test(list, getal);
			samples.add(System.nanoTime() - start);
		}
		return samples.build().sum();
	}
}
