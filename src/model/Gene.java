package model;

public class Gene {

	private double dirEscapeMult;

	private double dirFoodMult;
	private double noiseA;
	private double noiseF;
	
	public Gene() {
		dirFoodMult = 1.;
		dirEscapeMult = 0.001;
		noiseA = 10;
		noiseF = 0.01;
	}

	public double getDirEscapeMult() {
		return dirEscapeMult;
	}

	public void setDirEscapeMult(double dirEscapeMult) {
		this.dirEscapeMult = dirEscapeMult;
	}

	public double getDirFoodMult() {
		return dirFoodMult;
	}

	public void setDirFoodMult(double dirFoodMult) {
		this.dirFoodMult = dirFoodMult;
	}

	public double getNoiseA() {
		return noiseA;
	}

	public void setNoiseA(double noiseA) {
		this.noiseA = noiseA;
	}

	public double getNoiseF() {
		return noiseF;
	}

	public void setNoiseF(double noiseF) {
		this.noiseF = noiseF;
	}

}
