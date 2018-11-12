package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
		simulation.animate();
	}

}