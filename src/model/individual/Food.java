package model.individual;

import javafx.scene.paint.Color;

public class Food extends Individual implements Live{

	public Food() {
		super();
		color = new Color(0, 1, 0, 1);
		genotype[Gene.SIGA.ordinal()] = 0;
		name = "Food";
	}

	public Food(double x, double y) {
		super(x, y);
		color = new Color(0, 1, 0, 1);
		genotype[Gene.SIGA.ordinal()] = 0;
		name = "Food";
	}

	public Food(double R) {
		super(R);
		color = new Color(0, 1, 0, 1);
		genotype[Gene.SIGA.ordinal()] = 0;
		name = "Food";
	}
	
	@Override
	public void move(double dt) {
		
	}

	@Override
	public void die(double dt) {
		
	}

}
