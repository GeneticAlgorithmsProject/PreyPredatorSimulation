package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Simulation;

public class LayoutController {

	@FXML
	private Button resetButton;
	@FXML
	private Pane pane;
	@FXML
	private Canvas canvas;
	@FXML
	private LineChart<Number, Number> preyChart;
	@FXML
	private LineChart<Number, Number> predatorChart;
	@FXML
	private TextField timer;
	@FXML
	private Slider timeMultiplier;
	@FXML
	private Slider foodCount;
	@FXML
	private Slider preyCount;
	@FXML
	private Slider predatorCount;
	
	private Simulation simulation;

	@FXML
	public void initialize() {
		initSliders();
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		simulation = new Simulation(pane, preyChart, predatorChart, timer);
		simulation.init();
		simulation.initPositions();
		Simulation.timeMultiplier = timeMultiplier.getValue();
		simulation.animate();
	}
		
	private void initSliders() {
		timeMultiplier.valueProperty().addListener((observable, oldValue, newValue) -> {
			Simulation.timeMultiplier = timeMultiplier.getValue();
		});
		foodCount.valueProperty().addListener((observable, oldValue, newValue) -> {
			Simulation.foodCount = (int)foodCount.getValue();
		});
		preyCount.valueProperty().addListener((observable, oldValue, newValue) -> {
			Simulation.preyCount = (int)preyCount.getValue();
		});
		predatorCount.valueProperty().addListener((observable, oldValue, newValue) -> {
			Simulation.predatorCount = (int)predatorCount.getValue();
		});
	}

}