package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

public class Prey extends Individual {

	private Vector2d dirEscape;

	// auxiliary variables for changing direction of prey
	private double fDir, fA;
	
	public Prey() {
		super();
		dirEscape = new Vector2d();
		color = new Color(0, 0, 1, 1);
		fDir = 1.;
		fA = 0.;
	}

	public Prey(double x, double y) {
		super(x, y);
		dirEscape = new Vector2d();
		color = new Color(0, 0, 1, 1);
		fDir = 1.;
		fA = 0.;
	}
	
	public Prey(double R) {
		super(R);
		dirEscape = new Vector2d();
		color = new Color(0, 0, 1, 1);
		fDir = 1.;
		fA = 0.;	
	}

	@Override
	public void move(double dt) {
		dirFood = Vector2d.normedDiff(prey.getPos(), pos);
		dirFood.mult(gene.getDirFoodMult());
		dirEscape.mult(gene.getDirEscapeMult());
		
		Vector2d dir = Vector2d.add(dirFood, dirEscape);
		oscillate(dir);
		hunger(dir);
		dir.norm();
		dir.mult(speed);
		pos.add(dir);
	}

	private void oscillate(Vector2d v) {
		if(Vector2d.dist(pos, prey.getPos()) < gene.getSight())
			return;
		
		Vector2d v_t = Vector2d.PerpendicularClockwise(v);
		v_t.norm();
		v_t.mult(fA);
		if (Math.abs(fA) >= gene.getNoiseA())
			fDir *= -1;
		fA += fDir * gene.getNoiseA() * gene.getNoiseF();
		v.add(v_t);
	}
	
	private void hunger(Vector2d v) {
		if(health > gene.getHungerLevel())
			return;
		
		v.add(dirFood.multV(gene.getHungerMult()));
	}

	public Vector2d getDirEscape() {
		return dirEscape;
	}

	public void setDirEscape(Vector2d dirEscape) {
		this.dirEscape = dirEscape;
	}

}
