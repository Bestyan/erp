package gui_fx;

import java.util.HashMap;
import java.util.Map;

import fachlich.BapiFactory.BapiType;
import fachlich.SapModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AvailabilityController {
	
	
	@FXML
	private TextField textfieldPlant;
	@FXML
	private TextField textfieldCheckRule;
	@FXML
	private TextField textfieldStgeLoc;
	
	@FXML
	private Button button_suchen;
	
	@FXML
	public void click_button_Suchen() {
		//RESULTPLANT
		HashMap<String, Object> inputs = new HashMap<>();
		inputs.put("PLANT", textfieldPlant.getText());
		
		//CHECKRULE
		inputs.put("CHECK_RULE", textfieldCheckRule.getText());
		
		//STGE_LOC
		inputs.put("STGE_LOC", textfieldStgeLoc.getText());
		
		SapModel model = new SapModel("915", "GBI-0-18", "123456");
		Map<String, Object> resultAvailability = model.executeBapi(BapiType.GETLIST, inputs);
		ResultController.displayResults(resultAvailability);
	}
}
