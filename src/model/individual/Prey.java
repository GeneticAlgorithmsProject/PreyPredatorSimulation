package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

public class Prey extends Individual implements Live {

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
		moveToGoal(dt);
		runAway(dt);
		hunger(dt);
		boundaryConditions();
		dir.norm();
		pos.add(Vector2d.mult(dir, speed * dt));
	}

}
