/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

import andoridserver.androidData.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 *
 * @author Nicola
 */
public class DBConnector implements DBInterface {
    //singletone, una connessione al db per tutte le richieste
    //pro non devo aprire più connessioni diferenti per gestire le richieste
    //la esecuzione della query mi restituisce un object quindi factory
    //contro la velocità delle risposte è minore

    private Connection con;
    private static DBConnector ref = null;
    public static int POSTGRESPORT;
    public static String DRIVER = "postgresql";
    public static String ADDRESS;

    private DBConnector() {
    }

    public static DBConnector getIstance() {
        if (ref == null) {
            ref = new DBConnector();
        }
        return ref;
    }

    @Override
    public boolean connect(String dbType, String dbAdress, int dbPort, String resources) {
        boolean connected = false;
        try {
            Class.forName("org.postgresql.Driver");
            //"jdbc:postgresql://localhost:5432/postgres","postgres"
            String driver = "jdbc:" + dbType + "://" + dbAdress + ":" + dbPort + "/" + resources;//"jdbc:postgresql://52.36.66.44:5432/nicola";
            con = DriverManager.getConnection(driver, "nicola", "nicola");
            connected = true;
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

    /*da rivedere*/
    @Override
    public String compileQuery(String[] select, String[] from, String[] where, String[] optionalParam) {
        String query = "SELECT";
        for (int i = 0; i < select.length; i++) {
            if (i < select.length - 1) {
                query += " " + select[i] + ",";
            } else {
                query += " " + select[i] + " ";
            }
        }
        query += "FROM";
        for (int i = 0; i < from.length; i++) {
            if (i < from.length - 1) {
                query += " " + from[i] + ",";
            } else {
                query += " " + from[i] + " ";
            }
        }
        query += "WHERE";
        for (int i = 0; i < where.length; i++) {
            if (i < where.length - 1) {
                query += " " + where[i] + ",";
            } else {
                query += " " + where[i] + " ";
            }
        }

        return query;
    }

    public boolean updateTable() {
        Statement statement;
        try {
            statement = con.createStatement();
            String sql = "CREATE TABLE agency(\n"
                    + "	Agency_id text,\n"
                    + "	Agency_name text,\n"
                    + "	Agency_url text,\n"
                    + "	Agency_timezone text,\n"
                    + "	Agency_lang text,\n"
                    + "	Agency_phone varchar(10),\n"
                    + "	Agency_fare_url text,\n"
                    + "	PRIMARY KEY(Agency_id)\n"
                    + ");\n";
            int result = statement.executeUpdate(sql);

            sql = "CREATE TABLE calendar(\n"
                    + "	service_id text,\n"
                    + "	monday integer, \n"
                    + "	tuesday integer,\n"
                    + "	wednesday integer,\n"
                    + "	thursday integer,\n"
                    + "	friday integer,\n"
                    + "	saturday integer,\n"
                    + "	sunday integer,\n"
                    + "	start_date text,\n"
                    + "	end_date text,\n"
                    + "	PRIMARY KEY(service_id)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE calendar_dates(\n"
                    + "	service_id text,\n"
                    + "	end_date text,\n"
                    + "	exception_type text\n"
                    + "	--FOREIGN KEY(service_id) REFERENCES calendar(service_id)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE routes(\n"
                    + "	route_id integer,\n"
                    + "	agency_id text,\n"
                    + "	route_short_name varchar(3),\n"
                    + "	route_long_name text,\n"
                    + "	route_desc text,\n"
                    + "	route_type integer,\n"
                    + "	route_url text,\n"
                    + "	route_color varchar(6),\n"
                    + "	route_text_color varchar(6),\n"
                    + "	--FOREIGN KEY(agency_id) REFERENCES agency(agency_id),\n"
                    + "	PRIMARY KEY(route_id)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE stops(\n"
                    + "	stop_id integer,\n"
                    + "	stop_code text,\n"
                    + "	stop_name text,\n"
                    + "	stop_desc text,\n"
                    + "	stop_lat double precision,\n"
                    + "	stop_lon double precision,\n"
                    + "	--in caso correggere tipo\n"
                    + "	zone_id text,\n"
                    + "	stop_url text,\n"
                    + "	location_type text,\n"
                    + "	parent_station text,\n"
                    + "	stop_timezone time,\n"
                    + "	wheelchair_boarding text,\n"
                    + "	--\n"
                    + "	PRIMARY KEY(stop_id)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE trips(\n"
                    + "	route_id integer,\n"
                    + "	service_id text,\n"
                    + "	trip_id integer,\n"
                    + "	trip_headsign text,\n"
                    + "	--da controllare il tipo\n"
                    + "	trip_short_name text,\n"
                    + "	--\n"
                    + "	direction_id integer,\n"
                    + "	block_id integer,\n"
                    + "	shape_id text,\n"
                    + "	--da controllare il tipo\n"
                    + "	wheelchair_accessible text,\n"
                    + "	--\n"
                    + "	PRIMARY KEY(trip_id)\n"
                    + "	--FOREIGN KEY(route_id) REFERENCES routes(route_id),\n"
                    + "	--FOREIGN KEY(service_id) REFERENCES calendar(service_id)\n"
                    + "	--bisogna modificare la chiave primari di shapes\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE stop_times(\n"
                    + "	trip_id integer,\n"
                    + "	arrival_time time,\n"
                    + "	departure_time time,\n"
                    + "	stop_id integer,\n"
                    + "	stop_sequence integer,\n"
                    + "	stop_headsign text,\n"
                    + "	pickup_type integer,\n"
                    + "	--in caso correggere tipo\n"
                    + "	drop_off_type text,\n"
                    + "	shape_dist_traveled text\n"
                    + "	--\n"
                    + "	--FOREIGN KEY(stop_id) REFERENCES stops(stop_id),\n"
                    + "	--FOREIGN KEY(trip_id) REFERENCES trips(trip_id)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE shapes(\n"
                    + "	shape_id text,\n"
                    + "	shape_pt_lat double precision,\n"
                    + "	shape_pt_lon double precision,\n"
                    + "	shape_pt_sequence integer,\n"
                    + "	shape_dist_traveled double precision,\n"
                    + "	PRIMARY KEY(shape_id,shape_pt_sequence)\n"
                    + ")";
            result = statement.executeUpdate(sql);

            sql = "CREATE TABLE trips_shape(\n"
                    + "	trip_id integer,\n"
                    + "	shape_id text,\n"
                    + "	shape_pt_sequence integer,\n"
                    + "	FOREIGN KEY(trip_id) REFERENCES trips(trip_id),\n"
                    + "	FOREIGN KEY(shape_id,shape_pt_sequence) REFERENCES shapes(shape_id,shape_pt_sequence),\n"
                    + "	PRIMARY KEY(trip_id,shape_id,shape_pt_sequence)\n"
                    + ")";
            result = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean dropDB() {
        try {
            Statement statement = con.createStatement();
            String sql = "DROP TABLE agency CASCADE;";
            int result = statement.executeUpdate(sql);
            sql = "DROP TABLE calendar CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE calendar_dates CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE routes CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE shapes CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE stops CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE trips CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE stop_times CASCADE;";
            result = statement.executeUpdate(sql);
            sql = "DROP TABLE trips_shape CASCADE;";
            result = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /*da sistemere per vesatilita*/
    public boolean updateDatabaseServer(String insertInto) {
        boolean ret ;//= false;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(insertInto);
            resultSet.toString();
            ret = true;
        } catch (SQLException ex) {
            ret = false;
        }
        return ret;
    }

    @Override
    public AndroidDataInterface executeQuery(String compiledQuery) {
        AndroidDataInterface adi = null;
        DBAsk ask = new DBAsk();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android

            Array arr = null;
            while (resultSet.next()) {
                adi.addData("" + resultSet.getInt("intero"));
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

    @Override
    public String compileQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AndroidDataInterface executeTimetable(String compiledQuery) {
        AndroidDataInterface adi = new AndroidOrariData();
        String s = "";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android
            while (resultSet.next()) {
                s = resultSet.getString("arrival_time");
                //System.out.println(s);
                adi.addData(s);
            }
            System.out.println(adi.getDataAsList().toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adi;
    }

    @Override
    public AndroidDataInterface executeLines(String compiledQuery) {
        AndroidDataInterface adi = new AndroidDataLinee();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android
            while (resultSet.next()) {
                adi.addData(resultSet.getString("route_short_name")+" "+resultSet.getString("trip_headsign"));
            }
            System.out.println(adi.getDataAsList().toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adi;
    }

    /*TODO: fare la parte legata al db*/
    @Override
    public AndroidDataInterface executeDealay(String compiledQuery) {
        AndroidDataInterface adi = new AndroidDataDelay();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android
            while (resultSet.next()) {
                adi.addData(resultSet.getString("departure_time"));
            }
            System.out.println(adi.getDataAsList().toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adi;
    }

    @Override
    public AndroidDataInterface executeAllStopNames(String compiledQuery) {
        AndroidDataInterface adi = new AndroidDataStops();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(compiledQuery);
            //adesso devo convertire il mio result set nell'oggetto per android
            while (resultSet.next()) {
                adi.addData(resultSet.getString("stop_name"));
            }
            System.out.println(adi.getDataAsList().toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adi;
    }

    public static void main(String[] args) {
        DBConnector db = DBConnector.getIstance();
        System.out.println("Trying to connect...");
        //System.out.println(db.connect("postgresql", "52.33.218.151", 5432, "autobus"));
        //System.out.println(db.executeQuery("SELECT * FROM nick").getDataAsList().get(0));     
       
        System.out.println(db.connect("postgresql", "52.33.218.151", 5432, "autobus"));
        /*if (!db.dropDB()) {
            System.out.println("costruzione non riouscita");
        }*/
      //  System.out.println(db.connect("postgresql", "52.33.218.151", 5432, "battelli"));
        if (!db.updateTable()) {
            System.out.println("costruzione non riouscita");
        }
        /*
        if( !db.dropDB() ){
            System.out.println("le tabelle sono state tolte");
        }else{
            System.out.println("le tabelle sono state eliminate");
        }
         */
        db.disconnect();
    }

}