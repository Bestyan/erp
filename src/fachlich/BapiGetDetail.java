package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.IFunctionTemplate;

public class BapiGetDetail extends Bapi {
	
	public BapiGetDetail(IFunctionTemplate template) {
		super(template);
	}
	
	@Override
	protected HashMap<String, ParameterType> getExportParameterTypes() {
		return new HashMap<>();
	}
	
}