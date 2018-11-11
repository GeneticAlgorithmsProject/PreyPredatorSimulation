package application;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import model.Food;
import model.Population;
import model.Predators;
import model.Preys;

public class Simulation {

	private Population food, preys, predators;
	private int foodCount, preyCount, predatorCount;

	private Pane pane;

	private Light.Spot light;
	public static Lighting lighting;

	public static double DT = 0.01;
	public static double width, height;

	public Simulation(Pane pane) {
		this.pane = pane;
		width = pane.getWidth();
		height = pane.getHeight();
		foodCount = 10;
		preyCount = 5;
		predatorCount = 2;
	}

	public void init() {
		food = new Food(foodCount, pane);
		preys = new Preys(preyCount, pane);
		predators = new Predators(predatorCount, pane);

		food.init();

		preys.init();
		preys.setPrey(food);
		preys.setAdversary(predators);

		predators.init();
		predators.setPrey(preys);
	}

	public void reset() {

	}

	public void move() {
		predators.move();
		preys.move();
	}

	public void time(double dt) {
		food.time(dt);
		preys.time(dt);
		predators.time(dt);
	}

	public void update() {
		food.update();
		preys.update();
		predators.update();
	}

	public void draw(Lighting lighting) {
		food.draw(lighting);
		preys.draw(lighting);
		predators.draw(lighting);
	}

	public boolean pause() {
		return food.getPopulation().size() < 1 || preys.getPopulation().size() < 1
				|| predators.getPopulation().size() < 1;
	}

	public void initLighting() {
		light = new Light.Spot();
		light.setX(0);
		light.setY(0);
		light.setZ(100);
		light.setPointsAtX(width);
		light.setPointsAtY(height);
		light.setPointsAtZ(-50);
		light.setSpecularExponent(5.);
		lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(1.);
	}

	public Population getFood() {
		return food;
	}

	public Population getPreys() {
		return preys;
	}

	public Population getPredators() {
		return predators;
	}

}
