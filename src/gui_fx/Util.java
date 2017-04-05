package gui_fx;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Util {
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
}
