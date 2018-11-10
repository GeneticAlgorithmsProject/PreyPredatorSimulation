package model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food extends Population{
	
	public Food(int count, Pane pane) {
		super(count,pane);
		color = new Color(0,1,0,1);
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
			group.getChildren().add(new Circle(ind.getX(),ind.getY(),ind.getSize()));
		}	
	}
	
	@Override
	public void setColor() {
		for(Node n : group.getChildren()) {
			Circle c = (Circle)n;
			c.setFill(color);
		}
	}
	
	@Override
	public void time(double dt) {
		
	}

}
