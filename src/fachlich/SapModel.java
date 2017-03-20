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
	private Repository repository;
	
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
		
		this.setRepository(new Repository("ERPKingsRepository", this.getConnection()));
		BapiFactory.setRepository(this.getRepository());
	}
	
	public Client getConnection() {
		return connection;
	}
	
	public void setConnection(Client connection) {
		this.connection = connection;
	}
	
	public Repository getRepository() {
		return repository;
	}
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public void testFunction(){
		Function function = BapiFactory.getFunction(BapiType.LISTE);
		function.getImportParameterList();
		Table matnr = function.getImportParameterList().getTable("BAPIMATRAM");
		matnr.setValue("I", "SIGN");
		matnr.setValue("CP", "OPTION");
		matnr.setValue("ERPKÖ*", "MATNR_LOW");
		matnr.setValue("", "MATNR_HIGH");
		this.getConnection().execute(function);
		ParameterList exportParams = function.getExportParameterList();
		int i = 0;
	}
}
