package model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Predator extends Population{

	public Predator(int count, Pane pane) {
		super(count,pane);
		color = new Color(1, 0, 0, 1);	
	}
	
	@Override
	public void move() {
		findFood();
		boundaryConditions();
		moveInds();		
	}
	
	@Override
	public void update() {
		eat();
		death();
	}
	
	@Override
	public void setColor() {
		for(Node n : group.getChildren()) {
			Circle c = (Circle)n;
			c.setFill(color);
		}
	}

}
