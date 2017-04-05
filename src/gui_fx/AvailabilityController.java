package gui_fx;

import java.util.HashMap;
import java.util.Map;

import fachlich.BapiFactory.BapiType;
import fachlich.SapModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AvailabilityController {
	
	
	@FXML
	private TextField textfieldPlant;
	@FXML
	private TextField textfieldMaterial;
	@FXML
	private TextField textfieldUnit;
	@FXML
	private TextField textfieldCheckRule;
	@FXML
	private TextField textfieldStgeLoc;
	
	@FXML
	private CheckBox checkbox_Plant;
	@FXML
	private CheckBox checkbox_Material;
	@FXML
	private CheckBox checkbox_Unit;
	@FXML
	private CheckBox checkbox_CheckRule;
	@FXML
	private CheckBox checkbox_StgeLoc;
	
	@FXML
	private Button button_suchen;
	
	@FXML
	public void click_button_Suchen() {
		//RESULTPLANT
		HashMap<String, Object> inputs = new HashMap<>();
		inputs.put("PLANT", textfieldPlant.getText());
		
		//MATERIAL
		inputs.put("MATERIAL", textfieldMaterial.getText());
		
		//UNIT
		inputs.put("UNIT", textfieldUnit.getText());
		
		//CHECKRULE
		inputs.put("CHECK_RULE", textfieldCheckRule.getText());
		
		//STGE_LOC
		inputs.put("STGE_LOC", textfieldStgeLoc.getText());
		
		SapModel model = SapModel.connect();
		Map<String, Object> resultAvailability = model.executeBapi(BapiType.AVAILABILITY, inputs);
		ResultController.displayResults(resultAvailability);
	}
}
