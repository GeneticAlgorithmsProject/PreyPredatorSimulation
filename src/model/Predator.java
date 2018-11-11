package model;

import javafx.scene.paint.Color;

public class Predator extends Individual{

	private Gene gene;

	public Predator() {
		super();
		dirFood = new Vector2d();
		speed = 1.;
		color = new Color(1, 0, 0, 1);	
		gene = new Gene();
	}

	public Predator(double x, double y) {
		super(x, y);
		dirFood = new Vector2d();
		speed = 1.;
		color = new Color(1, 0, 0, 1);	
		gene = new Gene();
	}

	@Override
	public void move() {
		dirFood.mult(gene.getDirFoodMult());
		dirFood.mult(speed);
		pos.add(dirFood);
	}
}
