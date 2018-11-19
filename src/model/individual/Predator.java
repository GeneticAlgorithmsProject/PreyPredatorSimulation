package model.individual;

import javafx.scene.paint.Color;

public class Predator extends Individual implements Live{

	public Predator() {
		super();
		color = new Color(1, 0, 0, 1);	
	}

	public Predator(double x, double y) {
		super(x, y);
		color = new Color(1, 0, 0, 1);	
	}
	
	public Predator(double R) {
		super(R);
		color = new Color(1, 0, 0, 1);
	}

	@Override
	public void move(double dt) {	
		//		dirFood = Vector2d.diff(prey.getPos(), pos);
//		dirFood.norm();
//		dirFood.mult(speed);
		pos.add(getDirGo());
	}

	@Override
	public void die(double dt) {
		// TODO Auto-generated method stub
		
	}
}
