package model.population;

import java.util.Random;

import model.Simulation;
import model.individual.Individual;
import model.individual.Predator;
import utils.Vector2d;

public class PredatorPopulation extends Population {

	private Vector2d[] initPositions;

	public PredatorPopulation(int count) {
		super(count);
		name = "PredatorPopulation";
		initPositions = new Vector2d[count];
		Random rnd = new Random();
		for (int i = 0; i < count; i++)
			initPositions[i] = new Vector2d(rnd.nextDouble() * Simulation.width, rnd.nextDouble() * Simulation.height);
	}

	@Override
	public void reset() {
		for (int i = 0; i < count; i++) {
			Individual ind = population.get(i);
			ind.setPos(initPositions[i]);
			ind.maxHealth();
		}
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Predator(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
		findGoal();
		moveInds(dt);
	}

	@Override
	public void updateSpecial(double dt) {
		eat(dt);
		death();
	}

}
