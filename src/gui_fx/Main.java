package gui_fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fachlich.BapiFactory.BapiType;
import fachlich.SapModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Utils;

public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/main.fxml"));
		AnchorPane root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Retail Material");
		primaryStage.sizeToScene();
		primaryStage.show();
		
		Utils.sizeStage(primaryStage);
		primaryStage.setResizable(false);
	}
	
	public void testGetList(){
		SapModel model = new SapModel("915", "GBI-0-18", "123456");
		Map<String, Object> parameters = new HashMap<>();
		List<Map<String, String>> matnrSelection = new ArrayList<>();
		Map<String, String> row = new HashMap<>();
		row.put("SIGN", "I");
		row.put("OPTION", "CP");
		row.put("MATNR_LOW", "ERPKÖ*");
		matnrSelection.add(row);
		parameters.put("MATNRSELECTION", matnrSelection);
		Map<String, Object> result = model.executeBapi(BapiType.GETLIST, parameters);
		ResultController.displayResults(result);
	}
	
	public void testGetDetail(){
		SapModel model = new SapModel("915", "GBI-0-18", "123456");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("MATERIAL", "ERPKÖ01");
		Map<String, Object> result = model.executeBapi(BapiType.GETDETAIL, parameters);
		ResultController.displayResults(result);
	}
	
}
