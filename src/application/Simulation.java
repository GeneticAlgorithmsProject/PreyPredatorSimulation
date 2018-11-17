package application;

import javafx.animation.AnimationTimer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Food;
import model.Individual;
import model.Population;
import model.Predators;
import model.Preys;

public class Simulation {

	private Population food, preys, predators;
	private int foodCount, preyCount, predatorCount;

	private Pane pane;
	private Circle circle;

	private Light.Spot light;
	public static Lighting lighting;

	public static double DT = 10;
	public static double width, height;

	private TextField timer;

	private LineChart<Number, Number> preyChart;
	private XYChart.Series<Number, Number> preyBest;
	private XYChart.Series<Number, Number> preyAverage;

	private LineChart<Number, Number> predatorChart;
	private XYChart.Series<Number, Number> predatorBest;
	private XYChart.Series<Number, Number> predatorAverage;

	private int generation;
	
	public Simulation(Pane pane, LineChart<Number, Number> preyChart, LineChart<Number, Number> predatorChart,
			TextField timer) {
		this.pane = pane;
		this.preyChart = preyChart;
		this.predatorChart = predatorChart;
		this.timer = timer;
		width = pane.getWidth();
		height = pane.getHeight();
		foodCount = 50;
		preyCount = 20;
		predatorCount = 25;

		generation = 0;
		
		preyBest = new XYChart.Series<>();
		preyBest.setName("best");
		preyAverage = new XYChart.Series<>();
		preyAverage.setName("average");

		predatorBest = new XYChart.Series<>();
		predatorBest.setName("best");
		predatorAverage = new XYChart.Series<>();
		predatorAverage.setName("average");

		preyChart.getData().clear();
		preyChart.getData().add(preyBest);
		preyChart.getData().add(preyAverage);

		predatorChart.getData().clear();
		predatorChart.getData().add(predatorBest);
		predatorChart.getData().add(predatorAverage);
		
		preyBest.getNode().setStyle("-fx-stroke-width: 1; -fx-stroke: blue;");
		preyAverage.getNode().setStyle("-fx-stroke-width: 1; -fx-stroke: grey;");
		predatorBest.getNode().setStyle("-fx-stroke-width: 1; -fx-stroke: red;");
		predatorAverage.getNode().setStyle("-fx-stroke-width: 1; -fx-stroke: grey;");
		
		circle = new Circle(width/2,height/2,Math.min(width,height)/2);
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
			long startTime = -1, currTime = -1;
			double time = -1;

			private void check(long now) {
				if (pause()) {
					startTime = now;
					timer.setText(String.valueOf(0));
					join();
					addData();
					init();
					//GA
				}
			}

			@Override
			public void handle(long now) {
				if (startTime == -1) {
					startTime = now;
					currTime = now;
				}
				long deltaNanos = now - currTime;
				currTime = now;
				double dt = deltaNanos / 1.0e9;
				check(now);
				reset();
				move(dt);
				update(dt);
				draw();
				time = (now - startTime) / 1.0e9;
				timer(time);
			}
		}.start();
	}

	private void timer(double time) {
		timer.setText(String.valueOf(time));
	}

	private void addData() {
		preyBest.getData().add(new XYChart.Data<Number, Number>(generation, preys.getMaxAge()));
		preyAverage.getData().add(new XYChart.Data<Number, Number>(generation, preys.getAverageAge()));
		predatorBest.getData().add(new XYChart.Data<Number, Number>(generation, predators.getMaxAge()));
		predatorAverage.getData().add(new XYChart.Data<Number, Number>(generation, predators.getAverageAge()));
		generation++;
	}
	
	private void join() {
		predators.joinIndLists();
		preys.joinIndLists();
	}

	private void reset() {
		pane.getChildren().clear();
	}

	private void draw() {
		for (Individual ind : food.getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : preys.getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : predators.getPopulation())
			pane.getChildren().add(ind.getCircle());
		pane.getChildren().add(circle);
	}

	private void move(double dt) {
		predators.move(dt);
		preys.move(dt);
	}

	private void update(double dt) {
		width = pane.getWidth();
		height = pane.getHeight();
		circle.setCenterX(width/2);
		circle.setCenterY(height/2);
		circle.setRadius(Math.min(width,height)/2);
		circle.setOpacity(0.05);
		food.update(dt);
		preys.update(dt);
		predators.update(dt);
		food.updateSpecial(dt);
		preys.updateSpecial(dt);
		predators.updateSpecial(dt);
	}

	private boolean pause() {
		return food.getPopulation().size() < 1 || preys.getPopulation().size() < 1
				|| predators.getPopulation().size() < 1;
	}

	private void initLighting() {
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
