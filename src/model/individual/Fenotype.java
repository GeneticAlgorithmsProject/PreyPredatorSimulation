package model.individual;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utils.Vector2d;

public class Fenotype extends Genotype {

	protected Color color;

	protected Circle shape;
	protected Circle sight;

	protected double age;
	protected double health, maxHealth, incrementHealth, decrementHealth;
	protected double speed;

	protected double fDir, fA;

	protected Vector2d[] movement;

	enum Move {
		dEsc, dGo
	}

	public Fenotype() {
		super();
		movement = new Vector2d[Move.values().length];
		for (int i = 0; i < Move.values().length; i++) {
			movement[i] = new Vector2d();
		}
		color = new Color(1, 1, 1, 1);
		shape = new Circle();
		sight = new Circle();
		age = 0.;
		health = 1.;
		maxHealth = 1.;
		incrementHealth = 0.2;
		decrementHealth = 0.002;
		speed = 1. / getSizA();
	}

	public Vector2d getDirGo() {
		return movement[Move.dGo.ordinal()];
	}

	public Vector2d getDirEsc() {
		return movement[Move.dEsc.ordinal()];
	}

	public void decrementHealth(double dt) {
		health -= decrementHealth * dt;
	}

	public void incrementHealth(double dt) {
		health += incrementHealth;
		if(health > maxHealth)
			health = maxHealth;
	}

	public void reset() {
		health = maxHealth;
		age = 0;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Circle getShape() {
		return shape;
	}

	public void setShape(Circle shape) {
		this.shape = shape;
	}

	public Circle getSight() {
		return sight;
	}

	public void setSight(Circle sight) {
		this.sight = sight;
	}

	public double[] getGenome() {
		return genotype;
	}

	public void setGene(double[] genotype) {
		this.genotype = genotype;
	}

	

}
