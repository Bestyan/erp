package fachlich;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Repository;
import com.sap.mw.jco.JCO.Table;

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
	
	public Client getConnection() {
		return connection;
	}
	
	public void setConnection(Client connection) {
		this.connection = connection;
	}
	
	public void testFunction(){
		Function function = BapiFactory.getFunction(BapiType.GETLIST);
		ParameterList params = function.getTableParameterList();
		Table matnrSelection = params.getTable("MATNRSELECTION");
		matnrSelection.appendRow();
		matnrSelection.setValue("I", "SIGN");
		matnrSelection.setValue("CP", "OPTION");
		matnrSelection.setValue("ERPKÖ*", "MATNR_LOW");
		matnrSelection.setValue("", "MATNR_HIGH");
		this.getConnection().execute(function);
		Table export = params.getTable("MATNRLIST");
		System.out.println("Anzahl Einträge:" + export.getFieldCount());
		do{
			for(int i = 0; i < export.getFieldCount(); i++){
				System.out.println(export.getValue(i));
			}
		}while(export.nextRow());
	}
	
	
}
