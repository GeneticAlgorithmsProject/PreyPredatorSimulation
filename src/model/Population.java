package model;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.layout.Pane;

import model.Individual;

public class Population {

	protected List<Individual> population;
	protected Population adversary;
	protected Population prey;

	protected Random rnd;

	protected int count;
	protected double width, height;

	public Population(int count, Pane pane) {
		population = new LinkedList<>();
		rnd = new Random();
		this.count = count;
		width = pane.getWidth();
		height = pane.getHeight();
	}

	public void init() {
		for (int i = 0; i < count; ++i) {
			Individual ind = new Individual(width * rnd.nextDouble(), height * rnd.nextDouble());
			population.add(ind);
		}
	}

	public void initPositions() {
		for (int i = 0; i < count; ++i) 
			population.get(i).setPos(new Vector2d(rnd.nextDouble() * width,rnd.nextDouble() * height));
	}

	public void move() {
	
	}

	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);		
		eat();		
	}

	protected void findFood() {
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
			
			Individual pr = prey.getPopulation().get(index);
			ind.setDirFood(Vector2d.normedDiff(pr.getPos(), ind.getPos()));
		}
	}

	protected void eat() {
		for (Individual ind : population) {
			List<Individual> rInd = new LinkedList<>();
			for (Individual pr : prey.getPopulation()) {
				if (Math.abs(ind.getPos().x - pr.getPos().x) < pr.getSize()
						&& Math.abs(ind.getPos().y - pr.getPos().y) < pr.getSize()) {
					ind.incrementHealth();
					rInd.add(pr);
				}
			}
			prey.getPopulation().removeAll(rInd);
		}
	}

	protected void boundaryConditions() {
		for (Individual ind : population)
			ind.getPos().fit(0, 0, width, height);
	}

	protected void moveInds() {
		for (Individual ind : population)
			ind.move();
	}

	protected void death() {
		List<Individual> rInd = new LinkedList<>();
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			if (ind.getHealth() <= 0) {
				rInd.add(ind);
			}
		}
		population.removeAll(rInd);
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
