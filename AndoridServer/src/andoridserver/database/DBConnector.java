/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

import andoridserver.androidData.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nicola
 */
public class DBConnector implements DBInterface{
    //singletone, una connessione al db per tutte le richieste
    //pro non devo aprire più connessioni diferenti per gestire le richieste
    //la esecuzione della query mi restituisce un object quindi factory
    //contro la velocità delle risposte è minore
    
     
    private Connection con;
    private static DBConnector ref=null;
    public static final int POSTGRESPORT = 5492;
    
    private DBConnector(){
    }
    
    
    public static DBConnector getIstance(){
        if(ref==null){
            ref=new DBConnector();
        }
        return ref;
    }

    @Override
    public boolean connect(String dbType, String dbAdress, int dbPort, String resources) {
        boolean connected = false;
        try {
            Class.forName("org.postgresql.Driver");
            //"jdbc:postgresql://localhost:5432/postgres","postgres"
            String driver="jdbc:"+dbType+"://"+dbAdress+":"+dbPort+"/"+resources;//"jdbc:postgresql://52.36.66.44:5432/nicola";
            con = DriverManager.getConnection(driver,"nicola", "nicola");
            connected=true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connected;
    }

    @Override
    public boolean connect(String dbTipe, String dbAdress, int dbPort, String[] otherParams) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String compileQuery(String[] select, String[] from, String[] where, String[] optionalParam) {
        String query="SELECT";
        for(int i=0;i<select.length;i++){
            if(i<select.length-1){
                query+=" "+select[i]+",";
            }else{
                query+=" "+select[i]+" ";
            }    
        }
        query+="FROM";
        for(int i=0;i<from.length;i++){
            if(i<from.length-1){
                query+=" "+from[i]+",";
            }else{
                query+=" "+from[i]+" ";
            }  
        }
        query+="WHERE";
        for(int i=0;i<where.length;i++){
            if(i<where.length-1){
                query+=" "+where[i]+",";
            }else{
                query+=" "+where[i]+" ";
            }  
        }
        
        return query;
    }

    @Override
    public String compileQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /*da sistemere per vesatilita*/
    @Override
    public AndroidDataInterface executeQuery(String compiledQuery) {
        AndroidDataInterface adi = null;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android
           adi = new AndroidOrariData();
           Array arr=null;
            while (resultSet.next()) {
                adi.addData(""+resultSet.getInt("intero"));
                //arr = resultSet.getArray("intero");
                //System.out.println(arr.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adi;
    }
    
    @Override
    public boolean updateDelayQuery(String lineNumber, int minRitardo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static void main(String[] args){
        DBConnector db = DBConnector.getIstance();
        System.out.println("Trying to connect...");
        System.out.println(db.connect("postgresql", "52.36.66.44", 5432, "nicola"));
        System.out.println(db.executeQuery("SELECT * FROM nick").getDataAsList().get(0));     
        System.out.println("Disconecting!! Bye");
        db.disconnect();
    
    
    }

 
}
