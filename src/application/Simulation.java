package application;

import ga.GeneticAlgorithm;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.population.FoodPopulation;
import model.population.Population;
import model.population.PredatorPopulation;
import model.population.PreyPopulation;

public class Simulation {

	private Population[] populations;

	enum PopulationType {
		Food, Prey, Predator
	}

	public static int foodCount = 50, preyCount = 20, predatorCount = 10;

	private Pane pane;

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
	}

	public void init() {
		populations = new Population[PopulationType.values().length];

		populations[PopulationType.Food.ordinal()] = new FoodPopulation(foodCount);
		populations[PopulationType.Predator.ordinal()] = new PredatorPopulation(predatorCount);
		populations[PopulationType.Prey.ordinal()] = new PreyPopulation(preyCount);

		for (Population p : populations)
			p.init();

		populations[PopulationType.Prey.ordinal()].setGoal(populations[PopulationType.Food.ordinal()]);
		populations[PopulationType.Predator.ordinal()].setGoal(populations[PopulationType.Prey.ordinal()]);
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
		preyBest.getData().add(new XYChart.Data<Number, Number>(generation, populations[PopulationType.Prey.ordinal()].getMaxAge()));
		preyAverage.getData().add(new XYChart.Data<Number, Number>(generation, populations[PopulationType.Prey.ordinal()].getAverageAge()));
		predatorBest.getData().add(new XYChart.Data<Number, Number>(generation, populations[PopulationType.Predator.ordinal()].getMaxAge()));
		predatorAverage.getData().add(new XYChart.Data<Number, Number>(generation, populations[PopulationType.Predator.ordinal()].getAverageAge()));
		generation++;
	}

	private void join() {
		for(Population p : populations)
			p.joinIndLists();
	}

	private void draw() {
		pane.getChildren().clear();
		for (Population p : populations)
			p.drawOn(pane);
	}

	private void move(double dt) {
		for (Population p : populations)
			p.move(dt);
	}

	private void update(double dt) {
		width = pane.getWidth();
		height = pane.getHeight();
		for(Population p : populations) {
			p.update(dt);
			p.updateSpecial(dt);
		}
	}

	public void initPositions() {
		for(Population p : populations)
			p.initPositions();
	}

	private boolean pause() {
		for(Population p : populations)
			if(p.getSize() < 1)
				return true;
		return false; 
	}
}
