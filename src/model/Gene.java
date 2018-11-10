package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gene {

	protected int numOfSpCo = 10;
	protected int numOfNoCo = 2;
	protected double noCoA = 2;
	protected List<Double> speedCoefficients;
	protected List<Double> noiseCoefficients;

	protected List<List<Double>> gene;
		
	private Random rnd;

	public Gene() {
		rnd = new Random();
		noiseCoefficients = new ArrayList<>(numOfNoCo);
		for(int i = 0; i < numOfNoCo; ++i)
			noiseCoefficients.add(rnd.nextDouble());

		speedCoefficients = new ArrayList<>(numOfSpCo);
		for(int i = 0; i < numOfSpCo; ++i)
			speedCoefficients.add(rnd.nextDouble());
		
		gene = new ArrayList<>();
		gene.add(speedCoefficients);
		gene.add(noiseCoefficients);
	}
	
	public List<List<Double>> getGene() {
		return gene;
	}

	public void setGene(List<List<Double>> gene) {
		this.gene = gene;
	}

}
