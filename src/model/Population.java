package model;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
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

	public Population(int count, Pane pane) {
		population = new LinkedList<>();
		group = new Group();
		rnd = new Random();
		this.count = count;
		width = pane.getWidth();
		height = pane.getHeight();
	}

	public void init() {
		for (int i = 0; i < count; ++i) {
			Individual ind = new Individual(width * rnd.nextDouble(), height * rnd.nextDouble());
			population.add(ind);
			group.getChildren().add(ind.getCircle());
		}
	}

	public void initPositions() {
		for (int i = 0; i < count; ++i) 
			population.get(i).setPos(new Vector2d(rnd.nextDouble() * width,rnd.nextDouble() * height));
	}

	public void update() {
		eat();
	}

	public void move() {
	
	}

	public void draw(Lighting lighting) {
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			Circle node = (Circle) group.getChildren().get(i);
			node.setCenterX(ind.getPos().x);
			node.setCenterY(ind.getPos().y);
			node.setFill(ind.getColor());
			node.setEffect(lighting);
			node.setRadius(ind.getSize());
		}
	}

	public void time(double dt) {
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			ind.setHealth(ind.getHealth() - dt);
			((Circle) group.getChildren().get(i)).setOpacity(ind.getHealth() / ind.getMaxHealth());
		}
	}

	protected void findFood() {
		for (Individual ind : population) {
			double minDistance = Double.MAX_VALUE;
			int index = 0;
			for (int i = 0; i < prey.getPopulation().size(); ++i) {
				Individual pr = prey.getPopulation().get(i);
				double dist = ind.distance(pr.getPos().x, pr.getPos().y);
				if (dist < minDistance) {
					minDistance = dist;
					index = i;
				}
			}
			if (prey.getPopulation().size() < 1)
				return;
			
			Individual pr = prey.getPopulation().get(index);
			double dirX = (pr.getPos().x - ind.getPos().x) / Math.abs(ind.getPos().x - pr.getPos().x);
			double dirY = (pr.getPos().y - ind.getPos().y) / Math.abs(ind.getPos().y - pr.getPos().y);
			ind.setDirFood(new Vector2d(dirX, dirY));
		}
	}

	protected void eat() {
		for (Individual ind : population) {
			int index = 0;
			List<Individual> rInd = new LinkedList<>();
			List<Node> rNode = new LinkedList<>();
			for (Individual pr : prey.getPopulation()) {
				if (Math.abs(ind.getPos().x - pr.getPos().x) < pr.getSize()
						&& Math.abs(ind.getPos().y - pr.getPos().y) < pr.getSize()) {
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
		for (Individual ind : population)
			ind.getPos().Fit(0, 0, width, height);
	}

	protected void moveInds() {
		for (Individual ind : population)
			ind.move();
	}

	protected void death() {
		List<Individual> rInd = new LinkedList<>();
		List<Node> rNode = new LinkedList<>();
		for (int i = 0; i < population.size(); ++i) {
			Individual ind = population.get(i);
			if (ind.getHealth() <= 0) {
				rInd.add(ind);
				rNode.add(group.getChildren().get(i));
			}
		}
		population.removeAll(rInd);
		group.getChildren().removeAll(rNode);
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
