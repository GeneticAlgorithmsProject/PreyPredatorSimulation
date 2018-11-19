package model.population;

import java.util.Random;

import model.Simulation;
import model.individual.Individual;
import model.individual.Predator;
import utils.Vector2d;

public class PredatorPopulation extends Population {

	public PredatorPopulation(int count) {
		super(count);
		name = "PredatorPopulation";
	}

	public void initPositions() {
		Random rnd = new Random();
		for (Individual ind : population)
			ind.setPos(new Vector2d(rnd.nextDouble() * Simulation.width / 3, rnd.nextDouble() * Simulation.height / 3));
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Predator(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
		boundaryConditions();
		findGoal();
		moveInds(dt);
		boundaryConditions();
	}

	@Override
	public void updateSpecial(double dt) {
		eat(dt);
		death();
	}

}
