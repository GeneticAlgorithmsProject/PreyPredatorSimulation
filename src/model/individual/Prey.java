package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

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
		movement[Move.dGo.ordinal()] = Vector2d.normedDiff(goal.getPos(), pos);
		movement[Move.dGo.ordinal()].mult(getDGoM());
		dir.add(movement[Move.dGo.ordinal()]);
//		movement[Move.dEsc.ordinal()] .mult(getDEscM());
//		
//		Vector2d dir = Vector2d.add(movement[Move.dGo.ordinal()] , movement[Move.dEsc.ordinal()] );
//		oscillate(dir);
//		hunger(dir);
		dir.norm();
		randomWalk(dt);
		dir.mult(speed*dt*100);
		pos.add(dir);
	}
	
	@Override
	public void die(double dt) {
		
	}
	
}
