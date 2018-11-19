package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

public class Prey extends Individual implements Live{

	public Prey() {
		super();
		color = new Color(0, 0, 1, 1);
	}

	public Prey(double x, double y) {
		super(x, y);
		color = new Color(0, 0, 1, 1);
	}
	
	public Prey(double R) {
		super(R);
		color = new Color(0, 0, 1, 1);
	}

	@Override
	public void move(double dt) {
		movement[Move.dGo.ordinal()] = Vector2d.normedDiff(goal.getPos(), pos);
		movement[Move.dGo.ordinal()].mult(getDirGoMult());
		movement[Move.dEsc.ordinal()] .mult(getDirEscapeMult());
		
		Vector2d dir = Vector2d.add(movement[Move.dGo.ordinal()] , movement[Move.dEsc.ordinal()] );
		oscillate(dir);
		hunger(dir);
		dir.norm();
		dir.mult(speed);
		pos.add(dir);
	}
	
	@Override
	public void die(double dt) {
		// TODO Auto-generated method stub
		
	}
	
}
