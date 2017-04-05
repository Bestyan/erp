package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Utils {
	public static int getRowCount(GridPane pane) {
		int numRows = pane.getRowConstraints().size();
		for (int i = 0; i < pane.getChildren().size(); i++) {
			Node child = pane.getChildren().get(i);
			if (child.isManaged()) {
				Integer rowIndex = GridPane.getRowIndex(child);
				if(rowIndex != null){
					numRows = Math.max(numRows,rowIndex+1);
				}
			}
		}
		return numRows;
	}
	
	public static void sizeStage(Stage stage){
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		if(stage.getHeight() > 800){
			stage.setMinHeight(800);
		} else{
			stage.setMinHeight(stage.getHeight());
		}
		if(stage.getWidth() > 600){
			stage.setMinWidth(600);
		} else{
			stage.setMinWidth(stage.getWidth());
		}
		stage.setMaxHeight(screen.getMaxY());
		stage.setMaxWidth(screen.getMaxX());
	}
	
	public static String getValue(Object valueObject){
		String fieldValue = null;
		if(valueObject instanceof String){
			fieldValue = (String) valueObject;
		} else if(valueObject instanceof Date){
			fieldValue = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format((Date) valueObject);
		} else{
			fieldValue = valueObject.toString();
		}
		return fieldValue;
	}
}
