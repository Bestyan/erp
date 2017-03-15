package gui_fx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button button_availability;
	
	@FXML
	public void click_availability() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/guiGetList.fxml"));
			AnchorPane root;
			root = loader.load();
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("CuteParts Migrator");
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setMinHeight(primaryStage.getHeight());
			primaryStage.setMinWidth(primaryStage.getWidth());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
