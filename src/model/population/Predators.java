package model.population;

import application.Simulation;
import model.individual.Predator;

public class Predators extends Population {

	public Predators(int count) {
		super(count);
		name = "Predator";
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
