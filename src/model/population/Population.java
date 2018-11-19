package model.population;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import application.Simulation;
import javafx.scene.layout.Pane;
import model.individual.Individual;
import model.individual.Live;
import utils.Vector2d;

public class Population implements Live{

	protected List<Individual> population;
	protected List<Individual> dead;
	protected Population adversary;
	protected Population prey;

	protected int count;

	protected String name;

	public Population(int count) {
		population = new LinkedList<>();
		dead = new LinkedList<>();
		this.count = count;
		name = "Default";
	}
	
	public void initPositions() {
		Random rnd = new Random();
		for(Individual ind : population) 
			ind.setPos(new Vector2d(rnd.nextDouble() * Simulation.width, rnd.nextDouble()*Simulation.height));
	}	

	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Individual(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
		for (Individual ind : population)
			ind.move(dt);
	}

	@Override
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

	protected void findPrey() {
		for (Individual ind : population) {
			double minDistance = Double.MAX_VALUE;
			int index = 0;
			for (int i = 0; i < prey.getSize(); ++i) {
				Individual pr = prey.get(i);
				double dist = Vector2d.dist(ind.getPos(), pr.getPos());
				if (dist < minDistance) {
					minDistance = dist;
					index = i;
				}
			}
			if (prey.getSize() < 1)
				return;
			ind.setGoal(prey.get(index));
		}
	}

	protected void eat() {
//		for (Individual ind : population)
//			for (Individual pr : prey.getPopulation())
//				if (Math.abs(ind.getPos().x - pr.getPos().x) < pr.getGenotype().getSize()
//						&& Math.abs(ind.getPos().y - pr.getPos().y) < pr.getGenotype().getSize()) {
//					ind.incrementHealth();
//					pr.death();
//				}
	}

	protected void death() {
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			if (ind.isDead()) {
				dead.add(ind);
			}
		}
		population.removeAll(dead);
	}

	public void joinIndLists() {
		population.addAll(dead);
		dead.clear();
	}
	
	public void drawOn(Pane pane) {
		for(Individual ind : population) {
			pane.getChildren().add(ind.getShape());
			pane.getChildren().add(ind.getSight());
		}
	}

	protected void boundaryConditions() {
		for (Individual ind : population)
			ind.getPos().fit();
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

	public void setPrey(Population prey) {
		this.prey = prey;
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
	
	
}
