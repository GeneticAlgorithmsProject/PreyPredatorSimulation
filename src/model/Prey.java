package model;

import java.util.List;
import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Prey extends Population{

	Population adversary;
	
	public Prey(int count, Pane pane) {
		super(count, pane);
		color = new Color(0,0,1,1);
	}
	
	@Override
	public void move() {
		findFood();
		calculatePredatorsField();
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

	@Override
	public void setAdversary(Population adversary) {
		this.adversary = adversary;
	}
	
	private void calculatePredatorsField() {
		for(Individual ind : population) {
			double dirX = ind.getDirX();
			double dirY = ind.getDirY();
			int size = adversary.getPopulation().size();
			for (int i = 0; i < size; ++i) {
				Individual adv = adversary.getPopulation().get(i);
				dirX -= (adv.getX() - ind.getX())/Math.abs(adv.getX()-ind.getX())/size;
				dirY -= (adv.getY() - ind.getY())/Math.abs(adv.getY()-ind.getY())/size;
			}
			ind.setDirX(dirX);
			ind.setDirY(dirY);
		}
	}
	
}
