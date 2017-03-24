package fachlich;

import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiAvailability extends Bapi {
	
	public BapiAvailability(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected Map<String, ParameterType> getExportParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
