package getalrij;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GetalRij {
	private int[] getallen;

	public GetalRij(int aantal, int max) {
		// Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
		this.getallen = new int[aantal];
		this.vulArrayMetUniekeWaarden(aantal, max);
	}

	private void vulArrayMetUniekeWaarden(int aantal, int max) {
		// Vul een hulplijst met getallen 0, ..., max
		ArrayList hulpLijst = new ArrayList(max);
		for (int i = 0; i < max; i++) {
			hulpLijst.add(i);
		}

		// Stop 'aantal' random waarden in getallen
		Random r = new Random();
		for (int i = 0; i < aantal; i++) {
			int getal = (Integer) hulpLijst.remove(r.nextInt(hulpLijst.size()));
			this.getallen[i] = getal;
		}
	}

	public boolean zitErinA(int zoekWaarde) {
		boolean exists = false;
		for (int value : this.getallen) {
			if (value == zoekWaarde) {
				exists = true;
			}
		}
		return exists;
	}

	public boolean zitErinB(int zoekWaarde) {
		for (int value : this.getallen) {
			if (value == zoekWaarde) {
				return true;
			}
		}
		return false;
	}

	public boolean zitErinC(int zoekWaarde) {
		return false;
	}

	public boolean zitErinD(int zoekWaarde) {
		return false;
	}

	public void sorteer() {
		Arrays.sort(getallen);
	}

	public void print() {
		for (int i = 0; i < getallen.length; i++) {
			System.out.println(getallen[i]);
		}
	}
}
