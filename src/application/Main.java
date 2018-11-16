package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private FXMLLoader layoutLoader;
	private AnchorPane layout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Evolution");
		initLayout();
	}

	public void initLayout() {
		try {
			layoutLoader = new FXMLLoader();
			layoutLoader.setLocation(Main.class.getResource("Layout.fxml"));
			layout = (AnchorPane) layoutLoader.load();
			Scene scene = new Scene(layout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}