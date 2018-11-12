package application;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import model.Food;
import model.Individual;
import model.Population;
import model.Predators;
import model.Preys;

public class Simulation {

	private Population food, preys, predators;
	private int foodCount, preyCount, predatorCount;

	private Pane pane;

	private Light.Spot light;
	public static Lighting lighting;

	public static double DT = 10;
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
	
	public void animate() {
		new AnimationTimer() {
			long startTime = -1;

			private void check() {
				if (pause()) {
					this.stop();
				}
			}

			@Override
			public void handle(long now) {
				if (startTime == -1) {
					startTime = now;
				}
				long deltaNanos = now - startTime;
				startTime = now;
				double dt = deltaNanos / 1.0e9;
//				double time = (now - startTime) / 1E9d;
				check();
				reset();
				move(dt);
				update(dt);
				draw();
			}
		}.start();
	}

	public void reset() {
		this.pane.getChildren().clear();
	}

	public void draw() {
		for (Individual ind : food.getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : preys.getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : predators.getPopulation())
			pane.getChildren().add(ind.getCircle());

	}

	public void move(double dt) {
		predators.move(dt);
		preys.move(dt);
	}

	public void update(double dt) {
		food.update(dt);
		preys.update(dt);
		predators.update(dt);
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
