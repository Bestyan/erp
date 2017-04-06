package gui_fx;

import java.util.HashMap;
import java.util.Map;

import fachlich.BapiFactory.BapiType;
import fachlich.SapModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class GetDetailController {
	
	@FXML
	private TextField textfieldMaterial;
	@FXML
	private TextField textfieldPlant;
	@FXML
	private TextField textfieldValuationArea;
	@FXML
	private TextField textfieldValuationType;
	
	@FXML
	private Button button_suchen;
	
	@FXML
	private CheckBox checkbox_Material;
	@FXML
	private CheckBox checkbox_Plant;
	@FXML
	private CheckBox checkbox_ValuationArea;
	@FXML
	private CheckBox checkbox_ValuationType;
	
	@FXML
	public void click_button_Suchen() {
		HashMap<String, Object> inputs = new HashMap<>();
		//MATERIAL
		if(checkbox_Material.isSelected()){
			inputs.put("MATERIAL", textfieldMaterial.getText());
		}
		
		//PLANT
		if(checkbox_Plant.isSelected()){
			inputs.put("PLANT", textfieldPlant.getText());
		}
		
		//VALUATIONAREA
		if(checkbox_ValuationArea.isSelected()){
			inputs.put("VALUATIONAREA", textfieldValuationArea.getText());
		}
		
		//VALUATIONTYPE
		if(checkbox_ValuationType.isSelected()){
			inputs.put("VALUATIONTYPE", textfieldValuationType.getText());
		}
		
		SapModel model = SapModel.connect();
		Map<String, Object> resultGetDetail = model.executeBapi(BapiType.GETDETAIL, inputs);
		ResultController.displayResults(resultGetDetail);
	}
}
