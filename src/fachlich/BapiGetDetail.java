package fachlich;

import java.util.HashMap;
import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiGetDetail extends Bapi {
	
	public BapiGetDetail(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected Map<String, ParameterType> getExportParameterTypes() {
		if(exportParameterTypes == null){
			exportParameterTypes = new HashMap<>();
			exportParameterTypes.put("MATERIAL_GENERAL_DATA", ParameterType.STRUCTURE);
			exportParameterTypes.put("RETURN", ParameterType.STRUCTURE);
			exportParameterTypes.put("MATERIALPLANTDATA", ParameterType.STRUCTURE);
			exportParameterTypes.put("MATERIALVALUATIONDATA", ParameterType.STRUCTURE);
		}
		return exportParameterTypes;
	}
	
}
