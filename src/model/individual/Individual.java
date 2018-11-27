package model.individual;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Simulation;
import utils.Vector2d;

public class Individual extends Fenotype implements Live {

	protected Vector2d pos;
	protected Vector2d dir;
	protected Line dirVec;

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
		dirVec = new Line();
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
		dirVec = new Line();
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
		dirVec = new Line();
		name = "Individual";
	}

	@Override
	public void move(double dt) {

	}

	@Override
	public void update(double dt) {
		setShape();
		setSight();
		setVecDir(dt);
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

	protected void randomWalk(double dt) {
		if (goalInSight() || runInSight() || hungry())
			return;

		if (rnd.nextDouble() < getDRanM()) {
			dir = Vector2d.RandomDir();
			dir.norm();
			dir.mult(getDRanA());
		}
	}

	protected void runAway(double dt) {
		if (!runInSight())
			return;

		movement[Move.dEsc.ordinal()].reset();
		for (Individual r : run) {
			movement[Move.dEsc.ordinal()].add(Vector2d.diff(pos, r.getPos()));
		}

		movement[Move.dEsc.ordinal()].div(run.size());
		movement[Move.dEsc.ordinal()].norm();
		movement[Move.dEsc.ordinal()].mult(getDEscM() * dt * speed);
		dir.add(movement[Move.dEsc.ordinal()]);
	}

	protected void moveToGoal(double dt) {
		if (!goalInSight() || !hungry())
			return;

		movement[Move.dGo.ordinal()].reset();
		movement[Move.dGo.ordinal()].add(Vector2d.diff(goal.getPos(), pos));
		movement[Move.dGo.ordinal()].norm();
		movement[Move.dGo.ordinal()].mult(getDGoM() * dt * speed);
		dir.add(movement[Move.dGo.ordinal()]);
	}

	protected void hunger(double dt) {
		if(!criticallyHungry())
			return;
		movement[Move.dGo.ordinal()].multV(getHecM() * dt * speed);
		dir.add(movement[Move.dGo.ordinal()]);
	}

	public void boundaryConditions() {
		if (Simulation.closed) {
			if (pos.x >= Simulation.width - getSizA()) {
				pos.x = Simulation.width - getSizA();
				dir.x *= -1;
			} else if (pos.x <= getSizA()) {
				pos.x = getSizA();
				dir.x *= -1;
			}
			if (pos.y >= Simulation.height - getSizA()) {
				pos.y = Simulation.height - getSizA();
				dir.y *= -1;
			} else if (pos.y <= getSizA()) {
				pos.y = getSizA();
				dir.y *= -1;
			}
		}
	}

	protected void bounce() {

	}

	private boolean runInSight() {
		return run.size() > 0;
	}

	private boolean goalInSight() {
		return Vector2d.dist(pos, goal.getPos()) < getSigA();
	}

	public boolean overlaps(Individual ind) {
		return Math.abs(pos.x - ind.getPos().x) < getSizA() + ind.getSizA()
				&& Math.abs(pos.y - ind.getPos().y) < getSizA() + ind.getSizA();
	}

	private boolean hungry() {
		return health < getHeaL();
	}
	
	private boolean criticallyHungry() {
		return health < getHecL();
	}

	public void setVecDir(double dt) {
		dirVec.setStartX(pos.x);
		dirVec.setStartY(pos.y);
		dirVec.setEndX(pos.x + dir.x * getSigA());
		dirVec.setEndY(pos.y + dir.y * getSigA());
		dirVec.setStrokeWidth(2);
		dirVec.setStroke(color);
		dirVec.setOpacity(0.5 * health / maxHealth);
	}

	protected void setShape() {
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		shape.setFill(color);
		shape.setRadius(getSizA());
		shape.setOpacity(health / maxHealth);
	}

	private void setSight() {
		sight.setCenterX(pos.x);
		sight.setCenterY(pos.y);
		sight.setFill(color);
		sight.setRadius(getSigA());
		sight.setOpacity(0.2 * health / maxHealth);
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
	public Individual getGoal() {
		return goal;
	}

	public void setGoal(Individual prey) {
		this.goal = prey;
	}

	public void addRun(Individual ind) {
		run.add(ind);
	}

	public Line getDirVec() {
		return dirVec;
	}
}
