package fachlich;

import java.util.HashMap;
import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiAvailability extends Bapi {
	
	public BapiAvailability(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected Map<String, ParameterType> getExportParameterTypes() {
		if(exportParameterTypes == null){
			exportParameterTypes = new HashMap<>();
			exportParameterTypes.put("ENDLEADTME", ParameterType.FIELD);
			exportParameterTypes.put("AV_QTY_PLT", ParameterType.FIELD);
			exportParameterTypes.put("DIALOGFLAG", ParameterType.FIELD);
			exportParameterTypes.put("RETURN", ParameterType.STRUCTURE);
			exportParameterTypes.put("WMDVSX", ParameterType.TABLE);
			exportParameterTypes.put("WMDVEX", ParameterType.TABLE);
		}
		return null;
	}
}
