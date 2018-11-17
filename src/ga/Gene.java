package ga;

import java.util.Random;

public class Gene {

	private double dirEscapeMult;

	private double dirFoodMult;
	private double noiseA;
	private double noiseF;
	private double noiseR;
	private double size;

	public Gene() {
		Random rnd = new Random();
		dirFoodMult = 1.;
		dirEscapeMult = 10;
		noiseA = (1 + rnd.nextDouble()) * 5;
		noiseF = (1 + rnd.nextDouble()) * 0.01;
		noiseR = (1 + rnd.nextDouble()) * 30;
		size = (1 + rnd.nextDouble()) * 2;
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

	public double getNoiseR() {
		return noiseR;
	}

	public void setNoiseR(double noiseR) {
		this.noiseR = noiseR;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}
