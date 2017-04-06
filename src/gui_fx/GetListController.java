package gui_fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fachlich.BapiFactory.BapiType;
import fachlich.SapModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class GetListController {
	
	@FXML
	private TextField textfieldMatnrSelection1;
	@FXML
	private TextField textfieldMatnrSelection2;
	@FXML
	private TextField textfieldMatnrSelection3;
	@FXML
	private TextField textfieldMatnrSelection4;
	
	@FXML
	private TextField textfieldMaterialShortDescSel1;
	@FXML
	private TextField textfieldMaterialShortDescSel2;
	@FXML
	private TextField textfieldMaterialShortDescSel3;
	@FXML
	private TextField textfieldMaterialShortDescSel4;
	
	@FXML
	private TextField textfieldManufacturePartNumb1;
	@FXML
	private TextField textfieldManufacturePartNumb2;
	
	@FXML
	private TextField textfieldPlantSelection1;
	@FXML
	private TextField textfieldPlantSelection2;
	@FXML
	private TextField textfieldPlantSelection3;
	@FXML
	private TextField textfieldPlantSelection4;
	
	@FXML
	private TextField textfieldStorageLocationSelection1;
	@FXML
	private TextField textfieldStorageLocationSelection2;
	@FXML
	private TextField textfieldStorageLocationSelection3;
	@FXML
	private TextField textfieldStorageLocationSelection4;
	
	@FXML
	private TextField textfieldSalesOrganisationSelection1;
	@FXML
	private TextField textfieldSalesOrganisationSelection2;
	@FXML
	private TextField textfieldSalesOrganisationSelection3;
	@FXML
	private TextField textfieldSalesOrganisationSelection4;
	
	@FXML
	private TextField textfieldDistributionChannelSelection1;
	@FXML
	private TextField textfieldDistributionChannelSelection2;
	@FXML
	private TextField textfieldDistributionChannelSelection3;
	@FXML
	private TextField textfieldDistributionChannelSelection4;
	
	@FXML
	private CheckBox checkbox_MatnrSelection;
	@FXML
	private CheckBox checkbox_MaterialShortDescSel;
	@FXML
	private CheckBox checkbox_ManufacturePartNumb;
	@FXML
	private CheckBox checkbox_PlantSelection;
	@FXML
	private CheckBox checkbox_StorageLocationSelection;
	@FXML
	private CheckBox checkbox_SalesOrganisationSelection;
	@FXML
	private CheckBox checkbox_distributionChannelSelection;
	
	@FXML
	private Button button_suchen;
	
	@FXML
	public void click_button_Suchen() {
		
		//MATNRSELECTION
		HashMap<String, Object> resultMatnrSelection = new HashMap<>();
		ArrayList<HashMap<String, String>> matnrSelection = new ArrayList<>();
		HashMap<String, String> matnrSelectionRow = new HashMap<>();
		
		if (checkbox_MatnrSelection.isSelected()) {
			matnrSelectionRow.put("SIGN", textfieldMatnrSelection1.getText());
			matnrSelectionRow.put("OPTION", textfieldMatnrSelection2.getText());
			matnrSelectionRow.put("MATNR_LOW", textfieldMatnrSelection3.getText());
			matnrSelectionRow.put("MATNR_HIGH", textfieldMatnrSelection4.getText());
		
			matnrSelection.add(matnrSelectionRow);
			resultMatnrSelection.put("MATNRSELECTION", matnrSelection);
		}
		
		//MATERIALSHORTDESCSEL
		HashMap<String, Object> resultMaterialShortDescSel = new HashMap<>();
		ArrayList<HashMap<String, String>> materialShortDescSel = new ArrayList<>();
		HashMap<String, String> MaterialShortDescSelRow = new HashMap<>();
		
		if (checkbox_MaterialShortDescSel.isSelected()) {
			MaterialShortDescSelRow.put("SIGN", textfieldMaterialShortDescSel1.getText());
			MaterialShortDescSelRow.put("OPTION", textfieldMaterialShortDescSel2.getText());
			MaterialShortDescSelRow.put("DESCR_LOW", textfieldMaterialShortDescSel3.getText());
			MaterialShortDescSelRow.put("DESCR_HIGH", textfieldMaterialShortDescSel4.getText());
		
			materialShortDescSel.add(MaterialShortDescSelRow);
			resultMaterialShortDescSel.put("MATERIALSHORTDESCSEL", materialShortDescSel);
		}
		
		//MANUFACTUREPARTNUMB
		HashMap<String, Object> resultManufacturePartNumb = new HashMap<>();
		ArrayList<HashMap<String, String>> manufacturePartNumb = new ArrayList<>();
		HashMap<String, String> ManufacturePartNumbRow = new HashMap<>();
		
		if (checkbox_ManufacturePartNumb.isSelected()) {
			ManufacturePartNumbRow.put("MANU_MAT", textfieldManufacturePartNumb1.getText());
			ManufacturePartNumbRow.put("MFR_NO", textfieldManufacturePartNumb2.getText());
		
			manufacturePartNumb.add(ManufacturePartNumbRow);
			resultManufacturePartNumb.put("MANUFACTUREPARTNUMB", manufacturePartNumb);
		}
		
		//PLANTSELECTION
		HashMap<String, Object> resultPlantSelection = new HashMap<>();
		ArrayList<HashMap<String, String>> plantSelection = new ArrayList<>();
		HashMap<String, String> plantSelectionRow = new HashMap<>();
		
		if (checkbox_PlantSelection.isSelected()) {
			plantSelectionRow.put("SIGN", textfieldPlantSelection1.getText());
			plantSelectionRow.put("OPTION", textfieldPlantSelection2.getText());
			plantSelectionRow.put("PLANT_LOW", textfieldPlantSelection3.getText());
			plantSelectionRow.put("PLANT_HIGH", textfieldPlantSelection4.getText());
		
			plantSelection.add(plantSelectionRow);
			resultPlantSelection.put("PLANTSELECTION", plantSelection);
		}
		
		//STORAGELACATIONSELECT
		HashMap<String, Object> resultStorageLocationSelect = new HashMap<>();
		ArrayList<HashMap<String, String>> storageLocationSelect = new ArrayList<>();
		HashMap<String, String> storageLocationSelectRow = new HashMap<>();
		
		if (checkbox_StorageLocationSelection.isSelected()) {
			storageLocationSelectRow.put("SIGN", textfieldPlantSelection1.getText());
			storageLocationSelectRow.put("OPTION", textfieldPlantSelection2.getText());
			storageLocationSelectRow.put("PLANT_LOW", textfieldPlantSelection3.getText());
			storageLocationSelectRow.put("PLANT_HIGH", textfieldPlantSelection4.getText());
		
			storageLocationSelect.add(storageLocationSelectRow);
			resultStorageLocationSelect.put("STORAGELOCATIONSELECT", storageLocationSelect);
		}
		
		//SALESORGANISATIONSELECTION
		HashMap<String, Object> resultSalesOrganisationSelection = new HashMap<>();
		ArrayList<HashMap<String, String>> salesOrganisationSelection = new ArrayList<>();
		HashMap<String, String> salesOrganisationSelectionRow = new HashMap<>();
		
		if (checkbox_SalesOrganisationSelection.isSelected()) {
			salesOrganisationSelectionRow.put("SIGN", textfieldSalesOrganisationSelection1.getText());
			salesOrganisationSelectionRow.put("OPTION", textfieldSalesOrganisationSelection2.getText());
			salesOrganisationSelectionRow.put("SALESORG_LOW", textfieldSalesOrganisationSelection3.getText());
			salesOrganisationSelectionRow.put("SALESORG_HIGH", textfieldSalesOrganisationSelection4.getText());
		
			salesOrganisationSelection.add(salesOrganisationSelectionRow);
			resultSalesOrganisationSelection.put("SALESORGANISATIONSELECTION", salesOrganisationSelection);
		}
		
		//DISTRIBUTIONCHANNELSELECTION
		HashMap<String, Object> resultDistributionChannelSelection = new HashMap<>();
		ArrayList<HashMap<String, String>> distributionChannelSelection = new ArrayList<>();
		HashMap<String, String> distributionChannelSelectionRow = new HashMap<>();
		
		if (checkbox_distributionChannelSelection.isSelected()) {
			distributionChannelSelectionRow.put("SIGN", textfieldDistributionChannelSelection1.getText());
			distributionChannelSelectionRow.put("OPTION", textfieldDistributionChannelSelection2.getText());
			distributionChannelSelectionRow.put("DISTR_CHAN_LOW", textfieldDistributionChannelSelection3.getText());
			distributionChannelSelectionRow.put("DISTR_CHAN_HIGH", textfieldDistributionChannelSelection4.getText());
		
			distributionChannelSelection.add(distributionChannelSelectionRow);
			resultDistributionChannelSelection.put("DISTRIBUTIONCHANNELSELECTION", distributionChannelSelection);
		}
		
		SapModel model = SapModel.connect();
		Map<String, Object> resultMapDistributionChannelSelection = model.executeBapi(BapiType.GETLIST, resultDistributionChannelSelection);
		ResultController.displayResults(resultMapDistributionChannelSelection);
	}
}
