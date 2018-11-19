package ga;

import java.util.Random;

public class Genotype {

	protected double[] genotype;
	protected enum Gene {
	    dEscM, dGoM, nA, nF, hM, hL, sightR, sizeR 
	}

	public Genotype() {
		Random rnd = new Random();
		genotype = new double[Gene.values().length];
		genotype[Gene.dEscM.ordinal()] = rnd.nextDouble();
		genotype[Gene.dGoM.ordinal()] = rnd.nextDouble();
		genotype[Gene.nA.ordinal()] = (1 + rnd.nextDouble()) * 5;
		genotype[Gene.nF.ordinal()] = (1 + rnd.nextDouble()) * 0.01;
		genotype[Gene.sightR.ordinal()]  = (1 + rnd.nextDouble()) * 50;
		genotype[Gene.sizeR.ordinal()] = (1 + rnd.nextDouble()) * 2;
		genotype[Gene.hM.ordinal()] = (1 + rnd.nextDouble()) * 50;
		genotype[Gene.hL.ordinal()] = rnd.nextDouble();
	}

	public double[] getGenotype() {
		return genotype;
	}

	public double getDirEscapeMult() {
		return genotype[Gene.dEscM.ordinal()];
	}

	public double getDirGoMult() {
		return genotype[Gene.dGoM.ordinal()];
	}

	public double getNoiseA() {
		return genotype[Gene.nA.ordinal()];
	}

	public double getNoiseF() {
		return genotype[Gene.nF.ordinal()];
	}

	public double getSightR() {
		return genotype[Gene.sightR.ordinal()];
	}

	public double getSizeR() {
		return genotype[Gene.sizeR.ordinal()];
	}
	
	public double getHungerMult() {
		return genotype[Gene.hM.ordinal()];
	}
	
	public double getHungerLevel() {
		return genotype[Gene.hL.ordinal()];
	}
	
}
