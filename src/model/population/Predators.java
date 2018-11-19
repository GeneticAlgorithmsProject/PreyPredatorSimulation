package model.population;

import java.util.Random;

import application.Simulation;
import model.individual.Individual;
import model.individual.Predator;
import utils.Vector2d;

public class Predators extends Population {

	public Predators(int count) {
		super(count);
		name = "Predator";
	}
	
	public void initPositions() {
		Random rnd = new Random();
		for(Individual ind : population) 
			ind.setPos(new Vector2d(rnd.nextDouble() * Simulation.width/8, rnd.nextDouble()*Simulation.height/8));
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Predator(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
		findPrey();
		boundaryConditions();
		moveInds(dt);
	}

	@Override
	public void updateSpecial(double dt) {
		eat();
		death();
	}

}
