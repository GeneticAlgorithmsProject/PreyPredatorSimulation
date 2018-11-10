package model;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import model.Individual;

public class Population {

	protected List<Individual> population;
	protected Population adversary;
	protected Population prey;

	protected Group group;
	protected Random rnd;

	protected int count;
	protected double width, height;

	protected Color color;

	public Population(int count, Pane pane) {
		population = new LinkedList<>();
		group = new Group();
		rnd = new Random();
		this.count = count;
		width = pane.getWidth();
		height = pane.getHeight();
		color = new Color(1, 1, 1, 1);
	}

	public void init() {
		for (int i = 0; i < count; ++i) {
			Individual ind = new Individual(width*rnd.nextDouble(), height*rnd.nextDouble());
			population.add(ind);
			group.getChildren().add(ind.getCircle());
		}
	}

	public void initPositions() {
		for (int i = 0; i < count; ++i) {
			population.get(i).setX(rnd.nextDouble() * width);
			population.get(i).setY(rnd.nextDouble() * height);
		}
	}

	public void update() {
		eat();
	}

	public void move() {
		if (prey.getPopulation().size() < 1 || population.size() < 1) {
			return;
		}
	}
	
	public void draw(Lighting lighting) {
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			Circle node = (Circle) group.getChildren().get(i);
			node.setCenterX(ind.getX());
			node.setCenterY(ind.getY());
			node.setFill(color);
			node.setEffect(lighting);
			node.setRadius(ind.getSize());
		}
		setColor();
	}

	public void time(double dt) {
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			ind.setHealth(ind.getHealth() - dt);
			((Circle) group.getChildren().get(i)).setOpacity(ind.getHealth() / ind.getMaxHealth());
		}
	}

	protected void findFood() {
		for(Individual ind : population) {
			double minDistance = Double.MAX_VALUE;
			int index = 0;
			for(int i = 0; i < prey.getPopulation().size(); ++i) {
			Individual pr = prey.getPopulation().get(i);
			double dist = ind.distance(pr.getX(), pr.getY());
				if (dist < minDistance) {
					minDistance = dist;
					index = i;
				}
			}
			if(prey.getPopulation().size() < 1)
				return;
			Individual pr = prey.getPopulation().get(index);
			double dirX = ind.getDirX();
			dirX = (pr.getX() - ind.getX()) / Math.abs(ind.getX() - pr.getX());
			double dirY =  ind.getDirY();
			dirY = (pr.getY() - ind.getY()) / Math.abs(ind.getY() - pr.getY());
			ind.setDirX(dirX);
			ind.setDirY(dirY);
		}
	}
	
	protected void eat() {
		for (Individual ind : population) {
			int index = 0;
			List<Individual> rInd = new LinkedList<>();
			List<Node> rNode = new LinkedList<>();
			for (Individual pr : prey.getPopulation()) {
				if (Math.abs(ind.getX() - pr.getX()) < pr.getSize()
						&& Math.abs(ind.getY() - pr.getY()) < pr.getSize()) {
					ind.setHealth(ind.getHealth() + ind.getAddHealth());
					rNode.add(prey.getGroup().getChildren().get(index));
					rInd.add(pr);
				}
			}
			prey.getPopulation().removeAll(rInd);
			prey.getGroup().getChildren().removeAll(rNode);
		}
	}
	
	protected void boundaryConditions() {
		for (Individual ind : population) {
			ind.move();
			if (ind.getX() > width) {
				ind.setX(width);
			} else if (ind.getX() < 0) {
				ind.setX(0);
			}

			if (ind.getY() > height) {
				ind.setY(height);
			} else if (ind.getY() < 0) {
				ind.setY(0);
			}
		}	
	}
	
	protected void moveInds() {
		for (Individual ind : population) 
			ind.move();
	}
	
	protected void death() {
		List<Individual> rInd= new LinkedList<>();
		List<Node> rNode = new LinkedList<>();
		for(int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			if(ind.getHealth() <= 0) {
				rInd.add(ind);
				rNode.add(group.getChildren().get(i));
			}	
		}
		population.removeAll(rInd);
		group.getChildren().removeAll(rNode);
	}
	
	protected void setColor() {
		
	}

	public void setPrey(Population prey) {
		this.prey = prey;
	}

	public List<Individual> getPopulation() {
		return population;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Group getGroup() {
		return group;
	}

	public void setAdversary(Population predators) {

	}
}
