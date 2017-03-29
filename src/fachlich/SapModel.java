package fachlich;

import java.util.Map;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Repository;

import fachlich.BapiFactory.BapiType;

public class SapModel {
	
	private static final String SERVER = "/H/saprouter.hcc.in.tum.de/S/3299/H/i28lp1";
	private static final String LANGUAGE = "DE";
	private static final String SYSTEM_NUMBER = "28";
	
	private Client connection;
	
	public SapModel(String mandant, String user, String password) {
		super();
		this.connect(mandant, user, password, LANGUAGE, SERVER, SYSTEM_NUMBER);
	}
	
	private void connect(String mandant, String user, String password, String language, String server,
			String systemNumber) {
		System.out.println("Verbinde mit SAP ... ");
		
		this.setConnection(JCO.createClient(mandant, user, password, language, server, systemNumber));
		this.getConnection().connect();
		
		System.out.println("Verbindung erfolgreich");
		
		BapiFactory.setRepository(new Repository("ERPKingsRepository", this.getConnection()));
	}
	
	protected Client getConnection() {
		return connection;
	}
	
	protected void setConnection(Client connection) {
		this.connection = connection;
	}
	
	/**
	 * Aufruf der BAPI
	 * @param bapi
	 * @param parameters ist nach folgender Hierarchie aufzubauen:<br/>
	 * 	<ul>
	 * 		<li>{@code HashMap<parameterName, parameterWert>}<br/></li>
	 * 		<ul>
	 * 				<li>falls parameterWert nur ein Wert ist, ist parameterWert vom Typ {@code String}</li>
	 * 				<li>falls parameterWert eine Structure ist, ist parameterWert vom Typ {@code HashMap<feldName, feldWert>}</li>
	 * 				<li>falls parameterWert eine Tabelle ist, ist parameterWert vom Typ {@code ArrayList<HashMap<spaltenName, spaltenWert>>},
	 * 					wobei jede HashMap einer Zeile der Tabelle entspricht</li>
	 * 		</ul>
	 * 	</ul>
	 * @return Exportparameter der BAPI, aufgebaut nach demselbem Schema wie parameters
	 */
	public Map<String, Object> executeBapi(BapiType bapi, Map<String, Object> parameters){
		Map<String, Object> result = BapiFactory.getBapi(bapi).execute(parameters, this.getConnection());
		return result;
	}
	
	/**
	 * zum Testen
	 * @param bapiName relevante Werte:
	 * 	<ul>
	 * 		<li>BAPI_MATERIAL_GET_DETAIL</li>
	 * 		<li>BAPI_MATERIAL_GET_DETAIL</li>
	 * 		<li>BAPI_MATERIAL_GETLIST</li>
	 * 	</ul>
	 */
	void getParameterNames(String bapiName){
		Function function = BapiFactory.getRepository().getFunctionTemplate(bapiName).getFunction();
		ParameterList imports = function.getImportParameterList();
		System.out.println("Import:");
		for(int i = 0; i < imports.getFieldCount(); i++){
			System.out.println("\t" + imports.getName(i));
		}
		ParameterList exports = function.getExportParameterList();
		System.out.println("Export (vor Ausführung):");
		for(int i = 0; i < exports.getFieldCount(); i++){
			System.out.println("\t" + exports.getName(i));
		}
		ParameterList tables = function.getTableParameterList();
		System.out.println("Tables:");
		for(int i = 0; i < tables.getFieldCount(); i++){
			System.out.println("\t" + tables.getName(i));
		}
	}
	
	
}
