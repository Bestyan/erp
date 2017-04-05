package gui_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

public class ExportTabController {
	
	@FXML
	private StackPane contentPane;
	@FXML
	private Tab exportTab;
	
	protected StackPane getContentPane() {
		return contentPane;
	}
	
	protected Tab getExportTab() {
		return exportTab;
	}
}
