package fachlich;

import java.util.HashMap;
import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiGetList extends Bapi{
	
	public BapiGetList(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected Map<String, ParameterType> getExportParameterTypes() {
		if(exportParameterTypes == null){
			exportParameterTypes = new HashMap<>();
			exportParameterTypes.put("MATNRLIST", ParameterType.TABLE);
			exportParameterTypes.put("RETURN", ParameterType.TABLE);
		}
		return exportParameterTypes;
	}
}
