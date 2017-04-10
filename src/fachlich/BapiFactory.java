package fachlich;

import java.util.HashMap;
import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO.Repository;

public abstract class BapiFactory {
	public static enum BapiType{
		AVAILABILITY, GETDETAIL, GETLIST
	}
	
	private static Repository repository;
	
	private static Map<BapiType, String> nameMappings;
	private static Map<BapiType, String> getMappings(){
		if(nameMappings == null){
			nameMappings = new HashMap<>();
			/**
			 * AVAILABILITY
			 * Imports:
			 * 	Fields:
			 * 		BATCH
			 * 		CHECK_RULE
			 * 		CUSTOMER
			 * 		PLANT
			 * 		UNIT
			 * 		STGE_LOC
			 * 		MATERIAL
			 * 	vmtl irrelevant (Fields):
			 * 		DEC_FOR_ROUNDING
			 * 		DEC_FOR_ROUNDING_X
			 * 		DOC_NUMBER
			 * 		ITM_NUMBER
			 * 		READ_ATP_LOCK
			 * 		READ_ATP_LOCK_X
			 * 		STOCK_IND
			 * 		WBS_ELEM
			 * 
			 * 	Structures:
			 * 		MATERIAL_EVG
			 * 
			 * 	Tables:
			 * 		WMDVEX
			 * 		WMDVSX
			 * 
			 */
			nameMappings.put(BapiType.AVAILABILITY, "BAPI_MATERIAL_AVAILABILITY");
			/**
			 * GETDETAIL:
			 * Imports:
			 * 	Fields:
			 * 		PLANT
			 * 		VALUATIONAREA
			 * 		VALUATIONTYPE
			 * 		MATERIAL
			 * 	Structures:
			 * 		MATERIAL_EVG
			 */
			nameMappings.put(BapiType.GETDETAIL, "BAPI_MATERIAL_GET_DETAIL");
			/**
			 * GETLIST
			 * Imports:
			 * 	Fields:
			 * 		MAXROWS
			 * 
			 * 	Tables:
			 * 		DISTRIBUTIONCHANNELSELECTION
			 * 		MANUFACTURERPARTNUMB
			 * 		MATERIALSHORTDESCSEL
			 * 		MATNRLIST
			 * 		MATNRSELECTION
			 * 		PLANTSELECTION
			 * 		RETURN
			 * 		SALESORGANISATIONSELECTION
			 * 		STORAGELOCATIONSELECT
			 */
			nameMappings.put(BapiType.GETLIST, "BAPI_MATERIAL_GETLIST");
		}
		return nameMappings;
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
