package model.individual;

import java.util.Random;

public class Genotype {

	protected double[] genotype;
	protected static Random rnd = new Random();
	
	protected enum Gene {
		DESCM, DGOM, OSCA, OSCF, HEAM, HEAL, SIGA, SIZA, DRANM, DRANA
	}

	public Genotype() {
		genotype = new double[Gene.values().length];
//		Escape vector multiplier 
		genotype[Gene.DESCM.ordinal()] = rnd.nextDouble();
//		Goal vector multiplier
		genotype[Gene.DGOM.ordinal()] = rnd.nextDouble();
//		Oscillations amplitude
		genotype[Gene.OSCA.ordinal()] = (1 + rnd.nextDouble()) * 5;
//		Oscillations fractional increment
		genotype[Gene.OSCF.ordinal()] = (1 + rnd.nextDouble()) * 0.01;
//		Sight radius
		genotype[Gene.SIGA.ordinal()] = (1 + rnd.nextDouble()) * 50;
//		Size radius
		genotype[Gene.SIZA.ordinal()] = (1 + rnd.nextDouble()) * 2;
//		Goal vector multiplier when hungry
		genotype[Gene.HEAM.ordinal()] = (1 + rnd.nextDouble()) * 50;
//		Hunger level
		genotype[Gene.HEAL.ordinal()] = rnd.nextDouble();
//		Probability of changing direction when in random walk
		genotype[Gene.DRANM.ordinal()] = (1 + rnd.nextDouble()) * 0.01;
//		Random walk leap
		genotype[Gene.DRANA.ordinal()] = (1 - rnd.nextDouble());	
	}

	public double[] getGenotype() {
		return genotype;
	}

	public void setGenotype(double[] genotype) {
		this.genotype = genotype;
	}

	public double getDEscM() {
		return genotype[Gene.DESCM.ordinal()];
	}

	public double getDGoM() {
		return genotype[Gene.DGOM.ordinal()];
	}

	public double getOscA() {
		return genotype[Gene.OSCA.ordinal()];
	}

	public double getOscF() {
		return genotype[Gene.OSCF.ordinal()];
	}

	public double getSigA() {
		return genotype[Gene.SIGA.ordinal()];
	}

	public double getSizA() {
		return genotype[Gene.SIZA.ordinal()];
	}

	public double getHeaM() {
		return genotype[Gene.HEAM.ordinal()];
	}

	public double getHeaL() {
		return genotype[Gene.HEAL.ordinal()];
	}

	public double getDRanM() {
		return genotype[Gene.DRANM.ordinal()];
	}
	
	public double getDRanA() {
		return genotype[Gene.DRANA.ordinal()];
	}
}
