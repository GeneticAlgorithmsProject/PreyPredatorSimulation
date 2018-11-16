package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import application.Simulation;

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
	
	private Simulation simulation;
	

	@FXML
	public void initialize() {
		initCharts();
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		simulation = new Simulation(pane, preyChart, predatorChart, timer);
		simulation.init();
		simulation.animate();
	}
	
	private void initCharts() {
		NumberAxis axisX = (NumberAxis)preyChart.getXAxis();
		axisX.setLabel("Generation");
		axisX.setAutoRanging(true);
		axisX.setAnimated(true);
		axisX.setForceZeroInRange(false);
		
		NumberAxis axisY = (NumberAxis)preyChart.getYAxis();	
		axisY.setAutoRanging(true);
		axisY.setAnimated(true);
		axisY.setLabel("Lifespan");
	
		axisX = (NumberAxis)predatorChart.getXAxis();
		axisX.setLabel("Generation");
		axisX.setAutoRanging(true);
		axisX.setAnimated(true);
		axisX.setForceZeroInRange(false);
		
		axisY = (NumberAxis)predatorChart.getYAxis();	
		axisY.setAutoRanging(true);
		axisY.setAnimated(true);
		axisY.setLabel("Lifespan");
	}

}