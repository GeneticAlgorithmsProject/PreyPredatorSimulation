package ga;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Gene {

	private Map<String, Double> gene;
	public final String[] keys = { "dirEscapeMult", "dirFoodMult", "noiseA", "noiseF", "noiseR", "size" };

	public Gene() {
		Random rnd = new Random();
		gene = new HashMap<>();
		gene.put(keys[0], rnd.nextDouble());
		gene.put(keys[1], (1 + rnd.nextDouble()) * 5);
		gene.put(keys[2], (1 + rnd.nextDouble()) * 5);
		gene.put(keys[3], (1 + rnd.nextDouble()) * 0.01);
		gene.put(keys[4], (1 + rnd.nextDouble()) * 30);
		gene.put(keys[5], (1 + rnd.nextDouble()) * 2);
	}

	public Map<String, Double> getGene() {
		return gene;
	}

	public double getDirEscapeMult() {
		return gene.get(keys[0]);
	}

	public void setDirEscapeMult(double dirEscapeMult) {
		gene.put(keys[0], dirEscapeMult);
	}

	public double getDirFoodMult() {
		return gene.get(keys[1]);
	}

	public void setDirFoodMult(double dirFoodMult) {
		gene.put(keys[1], dirFoodMult);
	}

	public double getNoiseA() {
		return gene.get(keys[2]);
	}

	public void setNoiseA(double noiseA) {
		gene.put(keys[2], noiseA);
	}

	public double getNoiseF() {
		return gene.get(keys[3]);
	}

	public void setNoiseF(double noiseF) {
		gene.put(keys[3], noiseF);
	}

	public double getNoiseR() {
		return gene.get(keys[4]);
	}

	public void setNoiseR(double noiseR) {
		gene.put(keys[4], noiseR);
	}

	public double getSize() {
		return gene.get(keys[5]);
	}

	public void setSize(double size) {
		gene.put(keys[5], size);
	}

}
