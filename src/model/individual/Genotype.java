package model.individual;

import model.Simulation;
import java.util.Random;

public class Genotype {

	public static final double MAXSIZE = Math.min(Simulation.width, Simulation.height) / 100;

	public static final double MAXSIGHT = Math.min(Simulation.width, Simulation.height) / 5;

	public static final double MAXPRANDOMWALK = 0.01;

	protected double[] genotype;
	protected static Random rnd = new Random();

	protected enum Gene {
		DESCM, DGOM, OSCA, OSCF, HEAL, HECL, HECM, SIGA, SIZA, DRANM, DRANA
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
		genotype[Gene.SIGA.ordinal()] = rnd.nextDouble();
//		Size radius
		genotype[Gene.SIZA.ordinal()] = rnd.nextDouble();
//		Hunger level
		genotype[Gene.HEAL.ordinal()] = rnd.nextDouble();
//		Critical hunger level
		genotype[Gene.HEAL.ordinal()] = rnd.nextDouble();
//		Goal vector multiplier when critically hungry
		genotype[Gene.HECM.ordinal()] = rnd.nextDouble();
//		Probability of changing direction when in random walk
		genotype[Gene.DRANM.ordinal()] = rnd.nextDouble();
//		Random walk leap
		genotype[Gene.DRANA.ordinal()] = rnd.nextDouble();
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
		return genotype[Gene.SIGA.ordinal()] * MAXSIGHT;
	}

	public double getSizA() {
		return (genotype[Gene.SIZA.ordinal()] + 1) * MAXSIZE ;
	}

	public double getHecM() {
		return genotype[Gene.HECM.ordinal()];
	}

	public double getHecL() {
		return genotype[Gene.HECL.ordinal()];
	}

	public double getHeaL() {
		return genotype[Gene.HEAL.ordinal()];
	}

	public double getDRanM() {
		return genotype[Gene.DRANM.ordinal()] * MAXPRANDOMWALK;
	}

	public double getDRanA() {
		return genotype[Gene.DRANA.ordinal()];
	}
}
