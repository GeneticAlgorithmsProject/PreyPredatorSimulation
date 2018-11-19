package model.individual;

import javafx.scene.paint.Color;

public class Food extends Individual {

	public Food() {
		super();
		color = new Color(0, 1, 0, 1);
		genotype[Gene.sightR.ordinal()] = 0;
	}

	public Food(double x, double y) {
		super(x, y);
		color = new Color(0, 1, 0, 1);
		genotype[Gene.sightR.ordinal()] = 0;
	}

	public Food(double R) {
		super(R);
		color = new Color(0, 1, 0, 1);
		genotype[Gene.sightR.ordinal()] = 0;
	}

}
