package getalrij;

import java.util.function.BiPredicate;
import java.util.Collection;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.Random;

public class SnelheidOefening {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SnelheidOefening speed = new SnelheidOefening(5, 100000);
		System.out.println("Done");
	}

	private long seed = new Random().nextLong();
	private int listSize = 100000;
	private int sampleSize = 1;

  /**
	 * Create a sort algorithm benchmark with the specified sample and list size.
	 */
	protected SnelheidOefening(int sampleSize, int listSize) {
		this.sampleSize = sampleSize;
		this.listSize = listSize;
	}
	/**
	 * Create a sort algorithm benchmark with the default sample and list size.
	 */
	protected SnelheidOefening() {
	}

	protected OptionalDouble bench(BiPredicate<GetalRij, Integer> contains) {
		Collection<Long> samples = new ArrayList<>(sampleSize);
		// Reuse seed for fair comparison.
		Random random = new Random(this.seed);
		for (int i = 0; i < this.sampleSize; i++) {
			GetalRij gr = new GetalRij(this.listSize, 2 * this.listSize);
			int getal = random.nextInt(2 * this.listSize);
			long start = this.time();
			contains.test(gr, getal);
			samples.add(this.time() - start);
		}
		return samples.stream()
			.mapToLong(n -> n)
			.average();
	}

	// Hulpmethode voor tijdsbepaling
	private long time() {
		return System.currentTimeMillis();
	}
}
