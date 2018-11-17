package model.population;

import javafx.scene.layout.Pane;
import model.individual.Predator;

public class Predators extends Population {

	public Predators(int count, Pane pane) {
		super(count, pane);
		name = "Predator";
	}

	@Override
	public void init() {
		for (int i = 0; i < count; ++i)
			population.add(new Predator(Math.min(width, height)));
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
