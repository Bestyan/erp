package fachlich;

import java.util.HashMap;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO.Repository;

public class BapiFactory {
	public static enum BapiType{
		AVAILABILITY, GETDETAIL, GETLIST
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
			 * GETDETAIL:
			 * Imports:
			 * 	MATERIAL
			 * 	MATERIAL_EVG
			 * 	PLANT
			 * 	VALUATIONAREA
			 * 	VALUATIONTYPE
			 */
			nameMappings.put(BapiType.GETDETAIL, "BAPI_MATERIAL_GET_DETAIL");
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
			case GETDETAIL:
				return new BapiGetDetail(functionTemplate);
			default:
				throw new IllegalStateException("BapiType ohne Äquivalent");
		}
	}
}
