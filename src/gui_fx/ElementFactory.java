package gui_fx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

@SuppressWarnings("unchecked")
public class ElementFactory {
	protected static Tab addNewTab(TabPane parent, String tabName){
		Tab root = null;
		try {
			//load the Tab from fxml file
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/exportTab.fxml"));
			root = loader.load();
			
			//set Tab values
			root.setId(tabName);
			root.setText(tabName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public static Tab addNewTableTab(TabPane parent, String tableName, List<Map<String, String>> rows){
		Tab root = addNewTab(parent, tableName);
		try {
			//init Table
			StackPane contentPane = (StackPane) root.getContent().lookup("#contentPane");
			TableView<Map<String, String>> table = new TableView<>();
			contentPane.getChildren().add(table);
			
			if(!rows.isEmpty()){
				//build columns using first row of values
				Map<String, String> row = rows.get(0);
				List<String> headlines = new ArrayList<>(row.keySet());
				Collections.sort(headlines); //sort columns alphabetically
				for(final String headline : headlines){
					TableColumn<Map<String, String>, String> column = new TableColumn<>();
					column.setText(headline);
					column.setCellValueFactory((rowMap) -> {
						return new SimpleStringProperty(rowMap.getValue().get(headline));
					});
					table.getColumns().add(column);
				}
			}
			table.setItems(FXCollections.observableArrayList(rows));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public static void addField(GridPane parent, String name, String value) {
		try {
			//load the Tab from fxml file
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/exportTab.fxml"));
			loader.setRoot(parent);
			GridPane root = loader.load();
			parent.addRow(Util.getRowCount(parent), root);
			
			//set values
			//			Label labelValue = (Label) root.lookup("#value");
			//			Label labelField = (Label) root.lookup("#field");
			//			labelValue.setText(value);
			//			labelField.setText(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addNewStructureTab(TabPane root, String key, Object value) {
		Map<String, String> structureFields = (Map<String, String>) value;
		List<String> fields = new ArrayList<>(structureFields.keySet());
		Collections.sort(fields);
		
		Tab structureTab = ElementFactory.addNewTab(root, key);
		GridPane structurePane = new GridPane();
		StackPane tabRoot = (StackPane) structureTab.getContent().lookup("#contentPane");
		tabRoot.getChildren().add(structurePane);
		for(String field : fields){
			ElementFactory.addField(structurePane, field, structureFields.get(field));
		}
	}
	
	public static Tab addField(TabPane root, Tab fieldsTab, String key, Object value) {
		if(fieldsTab == null){
			fieldsTab = ElementFactory.addNewTab(root, "Fields");
			StackPane tabRoot = (StackPane) fieldsTab.getContent().lookup("#contentPane");
			GridPane fieldsRoot = new GridPane();
			fieldsRoot.setId("fieldsPane");
			tabRoot.getChildren().add(fieldsRoot);
		}
		GridPane fieldsRoot = (GridPane) fieldsTab.getContent().lookup("#fieldsPane");
		ElementFactory.addField(fieldsRoot, key, (String) value);
		return fieldsTab;
	}
}
