package model.population;

import java.util.Random;

import model.Simulation;
import model.individual.Individual;
import model.individual.Prey;
import utils.Vector2d;

public class PreyPopulation extends Population {

	public PreyPopulation(int count) {
		super(count);
		name = "PreyPopulation";
	}

	public void initPositions() {
		Random rnd = new Random();
		for (Individual ind : population)
			ind.setPos(new Vector2d(Simulation.width * (1 - rnd.nextDouble() / 2),
					Simulation.height * (1 - rnd.nextDouble() / 2)));
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Prey(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {
		findGoal();
		calculatePredatorsField();
		moveInds(dt);
	}

	@Override
	public void updateSpecial(double dt) {
		eat(dt);
		death();
	}

	private void calculatePredatorsField() {
		for (Individual ind : population) {
			for (Individual r : run.getPopulation()) {
				if (Vector2d.dist(r.getPos(), ind.getPos()) < ind.getSigA()) {
					ind.addRun(r);
				}
			}
		}
	}
}
