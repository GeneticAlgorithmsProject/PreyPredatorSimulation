package model.individual;

import javafx.scene.paint.Color;
import utils.Vector2d;

public class Predator extends Individual implements Live{

	public Predator() {
		super();
		color = new Color(1, 0, 0, 1);	
		name = "Predator";
		genotype[Gene.DGOM.ordinal()] = 1.;
	}

	public Predator(double x, double y) {
		super(x, y);
		color = new Color(1, 0, 0, 1);
		name = "Predator";
		genotype[Gene.DGOM.ordinal()] = 1.;
	}
	
	public Predator(double R) {
		super(R);
		color = new Color(1, 0, 0, 1);
		name = "Predator";
		genotype[Gene.DGOM.ordinal()] = 1.;
	}

	@Override
	public void move(double dt) {	
		randomWalk(dt);
		moveToGoal(dt);
		hunger(dt);
		boundaryConditions();
		dir.norm();
		pos.add(Vector2d.mult(dir, speed * dt));
	}

}
