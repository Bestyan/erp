package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.JCO.Repository;

public class BapiFactory {
	public static enum BapiType{
		VERFUEGBARKEIT, MATERIALBEDARFSPLANUNG, LISTE
	}
	
	private Repository repository;
	
	private static HashMap<BapiType, String> nameMappings;
	{
		nameMappings = new HashMap<>();
		nameMappings.put(BapiType.VERFUEGBARKEIT, "BAPI_MATERIAL_AVAILABILITY");
		nameMappings.put(BapiType.MATERIALBEDARFSPLANUNG, "BAPI_MATERIAL_PLANNING");
		nameMappings.put(BapiType.LISTE, "BAPI_MATERIAL_GETLIST");
	}
	private static HashMap<BapiType, String> getMappings(){
		return nameMappings;
	}
	
	public BapiFactory(Repository repository){
		super();
		this.setRepository(repository);
	}
	
	public Repository getRepository() {
		return repository;
	}
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	private static String getFunctionName(BapiType type){
		String result = getMappings().get(type);
		if(result == null){
			throw new IllegalStateException("BapiType ohne Funktionsäquivalent (" + BapiFactory.class.getSimpleName() + ")");
		}
		return result;
	}
}
