package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.Repository;

public class BapiFactory {
	public static enum BapiType{
		VERFUEGBARKEIT, MATERIALBEDARFSPLANUNG, LISTE
	}
	
	private static Repository repository;
	
	private static HashMap<BapiType, String> nameMappings;
	private static HashMap<BapiType, String> getMappings(){
		if(nameMappings == null){
			nameMappings = new HashMap<>();
			nameMappings.put(BapiType.VERFUEGBARKEIT, "BAPI_MATERIAL_AVAILABILITY");
			nameMappings.put(BapiType.MATERIALBEDARFSPLANUNG, "BAPI_MATERIAL_PLANNING");
			nameMappings.put(BapiType.LISTE, "BAPI_MATERIAL_GETLIST");
		}
		return nameMappings;
	}
	
	public BapiFactory(Repository repository){
		super();
		setRepository(repository);
	}
	
	public static Repository getRepository() {
		return repository;
	}
	
	public static void setRepository(Repository repository) {
		BapiFactory.repository = repository;
	}
	
	private static String getFunctionName(BapiType type){
		String result = getMappings().get(type);
		if(result == null){
			throw new IllegalStateException("BapiType ohne Funktionsäquivalent (" + BapiFactory.class.getSimpleName() + ")");
		}
		return result;
	}
	public static Function getFunction(BapiType type){
		Function function = getRepository().getFunctionTemplate(getFunctionName(type)).getFunction();
		return function;
	}
}
