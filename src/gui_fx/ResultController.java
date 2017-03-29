package gui_fx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@SuppressWarnings("unchecked")
public class ResultController {
	
	public static Tab addNewTableTab(TabPane parent, String tableName, ResultController controller, ArrayList<Map<String, String>> rows){
		try {
			//load the Tab from fxml file
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/exportTab.fxml"));
			Tab root = loader.load();
			
			//set Tab values
			root.setId(tableName);
			root.setText(tableName);
			
			//init Table
			TableView<Map<String, String>> table = (TableView<Map<String, String>>) root.getContent().lookup("#table");
			TableColumn<Map<String, String>, String> column = new TableColumn<>();
			if(rows.isEmpty()){
				table.getColumns().add(column);
				ObservableList<Map<String, String>> items = FXCollections.observableArrayList();
				Map<String, String> value = new HashMap<>();
				value.put("value", "Tabelle ist leer");
				items.add(value);
				table.setItems(items);
			} else{
				//TODO
			}
			
			return root;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
