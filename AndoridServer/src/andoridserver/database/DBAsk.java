/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

/**
 *
 * @author Utente
 */
public class DBAsk {
    //devo rivederle
    public String dbLinee(String x){
        String str="SELECT(a.route_short_name,a.route_lomg_name)"
                + "FROM routes a,routes b "
                + "WHERE a.route_lomg_name=b.route_long_name"
                + "ORDER BY a.route_short_name";
        
        return str;
                
    }
    
    public String dbStops(int linea){
        String str="SELECT DISTINCT(s.stop_id,s.stop_code)"
                + "FROM trips tr,stop_times st,stop s"
                + "WHERE tr.route_id="+linea+"AND"
                + "st.stop_id=s.stop_id AND"
                + "st.trip_id=tr.trip_id"
                + "ORDER BY stop_sequence";
        return str;
    }
    
    public String dbTime(String stop,String linea){
        String str="SELECT"
                + "FROM "
                + "st.stop_id=s.stop_id AND"
                + "st.trip_id=tr.trip_id"
                + "ORDER BY stop_sequence";
        return str;
    }
    
    public String dbStopBus(String linea,String stop){
        return "";
    }
    
    
}
