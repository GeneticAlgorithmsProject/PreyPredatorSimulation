package application;

import ga.GeneticAlgorithm;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.individual.Individual;
import model.population.Food;
import model.population.Population;
import model.population.Predators;
import model.population.Preys;

public class Simulation {

	private Population food, preys, predators;
	public static int foodCount = 50, preyCount = 20, predatorCount = 10;

	private Pane pane;
	private Circle circle;

	public static double DT = 10;
	public static double width, height;
	public static double timeMultiplier;
	public static boolean closed = false;

	private TextField timer;

	private XYChart.Series<Number, Number> preyBest;
	private XYChart.Series<Number, Number> preyAverage;

	private XYChart.Series<Number, Number> predatorBest;
	private XYChart.Series<Number, Number> predatorAverage;

	private int generation;

	public Simulation(Pane pane, LineChart<Number, Number> preyChart, LineChart<Number, Number> predatorChart,
			TextField timer) {
		this.pane = pane;
		this.timer = timer;
		width = pane.getWidth();
		height = pane.getHeight();
	
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

		circle = new Circle(width / 2, height / 2, Math.min(width, height) / 2);
	}

	public void init() {
		food = new Food(foodCount);
		preys = new Preys(preyCount);
		predators = new Predators(predatorCount);

		food.init();

		preys.init();
		preys.setPrey(food);
//		preys.setAdversary(predators);

		predators.init();
		predators.setPrey(preys);
	}

	public void animate() {
		new AnimationTimer() {
			long startTime = -1, currTime = -1;
			double time = 0;

			private void check(long now) {
				if (pause()) {
					startTime = now;
					timer(time = 0);
					join();
					addData();
//					GeneticAlgorithm ga = new GeneticAlgorithm(2, 1, 0.01);
//					ga.createNewGeneration(preys);
//					ga.createNewGeneration(predators);
					init();
					initPositions();
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
				double dt = deltaNanos / 1.0e9 * timeMultiplier;
				check(now);
				move(dt);
				update(dt);
				draw();
				time += dt;
				timer(time);
			}
		}.start();
	}

	private void timer(double time) {
		timer.setText(String.format("t=%.2fs", time));
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

	private void draw() {
		pane.getChildren().clear();
		preys.drawOn(pane);
		predators.drawOn(pane);
		pane.getChildren().add(circle);
	}

	private void move(double dt) {
		predators.move(dt);
		preys.move(dt);
	}

	private void update(double dt) {
		width = pane.getWidth();
		height = pane.getHeight();
		circle.setCenterX(width / 2);
		circle.setCenterY(height / 2);
		circle.setRadius(Math.min(width, height) / 2);
		circle.setOpacity(0.05);
		food.update(dt);
		preys.update(dt);
		predators.update(dt);
		food.updateSpecial(dt);
		preys.updateSpecial(dt);
		predators.updateSpecial(dt);
	}
	
	public void initPositions() {
		food.initPositions();
		preys.initPositions();
		predators.initPositions();
	}

	private boolean pause() {
		return food.getSize() < 1 || preys.getSize() < 1
				|| predators.getSize() < 1;
	}
}
