
import com.sap.mw.jco.*;
import com.sap.mw.jco.JCO.Repository;

public class Verbindungstest {
	JCO.Client mConnection;

	public Verbindungstest() {
		openConnectionToSAP("915", "adv-demo", "654321", "DE", "/H/saprouter.hcc.in.tum.de/S/3299/H/i28lp1", "28");

		connectionAttributes();
		closeConnectionToSAP();
	}

	public void connectionAttributes() {
//		System.out.println(mConnection.getAttributes());
		
		Repository mRepository = new JCO.Repository( "MyRepository", mConnection );
		JCO.Function function = this.createFunction( mRepository, "BAPI_CUSTOMER_GETLIST" );
        function.getImportParameterList().setValue( 20, "MAXROWS" );
        JCO.Table tblRange = function.getTableParameterList().getTable( "IDRANGE" );
        tblRange.appendRow();
        tblRange.setValue( "I",          "SIGN"   );
        tblRange.setValue( "BT",         "OPTION" );
        tblRange.setValue( 0, "LOW"    );
        tblRange.setValue( "9999999999", "HIGH"   );
        mConnection.execute( function );
//        JCO.Structure returnStructure = function.getExportParameterList().getStructure( "RETURN" );
//        System.out.println("BLUB: " + returnStructure.getString(1000));
        JCO.Table tblAdr = function.getTableParameterList().getTable( "ADDRESSDATA" );
        for(int i=0; i<tblAdr.getNumRows(); i++){
        	tblAdr.setRow( i );
        	System.out.println(tblAdr.getString( "NAME"       ));
        }
        
//		System.out.println(mConnection.ProjectDefinition.Getlist());
	}

	public void openConnectionToSAP(String clientSAP, String login, String passwort, String language, String server,
			String systemNumber) {
		// Verbindungsdaten
		System.out.print("Verbinde mich mit SAP ... ");
		mConnection = JCO.createClient(clientSAP, login, passwort, language, server, systemNumber);
		// Verbindung herstellen
		mConnection.connect();
		System.out.println("verbunden");
		System.out.println("-------------------------------------------");
	}

	public void closeConnectionToSAP() {
		mConnection.disconnect();
	}

	public static void main(String[] args) {
		Verbindungstest app;
		app = new Verbindungstest();
	}

	
    private JCO.Function createFunction( IRepository mRepository, String name )
    {
      try {
        return mRepository.getFunctionTemplate( name.toUpperCase() ).getFunction();
      } catch( Exception ex ) {/*ok*/}
      return null;
    }
}// Ende der Klasse
