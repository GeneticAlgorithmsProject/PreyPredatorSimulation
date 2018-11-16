package model;

import java.util.Random;

import application.Simulation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utils.Vector2d;

public class Individual {
	protected Vector2d pos;

	protected double age;
	protected double health, maxHealth;
	protected double addHealth;
	protected double speed;

	protected Vector2d dirFood;

	protected Random rnd = new Random();

	protected Circle circle;
	protected Color color;

	protected Gene gene;

	protected Individual prey;

	public Individual() {
		gene = new Gene();
		age = 0.;
		health = 1.;
		maxHealth = health;
		addHealth = 0.0001;
		pos = new Vector2d();
		circle = new Circle(pos.x, pos.y, gene.getSize());
		color = new Color(0, 1, 0, 1);
		dirFood = new Vector2d();
	}

	public Individual(double x, double y) {
		gene = new Gene();
		age = 0.;
		health = 1.;
		maxHealth = health;
		addHealth = 0.0001;
		pos = new Vector2d(x, y);
		circle = new Circle(pos.x, pos.y, gene.getSize());
		color = new Color(0, 1, 0, 1);
		dirFood = new Vector2d();
	}

	public void update(double dt) {
		circle.setCenterX(pos.x);
		circle.setCenterY(pos.y);
		circle.setFill(color);
		circle.setEffect(Simulation.lighting);
		circle.setRadius(gene.getSize());
		circle.setOpacity(health / maxHealth);
		decrementHealth(dt);
		age += dt;
	}

	public void move(double dt) {
		
	}

	public void decrementHealth(double dt) {
		health -= addHealth;
	}

	public void incrementHealth() {
		health += addHealth;
	}

	public Vector2d getPos() {
		return pos;
	}

	public void setPos(Vector2d pos) {
		this.pos = pos;
	}

	public double getAge() {
		return age;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Vector2d getDirFood() {
		return dirFood;
	}

	public void setDirFood(Vector2d dirFood) {
		this.dirFood = dirFood;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Individual getPrey() {
		return prey;
	}

	public void setPrey(Individual prey) {
		this.prey = prey;
	}

}
