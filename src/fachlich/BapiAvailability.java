package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiAvailability extends Bapi {
	
	public BapiAvailability(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected HashMap<String, ParameterType> getExportParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
