package model.population;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import model.Simulation;
import model.individual.Individual;
import utils.Vector2d;

public class Population {

	protected List<Individual> population;
	protected List<Individual> dead;
	protected Population run;
	protected Population goal;

	protected int count;

	protected String name;

	public Population(int count) {
		population = new LinkedList<>();
		dead = new LinkedList<>();
		this.count = count;
		name = "Default";
	}

	public void reset() {
		Random rnd = new Random();
		for (Individual ind : population) {
			ind.setPos(new Vector2d(rnd.nextDouble() * Simulation.width, rnd.nextDouble() * Simulation.height));
			ind.maxHealth();
		}
	}

	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Individual(Math.min(Simulation.width, Simulation.height)));
	}

	public void move(double dt) {
		moveInds(dt);
	}

	public void die(double dt) {

	}

	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);
	}

	public void updateSpecial(double dt) {

	}

	public double getAverageAge() {
		double averageAge = 0;
		for (Individual ind : population)
			averageAge += ind.getAge();
		return averageAge / population.size();
	}

	public double getMaxAge() {
		double maxAge = 0;
		for (Individual ind : population)
			if (ind.getAge() > maxAge)
				maxAge = ind.getAge();
		return maxAge;
	}

	protected void findGoal() {
		for (Individual ind : population) {
			double minDistance = Double.MAX_VALUE;
			int index = 0;
			for (int i = 0; i < goal.getSize(); ++i) {
				Individual pr = goal.get(i);
				double dist = Vector2d.dist(ind.getPos(), pr.getPos());
				if (dist < minDistance) {
					minDistance = dist;
					index = i;
				}
			}
			if (goal.getSize() < 1)
				return;
			ind.setGoal(goal.get(index));
		}
	}

	protected void eat(double dt) {
		for (Individual ind : population)
			for (Individual pr : goal.getPopulation())
				if (ind.overlaps(pr)) {
					ind.incrementHealth(dt);
					pr.death();
				}
	}

	protected void death() {
		for (Individual ind : population)
			if (ind.isDead())
				dead.add(ind);
		population.removeAll(dead);
	}

	public void joinIndLists() {
		population.addAll(dead);
		dead.clear();
	}

	public void drawOn(Pane pane) {
		for (Individual ind : population) {
			pane.getChildren().add(ind.getShape());
			pane.getChildren().add(ind.getSight());
			pane.getChildren().add(ind.getDirVec());
		}
	}

	protected void moveInds(double dt) {
		for (Individual ind : population)
			ind.move(dt);
	}

	public Individual get(int index) {
		return population.get(index);
	}

	public int getSize() {
		return population.size();
	}

	public void setGoal(Population goal) {
		this.goal = goal;
	}

	public void setRun(Population run) {
		this.run = run;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setPopulation(List<Individual> population) {
		this.population = population;
	}

	public List<Individual> getPopulation() {
		return population;
	}

}
