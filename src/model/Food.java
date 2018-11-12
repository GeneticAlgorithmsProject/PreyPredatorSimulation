package model;

import javafx.scene.layout.Pane;

public class Food extends Population{
	
	public Food(int count, Pane pane) {
		super(count,pane);
	}
	
	@Override
	public void move(double dt) {
		
	}
	
	@Override
	public void update(double dt) {
		for (Individual ind : population)
			ind.update(dt);		
	
		if(population.size() < count) {
			double x = rnd.nextDouble()*width;
			double y = rnd.nextDouble()*height;
			Individual ind = new Individual(x,y);
			population.add(ind);
		}	
	}
	
}
