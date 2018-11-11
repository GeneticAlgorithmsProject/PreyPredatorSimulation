package model;

import application.Simulation;
import javafx.scene.paint.Color;

public class Prey extends Individual {

	private Vector2d dirEscape;

	private double speed;

	private Gene gene;

//	auxiliary variables for changing direction of prey
	private double fDir, fA;
	
	public Prey() {
		super();
		dirEscape = new Vector2d();
		speed = 1./Simulation.DT;
		color = new Color(0, 0, 1, 1);
		gene = new Gene();
		fDir = 1.;
		fA = 0.;
	}

	public Prey(double x, double y) {
		super(x, y);
		dirEscape = new Vector2d();
		speed = 1./Simulation.DT;
		color = new Color(0, 0, 1, 1);
		gene = new Gene();
		fDir = 1.;
		fA = 0.;
	}

	@Override
	public void move() {
		dirEscape.mult(gene.getDirEscapeMult());
		dirFood.mult(gene.getDirFoodMult());
		Vector2d dir = Vector2d.add(dirFood, dirEscape);
		dir.norm();
		oscillate(dir);
		dir.mult(speed);
		pos.add(dir);
	}
	
	private void oscillate(Vector2d v) {
		Vector2d v_t = Vector2d.PerpendicularClockwise(v);
		v_t.norm();
		v_t.mult(fA);
		if(Math.abs(fA) >= gene.getNoiseA()) {
			fDir *= -1;
		}
		fA += fDir*gene.getNoiseA()*gene.getNoiseF();
		v.add(v_t);
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
