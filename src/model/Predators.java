package model;

import javafx.scene.layout.Pane;

public class Predators extends Population{

	public Predators(int count, Pane pane) {
		super(count,pane);
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
	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);		
		eat();
		death();
	}
}
