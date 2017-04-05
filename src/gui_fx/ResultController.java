package gui_fx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import utils.Utils;

@SuppressWarnings("unchecked")
public class ResultController {
	
	
	
	/**
	 * erzeugt ein neues Fenster, in dem alle Returnparameter wie im Bapi-Objekt angegeben ausgegeben werden
	 * @param results
	 */
	public static void displayResults(Map<String, Object> results){
		//init new Window and TabPane
		Stage stage = new Stage();
		TabPane root = new TabPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		//populate TabPane
		List<String> keys = new ArrayList<>(results.keySet());
		ExportTabController fieldController = null;
		Collections.sort(keys);
		for(String key : keys){
			Object value = results.get(key);
			
			if(value instanceof String){
				
				fieldController = ElementFactory.addField(root, fieldController, key, value);
				
			} else if(value instanceof Map){
				
				ElementFactory.addNewStructureTab(root, key, value);
				
			} else if(value instanceof List){
				
				ElementFactory.addNewTableTab(root, key, (List<Map<String, String>>) value);
				
			}
		}
		
		//show Window
		stage.setTitle("Retail Material");
		stage.sizeToScene();
		stage.show();
		Utils.sizeStage(stage);
	}
}
