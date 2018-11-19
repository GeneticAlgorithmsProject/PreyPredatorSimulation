package model.population;

import application.Simulation;
import javafx.scene.layout.Pane;
import model.individual.Food;
import model.individual.Individual;
import model.individual.Predator;

public class FoodPopulation extends Population{
	
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
		if(population.size() < count) {
			population.add(new Food(Math.min(Simulation.width, Simulation.height)));
		}	
	}
	
}
