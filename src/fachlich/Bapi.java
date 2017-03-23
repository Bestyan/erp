package fachlich;

import com.sap.mw.jco.IFunctionTemplate;

public abstract class Bapi {
	protected IFunctionTemplate functionTemplate;
	
	public Bapi(IFunctionTemplate template){
		super();
		this.setFunctionTemplate(template);
	}
	
	protected IFunctionTemplate getFunctionTemplate() {
		return functionTemplate;
	}
	
	protected void setFunctionTemplate(IFunctionTemplate functionTemplate) {
		this.functionTemplate = functionTemplate;
	}
}
