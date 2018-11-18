package ga;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Gene {

	private Map<String, Double> gene;
	public static final String[] keys = { "dirEscapeMult", "dirFoodMult", "noiseA", "noiseF", "sight", "size", "hungerMult", "hungerLevel" };

	public Gene() {
		Random rnd = new Random();
		gene = new HashMap<>();
		gene.put(keys[0], rnd.nextDouble());
		gene.put(keys[1], rnd.nextDouble());
		gene.put(keys[2], (1 + rnd.nextDouble()) * 5);
		gene.put(keys[3], (1 + rnd.nextDouble()) * 0.01);
		gene.put(keys[4], (1 + rnd.nextDouble()) * 50);
		gene.put(keys[5], (1 + rnd.nextDouble()) * 2);
		gene.put(keys[6], (1 + rnd.nextDouble()) * 50);
		gene.put(keys[7], rnd.nextDouble());
	}

	public Map<String, Double> getGene() {
		return gene;
	}

	public double getDirEscapeMult() {
		return gene.get(keys[0]);
	}

	public double getDirFoodMult() {
		return gene.get(keys[1]);
	}

	public double getNoiseA() {
		return gene.get(keys[2]);
	}

	public double getNoiseF() {
		return gene.get(keys[3]);
	}

	public double getSight() {
		return gene.get(keys[4]);
	}

	public double getSize() {
		return gene.get(keys[5]);
	}
	
	public double getHungerMult() {
		return gene.get(keys[6]);
	}
	
	public double getHungerLevel() {
		return gene.get(keys[7]);
	}
	
}
