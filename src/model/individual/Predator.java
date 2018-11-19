package model.individual;

import javafx.scene.paint.Color;

public class Predator extends Individual implements Live{

	public Predator() {
		super();
		color = new Color(1, 0, 0, 1);	
		name = "Predator";
	}

	public Predator(double x, double y) {
		super(x, y);
		color = new Color(1, 0, 0, 1);
		name = "Predator";
	}
	
	public Predator(double R) {
		super(R);
		color = new Color(1, 0, 0, 1);
		name = "Predator";
	}

	@Override
	public void move(double dt) {	
		randomWalk(dt);
//		oscillate();
//		moveToGoal();
//		hunger(dir);
//		dir.norm();
		dir.mult(speed*dt);
		pos.add(dir);
	}

}
