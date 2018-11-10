package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import application.Simulation;

public class LayoutController {

	@FXML
	private Button resetButton;
	@FXML
	private Pane pane;
	@FXML
	private Canvas canvas;

	private double width;
	private double height;

	private Light.Spot light;
	private Lighting lighting;

	private Group foodGroup, preyGroup, predatorsGroup;

	private Simulation simulation;

	@FXML
	public void initialize() {
		initLighting();
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		width = pane.getWidth();
		height = pane.getHeight();
//		GeneticAlgorithm gA = new GeneticAlgorithm(2, 1.5, 0.01);
//		System.out.println("Population:");
//		gA.createNewGeneration(prey);
		simulation = new Simulation(pane);
		simulation.init();
		animate();
	}

	public void animate() {
		new AnimationTimer() {
			long startTime = -1;
			double dt = 0.01;

			private void check() {
				if (simulation.pause()) {
					this.stop();
				}
			}

			@Override
			public void handle(long now) {
				if (startTime == -1) {
					startTime = now;
				}
//				double time = (now - startTime) / 1E9d;
				check();
				reset();
				move();
				time(dt);
				update();
				draw();
			}
		}.start();
	}

	public void reset() {
		this.pane.getChildren().clear();
	}

	public void move() {
		simulation.move();
	}

	public void update() {
		simulation.update();
	}

	public void draw() {
		simulation.draw(lighting);
		foodGroup = simulation.getFood().getGroup();
		preyGroup = simulation.getPreys().getGroup();
		predatorsGroup = simulation.getPredators().getGroup();
		pane.getChildren().addAll(foodGroup);
		pane.getChildren().addAll(preyGroup);
		pane.getChildren().addAll(predatorsGroup);
	}

	public void time(double dt) {
		simulation.time(dt);
	}

	public void init() {
		this.simulation.init();
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
}