package gui_fx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import utils.Utils;

@SuppressWarnings("unchecked")
public class ElementFactory {
	private static final int HGAP_GRIDPANE = 20;
	
	protected static ExportTabController addNewTab(TabPane parent, String tabName){
		ExportTabController controller = new ExportTabController();
		try {
			//load the Tab from fxml file
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/exportTab.fxml"));
			loader.setController(controller);
			Tab root = loader.load();
			
			//set Tab values
			root.setId(tabName);
			root.setText(tabName);
			
			parent.getTabs().add(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return controller;
	}
	
	public static ExportTabController addNewTableTab(TabPane parent, String tableName, List<Map<String, String>> rows){
		ExportTabController controller = addNewTab(parent, tableName);
		StackPane contentPane = controller.getContentPane();
		try {
			//init Table
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
		return controller;
	}
	
	public static void addField(GridPane parent, String name, String value) {
		Label labelValue = new Label();
		Label labelField = new Label();
		
		//set values
		labelValue.setText(value);
		labelField.setText(name);
		parent.addRow(Utils.getRowCount(parent), labelField, labelValue);
	}
	
	public static void addNewStructureTab(TabPane root, String key, Object value) {
		Map<String, String> structureFields = (Map<String, String>) value;
		List<String> fields = new ArrayList<>(structureFields.keySet());
		Collections.sort(fields);
		
		ExportTabController controller = ElementFactory.addNewTab(root, key);
		GridPane structurePane = new GridPane();
		structurePane.setHgap(HGAP_GRIDPANE);
		StackPane tabRoot = controller.getContentPane();
		tabRoot.getChildren().add(structurePane);
		for(String field : fields){
			ElementFactory.addField(structurePane, field, structureFields.get(field));
		}
	}
	
	public static ExportTabController addField(TabPane root, ExportTabController controller, String key, Object value) {
		if(controller == null){
			controller = ElementFactory.addNewTab(root, "Fields");
			StackPane tabRoot = controller.getContentPane();
			GridPane fieldsRoot = new GridPane();
			fieldsRoot.setHgap(HGAP_GRIDPANE);
			fieldsRoot.setId("fieldsPane");
			tabRoot.getChildren().add(fieldsRoot);
		}
		GridPane fieldsRoot = (GridPane) controller.getContentPane().lookup("#fieldsPane");
		ElementFactory.addField(fieldsRoot, key, (String) value);
		return controller;
	}
}
