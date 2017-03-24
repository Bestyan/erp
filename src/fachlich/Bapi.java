package fachlich;

import java.util.ArrayList;
import java.util.HashMap;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Structure;
import com.sap.mw.jco.JCO.Table;

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
	public HashMap<String, Object> execute(HashMap<String, Object> params, Client connection){
		Function function = this.buildFunction(params);
		connection.execute(function);
		return this.repackageExportParameters(function);
	}
	
	protected HashMap<String, Object> repackageExportParameters(Function function) {
		HashMap<String, Object> result = new HashMap<>();
		
		ParameterList tables = function.getTableParameterList();
		ParameterList imports = function.getImportParameterList();
		ParameterList exports = function.getExportParameterList();
		
		HashMap<String, ParameterType> exportTypes = this.getExportParameterTypes();
		for(String key : exportTypes.keySet()){
			ParameterType type = exportTypes.get(key);
			switch(type){
				case TABLE:
					try{
						Table table = tables.getTable(key);
						if(table.getNumRows() > 0){
							ArrayList<HashMap<String, String>> rows = new ArrayList<>();
							do{
								HashMap<String, String> row = new HashMap<>();
								for(int i = 0; i < table.getFieldCount(); i++){
									String fieldName = table.getField(i).getName();
									String fieldValue = (String) table.getValue(fieldName);
									row.put(fieldName, fieldValue);
								}
								rows.add(row);
							}while(table.nextRow());
							result.put(key, rows);
						}
						break;
					} catch(Exception tableNotFound){
						//do nothing, try structure in next case
					}
					//no break here!
				case STRUCTURE:
					try{
						Structure structure = exports.getStructure(key);
						HashMap<String, String> resultStructure = new HashMap<>();
						for(int i = 0; i < structure.getFieldCount(); i++){
							String fieldName = structure.getField(i).getName();
							String fieldValue = (String) structure.getValue(fieldName);
							resultStructure.put(fieldName, fieldValue);
						}
						result.put(key, resultStructure);
					} catch(Exception structureNotFoundExport){
						try{
							Structure structure = imports.getStructure(key);
							HashMap<String, String> resultStructure = new HashMap<>();
							for(int i = 0; i < structure.getFieldCount(); i++){
								String fieldName = structure.getField(i).getName();
								String fieldValue = (String) structure.getValue(fieldName);
								resultStructure.put(fieldName, fieldValue);
							}
							result.put(key, resultStructure);
						} catch(Exception e){
							e.printStackTrace();
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
	protected Function buildFunction(HashMap<String, Object> params){
		Function function = this.getFunctionTemplate().getFunction();
		ParameterList tableParams = function.getTableParameterList();
		ParameterList importParams = function.getImportParameterList();
		
		for(String paramKey : params.keySet()){
			Object value = params.get(paramKey);
			
			if(value instanceof ArrayList){
				Table table = tableParams.getTable(paramKey.toUpperCase());
				ArrayList<HashMap<String, String>> tableContent = (ArrayList<HashMap<String, String>>) value;
				
				for(HashMap<String, String> row : tableContent){
					table.appendRow();
					for(String column : row.keySet()){
						String columnContent = row.get(column);
						table.setValue(columnContent, column);
					}
				}
				
			} else if(value instanceof String){
				
				importParams.setValue((String) value, paramKey);
				
			} else if(value instanceof HashMap){
				HashMap<String, String> structureContent = (HashMap<String, String>) value;
				
				Structure structure = importParams.getStructure(paramKey);
				for(String column : structureContent.keySet()){
					String columnContent = structureContent.get(column);
					structure.setValue(columnContent, column);
				}
				
			}
		}
		return function;
	}
	
	protected abstract HashMap<String, ParameterType> getExportParameterTypes();
}
