package fachlich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Structure;
import com.sap.mw.jco.JCO.Table;

import utils.Utils;

public abstract class Bapi {
	public static enum ParameterType{
		TABLE, STRUCTURE, FIELD;
	}
	
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
	
	protected Map<String, ParameterType> exportParameterTypes;
	
	/**
	 * @param params alle Function-Parameter
	 * <ul>
	 * 	<li>key = Parametername (z.B. MATNRSELECTION)
	 * 	<li>value
	 * 	<ul>
	 * 		<li>Table: {@code ArrayList<HashMap<String, String>>}</li>
	 * 		<ul>
	 * 			<li>key = Spalte (z.B. SIGN)</li>
	 * 			<li>value = Spalteninhalt (z.B. I)</li>
	 * 		</ul>
	 * 		<li>einfaches Feld: {@code String}</li>
	 * 		<li>Structure: {@code HashMap<String, String>}</li>
	 * 		<ul>
	 * 			<li>key = Spalte</li>
	 * 			<li>value = Spalteninhalt</li>
	 * 		</ul>
	 * 	</ul>
	 * </ul>
	 * 
	 * @return
	 */
	public Map<String, Object> execute(Map<String, Object> params, Client connection){
		Function function = this.buildFunction(params);
		connection.execute(function);
		return this.repackageExportParameters(function);
	}
	
	protected Map<String, Object> repackageExportParameters(Function function) {
		Map<String, Object> result = new HashMap<>();
		
		ParameterList tables = function.getTableParameterList();
		ParameterList imports = function.getImportParameterList();
		ParameterList exports = function.getExportParameterList();
		
		Map<String, ParameterType> exportTypes = this.getExportParameterTypes();
		for(String key : exportTypes.keySet()){
			ParameterType type = exportTypes.get(key);
			switch(type){
				case TABLE:
					try{
						Table table = tables.getTable(key);
						List<Map<String, String>> rows = new ArrayList<>();
						if(table.getNumRows() > 0){
							do{
								Map<String, String> row = new HashMap<>();
								for(int i = 0; i < table.getFieldCount(); i++){
									String fieldName = table.getField(i).getName();
									Object valueObject = table.getValue(fieldName);
									String fieldValue = Utils.getValue(valueObject);
									row.put(fieldName, fieldValue);
								}
								rows.add(row);
							}while(table.nextRow());
						} else{
							//add a dummy row to have something to display
							Map<String, String> row = new HashMap<>();
							row.put("value", "empty table");
							rows.add(row);
						}
						result.put(key, rows);
						break;
					} catch(Exception tableNotFound){
						//do nothing, try structure in next case
					}
					//no break here!
				case STRUCTURE:
					try{
						Structure structure = exports.getStructure(key);
						Map<String, String> resultStructure = new HashMap<>();
						for(int i = 0; i < structure.getFieldCount(); i++){
							String fieldName = structure.getField(i).getName();
							Object valueObject = structure.getValue(fieldName);
							String fieldValue = Utils.getValue(valueObject);
							resultStructure.put(fieldName, fieldValue);
						}
						result.put(key, resultStructure);
					} catch(Exception structureNotFoundExport){
						try{
							Structure structure = imports.getStructure(key);
							Map<String, String> resultStructure = new HashMap<>();
							for(int i = 0; i < structure.getFieldCount(); i++){
								String fieldName = structure.getField(i).getName();
								Object valueObject = structure.getValue(fieldName);
								String fieldValue = Utils.getValue(valueObject);
								resultStructure.put(fieldName, fieldValue);
							}
							result.put(key, resultStructure);
						} catch(Exception e){
							structureNotFoundExport.printStackTrace();
						}
					}
					break;
				case FIELD:
					try{
						Object value = exports.getValue(key);
						result.put(key, value);
					} catch(Exception fieldNotFoundExport){
						try{
							Object value = imports.getValue(key);
							result.put(key, value);
						} catch(Exception e){
							e.printStackTrace();
						}
					}
					break;
				default:
					throw new IllegalStateException("ParameterType not caught (" + Bapi.class.getSimpleName() + ".execute(..))");
			}
		}
		
		return result;
	}
	
	/**
	 * erzeugt ein Function-Objekt, bei dem die übergebenen Parameter gesetzt sind
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Function buildFunction(Map<String, Object> params){
		Function function = this.getFunctionTemplate().getFunction();
		ParameterList tableParams = function.getTableParameterList();
		ParameterList importParams = function.getImportParameterList();
		
		for(String paramKey : params.keySet()){
			Object value = params.get(paramKey);
			
			if(value instanceof List){
				Table table = tableParams.getTable(paramKey.toUpperCase());
				List<Map<String, String>> tableContent = (List<Map<String, String>>) value;
				
				for(Map<String, String> row : tableContent){
					table.appendRow();
					for(String column : row.keySet()){
						String columnContent = row.get(column);
						table.setValue(columnContent, column);
					}
				}
				
			} else if(value instanceof String){
				
				importParams.setValue((String) value, paramKey);
				
			} else if(value instanceof Map){
				Map<String, String> structureContent = (Map<String, String>) value;
				
				Structure structure = importParams.getStructure(paramKey);
				for(String column : structureContent.keySet()){
					String columnContent = structureContent.get(column);
					structure.setValue(columnContent, column);
				}
				
			}
		}
		return function;
	}
	
	protected abstract Map<String, ParameterType> getExportParameterTypes();
}
