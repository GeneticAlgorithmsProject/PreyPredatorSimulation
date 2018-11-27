package model.population;

import java.util.Random;

import model.Simulation;
import model.individual.Food;
import model.individual.Individual;
import utils.Vector2d;

public class FoodPopulation extends Population {

	public FoodPopulation(int count) {
		super(count);
		name = "FoodPopulation";
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Food(Math.min(Simulation.width, Simulation.height)));
	}

	@Override
	public void move(double dt) {

	}

	@Override
	public void updateSpecial(double dt) {
		death();
		dead.clear();
		Random rnd = new Random();
		if (population.size() < count)
			if (rnd.nextDouble() < 0.1)
				population.add(new Food(Math.min(Simulation.width, Simulation.height)));
	}

}
