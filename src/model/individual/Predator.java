package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

public class Predator extends Individual{

	public Predator() {
		super();
		dirFood = new Vector2d();
		color = new Color(1, 0, 0, 1);	
	}

	public Predator(double x, double y) {
		super(x, y);
		dirFood = new Vector2d();
		color = new Color(1, 0, 0, 1);	
	}
	
	public Predator(double R) {
		super(R);
		dirFood = new Vector2d();
		color = new Color(1, 0, 0, 1);
	}

	@Override
	public void move(double dt) {
		dirFood = Vector2d.normedDiff(prey.getPos(), pos);
		
		dirFood.mult(speed);
		pos.add(dirFood);
	}
}
