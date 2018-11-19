package model.individual;

import javafx.scene.paint.Color;

public class Prey extends Individual implements Live{

	public Prey() {
		super();
		color = new Color(0, 0, 1, 1);
		name = "Prey";
	}

	public Prey(double x, double y) {
		super(x, y);
		color = new Color(0, 0, 1, 1);
		name = "Prey";
	}
	
	public Prey(double R) {
		super(R);
		color = new Color(0, 0, 1, 1);
		name = "Prey";
	}

	@Override
	public void move(double dt) {
		randomWalk(dt);
//		oscillate();
//		moveToGoal();
//		runAway();
//		dir.norm();
		dir.mult(speed*dt);
		pos.add(dir);
	}
	
}
