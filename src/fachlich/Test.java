package fachlich;

import com.sap.mw.jco.JCO;

public class Test {
	JCO.Client mConnection;
	
	public Test()
	{
		this.openConnectionToSAP("915", "GBI-0-18", "123456", "DE", "/H/saprouter.hcc.in.tum.de/S/3299/H/i28lp1", "28");
		this.connectionAttributes();
		this.closeConnectionToSAP();
	}
	
	public void connectionAttributes() {
		System.out.println(mConnection.getAttributes());
	}
	
	public void openConnectionToSAP(String clientSAP, String login, String passwort, String language, String server, String systemNumber)
	{
		//Verbindungsdaten
		System.out.println("Verbinde mich mit SAP ... ");
		mConnection = JCO.createClient(clientSAP,
				login,
				passwort,
				language,
				server,
				systemNumber);
		//Verbindung herstellen
		mConnection.connect();
		System.out.println("verbunden");
		System.out.println("-------------------------------------------");
	}
	
	
	public void closeConnectionToSAP()
	{
		mConnection.disconnect();
	}
	
	public static void main(String [] args)
	{
		new Test();
	}
}
