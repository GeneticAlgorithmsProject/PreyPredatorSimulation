package model;

import javafx.scene.paint.Color;

public class Prey extends Individual {

	private Vector2d dirEscape;

	private double speed;

	private Gene gene;

	public Prey() {
		super();
		dirEscape = new Vector2d();
		speed = 1.;
		color = new Color(0, 0, 1, 1);
		gene = new Gene();
	}

	public Prey(double x, double y) {
		super(x, y);
		dirEscape = new Vector2d();
		speed = 1.;
		color = new Color(0, 0, 1, 1);
		gene = new Gene();
	}

	@Override
	public void move() {
		dirEscape.mult(gene.getDirEscapeMult());
		dirFood.mult(gene.getDirFoodMult());
		Vector2d dir = Vector2d.add(dirFood, dirEscape);
		dir.mult(speed);
		pos.add(dir);
	}

	public Vector2d getDirEscape() {
		return dirEscape;
	}

	public void setDirEscape(Vector2d dirEscape) {
		this.dirEscape = dirEscape;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

}
