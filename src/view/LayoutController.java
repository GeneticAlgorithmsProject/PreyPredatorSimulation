package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Individual;
import application.Simulation;

public class LayoutController {

	@FXML
	private Button resetButton;
	@FXML
	private Pane pane;
	@FXML
	private Canvas canvas;

	private Simulation simulation;

	@FXML
	public void initialize() {

	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		simulation = new Simulation(pane);
		simulation.init();
		animate();
	}

	public void animate() {
		new AnimationTimer() {
			long startTime = -1;

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
				long deltaNanos = now - startTime;
				startTime = now;
				double dt = deltaNanos / 1.0e9;
//				double time = (now - startTime) / 1E9d;
				check();
				reset();
				move();
				update(dt);
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

	public void update(double dt) {
		simulation.update(dt);
	}

	public void draw() {
		for (Individual ind : simulation.getFood().getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : simulation.getPredators().getPopulation())
			pane.getChildren().add(ind.getCircle());
		for (Individual ind : simulation.getPreys().getPopulation())
			pane.getChildren().add(ind.getCircle());

	}

	public void init() {
		this.simulation.init();
	}
}