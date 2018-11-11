package model;

import javafx.scene.layout.Pane;

public class Food extends Population{
	
	public Food(int count, Pane pane) {
		super(count,pane);
	}
	
	@Override
	public void move() {
		
	}
	
	@Override
	public void update() {
		if(population.size() < count) {
			double x = rnd.nextDouble()*width;
			double y = rnd.nextDouble()*height;
			Individual ind = new Individual(x,y);
			population.add(ind);
			group.getChildren().add(ind.getCircle());
		}	
	}
	
	@Override
	public void time(double dt) {
		
	}

}
