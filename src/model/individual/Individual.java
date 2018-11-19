package model.individual;

import java.util.Random;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utils.Vector2d;

public class Individual extends Fenotype implements Live {

	protected Vector2d pos;
	protected Vector2d dir;

	protected Random rnd = new Random();

	protected Individual goal;
	protected List<Individual> run;

	protected String name;

	public Individual() {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d();
		dir = Vector2d.RandomDir();
		shape = new Circle(pos.x, pos.y, getSizA());
		sight = new Circle(pos.x, pos.y, getSigA());
		color = new Color(0, 0, 0, 1);
		run = new LinkedList<>();
		name = "Individual";
	}

	public Individual(double x, double y) {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d(x, y);
		dir = Vector2d.RandomDir();
		shape = new Circle(pos.x, pos.y, getSizA());
		sight = new Circle(pos.x, pos.y, getSigA());
		color = new Color(0, 0, 0, 1);
		run = new LinkedList<>();
		name = "Individual";
	}

	public Individual(double R) {
		super();
		age = 0.;
		health = 1.;
		fDir = 1.;
		fA = 0.;
		maxHealth = health;
		pos = new Vector2d(R);
		dir = Vector2d.RandomDir();
		shape = new Circle(pos.x, pos.y, getSizA());
		sight = new Circle(pos.x, pos.y, getSigA());
		color = new Color(0, 0, 0, 1);
		run = new LinkedList<>();
		name = "Individual";
	}

	@Override
	public void move(double dt) {

	}

	public void update(double dt) {
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		shape.setFill(color);
		shape.setRadius(getSizA());
		shape.setOpacity(health / maxHealth);

		sight.setCenterX(pos.x);
		sight.setCenterY(pos.y);
		sight.setFill(color);
		sight.setRadius(getSigA());
		sight.setOpacity(0.2 * health / maxHealth);

		decrementHealth(dt);
		run.clear();

		if (isDead())
			return;

		age += dt;
	}

	protected void oscillate() {
		if (goalInSight())
			return;

		Vector2d v_t = Vector2d.PerpendicularClockwise(dir);
		v_t.norm();
		if (Math.abs(fA) >= getOscA())
			fDir *= -1;
		fA += fDir * getOscA() * getOscF();
		v_t.mult(fA);
		dir.add(v_t);
	}

	protected void hunger(Vector2d v) {
		if (health > getHeaL())
			return;
		v.add(movement[Move.dGo.ordinal()].multV(getHeaM()));
	}

	protected void randomWalk(double dt) {
		if(goalInSight() || runInSight())
			return;
		
		if (rnd.nextDouble() < getDRanM()) {
			dir = Vector2d.RandomDir();
			dir.norm();
			dir.mult(speed*dt*getDRanA());
			pos.add(dir);
		}
		dir.reset();
	}
	
	protected boolean runInSight() {
		return run.size() > 0;
	}
	
	protected void runAway() {
		if(!runInSight())
			return;
		movement[Move.dEsc.ordinal()].reset();
		for(Individual ind : run) {
			movement[Move.dEsc.ordinal()].add(Vector2d.diff(ind.getPos(), pos));
		}
		movement[Move.dEsc.ordinal()].div(run.size());
		movement[Move.dEsc.ordinal()].mult(getDEscM());
		dir.add(movement[Move.dEsc.ordinal()]);
	}

	protected boolean goalInSight() {
		return Vector2d.dist(pos, goal.getPos()) < getSigA();
	}

	protected void moveToGoal() {
		if (!goalInSight())
			return;
		movement[Move.dGo.ordinal()].reset();
		movement[Move.dGo.ordinal()].add(Vector2d.diff(goal.getPos(), pos));
		movement[Move.dGo.ordinal()].norm();
		movement[Move.dGo.ordinal()].mult(getDGoM());
		dir.add(movement[Move.dGo.ordinal()]);
	}

	public boolean overlaps(Individual ind) {
		return Math.abs(pos.x - ind.getPos().x) < getSizA() + ind.getSizA()
				&& Math.abs(pos.y - ind.getPos().y) < getSizA() + ind.getSizA();
	}

	public void decrementHealth(double dt) {
		health -= incrementHealth * dt / 40;
	}

	public void incrementHealth(double dt) {
		health += incrementHealth * dt * 10;
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

	public void addRun(Individual ind) {
		run.add(ind);
	}

}
