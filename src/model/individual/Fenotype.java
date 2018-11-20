package model.individual;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utils.Vector2d;

public class Fenotype extends Genotype{

	protected Color color;
	
	protected Circle shape;
	protected Circle sight;
	
	protected double age;
	protected double health, maxHealth, incrementHealth;
	protected double speed;
	
	protected double fDir, fA;

	protected Vector2d[] movement;
	
	enum Move {
	    dEsc, dGo
	}
	
	public Fenotype() {
		super();
		movement = new Vector2d[Move.values().length];
		for(int i = 0; i < Move.values().length; i++) {
			movement[i] = new Vector2d();
		}
		color = new Color(1,1,1,1);
		shape = new Circle();
		sight = new Circle();
		age = 0;
		health = 1;
		maxHealth = 1;
		incrementHealth = 0.1;
		speed = 1;
	}
	
	public Vector2d getDirGo() {
		return movement[Move.dGo.ordinal()];
	}
	
	public Vector2d getDirEsc() {
		return movement[Move.dEsc.ordinal()];
	}
}
