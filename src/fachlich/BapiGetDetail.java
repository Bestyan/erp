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
		return new HashMap<>();
	}
	
}
