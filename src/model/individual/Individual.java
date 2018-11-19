package model.individual;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.individual.Fenotype.Move;
import utils.Vector2d;

public class Individual extends Fenotype implements Live {

	protected Vector2d pos;

	protected Random rnd = new Random();

	protected Individual goal;

	public Individual() {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d();
		shape = new Circle(pos.x, pos.y, getSizeR());
		sight = new Circle(pos.x, pos.y, getSightR());
		color = new Color(0, 1, 0, 1);
	}

	public Individual(double x, double y) {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d(x, y);
		shape = new Circle(pos.x, pos.y, getSizeR());
		sight = new Circle(pos.x, pos.y, getSightR());
		color = new Color(0, 1, 0, 1);
	}

	public Individual(double R) {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d(R);
		shape = new Circle(pos.x, pos.y, getSizeR());
		sight = new Circle(pos.x, pos.y, getSightR());
		color = new Color(0, 1, 0, 1);
	}

	public void update(double dt) {
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		shape.setFill(color);
		shape.setRadius(getSizeR());
		shape.setOpacity(health / maxHealth);

		sight.setCenterX(pos.x);
		sight.setCenterY(pos.y);
		sight.setFill(color);
		sight.setRadius(getSightR());
		sight.setOpacity(0.2 * health / maxHealth);

		decrementHealth(dt);

		if (isDead())
			return;

		age += dt;
	}

	protected void oscillate(Vector2d v) {
		if (Vector2d.dist(pos, pos) < getSightR())
			return;

		Vector2d v_t = Vector2d.PerpendicularClockwise(v);
		v_t.norm();
		v_t.mult(fA);
		if (Math.abs(fA) >= getNoiseA())
			fDir *= -1;
		fA += fDir * getNoiseA() * getNoiseF();
		v.add(v_t);
	}

	protected void hunger(Vector2d v) {
		if (health > getHungerLevel())
			return;

		v.add(movement[Move.dGo.ordinal()].multV(getHungerMult()));
	}

	protected void randomWalk(double dt) {

	}

	@Override
	public void move(double dt) {

	}

	@Override
	public void die(double dt) {

	}

	public void decrementHealth(double dt) {
		health -= incrementHealth * dt / 2;
	}

	public void incrementHealth(double dt) {
		health += incrementHealth * dt;
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

	public void setAge(double age) {
		this.age = age;
	}

	public double getHealth() {
		return health;
	}

	public void death() {
		health = 0;
	}

	public boolean isDead() {
		return health <= 0;
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

	public Individual getGoal() {
		return goal;
	}

	public void setGoal(Individual prey) {
		this.goal = prey;
	}

}
