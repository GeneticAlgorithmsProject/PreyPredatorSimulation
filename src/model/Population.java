package model;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.layout.Pane;

import model.Individual;
import utils.Vector2d;

public class Population {

	protected List<Individual> population;
	protected Population adversary;
	protected Population prey;

	protected List<Double> lifespan;

	protected Random rnd;

	protected int count;
	protected double width, height;

	protected String name;

	public Population(int count, Pane pane) {
		population = new LinkedList<>();
		rnd = new Random();
		this.count = count;
		width = pane.getWidth();
		height = pane.getHeight();
		lifespan = new LinkedList<>();
		name = "Default";
	}

	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Individual(Math.min(width, height)));
	}

	public void initPositions() {
		for (int i = 0; i < count; ++i)
			population.get(i).setPos(new Vector2d(rnd.nextDouble() * width, rnd.nextDouble() * height));
	}

	public void move(double dt) {
		for (Individual ind : population)
			ind.move(dt);
	}

	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);
	}
	
	public void updateSpecial(double dt) {
		
	}

	public double getAverageAge() {
		double averageAge = 0;
		for (Double a : lifespan)
			averageAge += a;
		return averageAge / population.size();
	}

	public double getMaxAge() {
		return Collections.max(lifespan);
	}

	protected void findPrey() {
		for (Individual ind : population) {
			double minDistance = Double.MAX_VALUE;
			int index = 0;
			for (int i = 0; i < prey.getPopulation().size(); ++i) {
				Individual pr = prey.getPopulation().get(i);
				double dist = Vector2d.dist(ind.getPos(), pr.getPos());
				if (dist < minDistance) {
					minDistance = dist;
					index = i;
				}
			}
			if (prey.getPopulation().size() < 1)
				return;
			ind.setPrey(prey.getPopulation().get(index));
		}
	}

	protected void eat() {
		for (Individual ind : population)
			for (Individual pr : prey.getPopulation())
				if (Math.abs(ind.getPos().x - pr.getPos().x) < pr.getGene().getSize()
						&& Math.abs(ind.getPos().y - pr.getPos().y) < pr.getGene().getSize()) {
					ind.incrementHealth();
					pr.death();
				}
	}

	public void setLifespan(double time) {
		for (int i = 0; i < population.size(); i++) {
			lifespan.add(time);
		}
	}

	protected void death() {
		List<Individual> rInd = new LinkedList<>();
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			if (ind.isDead()) {
				lifespan.add(ind.getAge());
				rInd.add(ind);
			}
		}
		population.removeAll(rInd);
	}

	protected void boundaryConditions() {
		for (Individual ind : population)
			ind.getPos().fit(0, 0, width, height);
	}

	protected void moveInds(double dt) {
		for (Individual ind : population)
			ind.move(dt);
	}

	public void setPrey(Population prey) {
		this.prey = prey;
	}

	public List<Individual> getPopulation() {
		return population;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setAdversary(Population predators) {

	}
}
