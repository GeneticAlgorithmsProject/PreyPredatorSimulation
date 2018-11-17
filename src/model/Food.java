package model;

import javafx.scene.layout.Pane;

public class Food extends Population{
	
	public Food(int count, Pane pane) {
		super(count,pane);
		name = "Food";
	}
	
	@Override
	public void move(double dt) {
		
	}
		
	@Override
	public void updateSpecial(double dt) {
		if(population.size() < count) {
			population.add(new Individual(Math.min(width, height)));
		}	
	}
	
}
