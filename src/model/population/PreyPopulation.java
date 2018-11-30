package model.population;

import java.util.Random;

import model.Simulation;
import model.individual.Individual;
import model.individual.Prey;
import utils.Vector2d;

public class PreyPopulation extends Population {

	private Vector2d[] initPositions;

	public PreyPopulation(int count) {
		super(count);
		name = "PreyPopulation";
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
			ind.reset();
		}
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
