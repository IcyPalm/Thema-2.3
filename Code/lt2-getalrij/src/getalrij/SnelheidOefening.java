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
		SnelheidOefening speed = new SnelheidOefening(200, 100000);
		speed.init();
		System.out.print("Lineair - zitErinA: ");
		speed.bench(GetalRij::zitErinA).ifPresent((time) -> {
			System.out.println((time / 200) + "ns");
		});
		System.out.print("Lineair - zitErinB: ");
		speed.bench(GetalRij::zitErinB).ifPresent((time) -> {
			System.out.println((time / 200) + "ns");
		});
		System.out.println("Done");
	}

	private long seed = new Random().nextLong();
	private int listSize = 100000;
	private int sampleSize = 1;
	private GetalRij list;

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
		for (int i = 0; i < 5; i++) {
			this.list = new GetalRij(this.listSize, 2 * this.listSize);
		}
	}

	public OptionalDouble bench(BiPredicate<GetalRij, Integer> contains) {
		Collection<Long> samples = new ArrayList<>(sampleSize);
		// Reuse seed for fair comparison.
		Random random = new Random(this.seed);
		for (int i = 0; i < this.sampleSize; i++) {
			int getal = random.nextInt(2 * this.listSize);
			long start = System.nanoTime();
			contains.test(this.list, getal);
			samples.add(System.nanoTime() - start);
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
