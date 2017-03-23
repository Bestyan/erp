package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO.Repository;

public class BapiFactory {
	public static enum BapiType{
		AVAILABILITY, MATERIAL_REQUIREMENTS_PLANNING, GETLIST
	}
	
	private static Repository repository;
	
	
	
	private static HashMap<BapiType, String> nameMappings;
	private static HashMap<BapiType, String> getMappings(){
		if(nameMappings == null){
			nameMappings = new HashMap<>();
			/**
			 * AVAILABILITY
			 * Imports:
			 * 	BATCH
			 * 	CHECK_RULE
			 * 	CUSTOMER
			 * 	DEC_FOR_ROUNDING
			 * 	DEC_FOR_ROUNDING_X
			 * 	DOC_NUMBER
			 * 	ITM_NUMBER
			 * 	MATERIAL
			 * 	MATERIAL_EVG
			 * 	PLANT
			 * 	READ_ATP_LOCK
			 * 	READ_ATP_LOCK_X
			 * 	STGE_LOC
			 * 	STOCK_IND
			 * 	UNIT
			 * 	WBS_ELEM
			 * 
			 * Tables:
			 * 	WMDVEX
			 * 	WMDVSX
			 */
			nameMappings.put(BapiType.AVAILABILITY, "BAPI_MATERIAL_AVAILABILITY");
			/**
			 * MATERIAL_REQUIREMENTS_PLANNING:
			 * Imports:
			 * 	MATERIAL
			 * 	MATERIAL_EVG
			 * 	MRP_AREA
			 * 	MRP_PLAN_PARAM
			 * 	PLANT
			 * 	PLAN_SCENARIO
			 * 
			 * Tables:
			 * 	EXTENSIONOUT
			 * 	MRP_LISTS
			 */
			nameMappings.put(BapiType.MATERIAL_REQUIREMENTS_PLANNING, "BAPI_MATERIAL_PLANNING");
			/**
			 * GETLIST
			 * Imports:
			 * 	MAXROWS
			 * 
			 * Tables:
			 * 	DISTRIBUTIONCHANNELSELECTION
			 * 	MANUFACTURERPARTNUMB
			 * 	MATERIALSHORTDESCSEL
			 * 	MATNRLIST
			 * 	MATNRSELECTION
			 * 	PLANTSELECTION
			 * 	RETURN
			 * 	SALESORGANISATIONSELECTION
			 * 	STORAGELOCATIONSELECT
			 */
			nameMappings.put(BapiType.GETLIST, "BAPI_MATERIAL_GETLIST");
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
	
	public static Bapi getBapi(BapiType type){
		IFunctionTemplate functionTemplate = getRepository().getFunctionTemplate(getMappings().get(type));
		switch(type){
			case AVAILABILITY:
				return new BapiAvailability(functionTemplate);
			case GETLIST:
				return new BapiGetList(functionTemplate);
			case MATERIAL_REQUIREMENTS_PLANNING:
				return new BapiMaterialRequirementsPlanning(functionTemplate);
			default:
				throw new IllegalStateException("BapiType ohne Äquivalent");
		}
	}
}
