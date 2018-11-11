package model;

import javafx.scene.layout.Pane;

public class Predators extends Population{

	public Predators(int count, Pane pane) {
		super(count,pane);
	}
	
	@Override
	public void init() {
		for (int i = 0; i < count; ++i) {
			Individual ind = new Predator(width * rnd.nextDouble(), height * rnd.nextDouble());
			population.add(ind);
		}
	}
	
	@Override
	public void move() {
		findFood();
		boundaryConditions();
		moveInds();		
	}
	
	@Override
	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);		
		eat();
		death();
	}
}
