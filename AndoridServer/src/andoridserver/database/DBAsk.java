/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

/**
 *
 * @author Jack
 */
public class DBAsk implements DBAskInterface{
    
    //devo rivederle
    @Override
    public String dbLinee(){
        String str="SELECT(a.route_short_name,a.route_lomg_name)"
                + "FROM routes a,routes b "
                + "WHERE a.route_lomg_name=b.route_long_name"
                + "ORDER BY a.route_short_name";
        
        return str;
                
    }
    
    @Override
    public String dbStops(String linea){
        String str="SELECT DISTINCT(s.stop_id,s.stop_code)"
                + "FROM trips tr,stop_times st,stop s"
                + "WHERE tr.route_id="+linea+"AND"
                + "st.stop_id=s.stop_id AND"
                + "st.trip_id=tr.trip_id"
                + "ORDER BY stop_sequence";
        return str;
    }
    
    @Override
    public String dbTime(String stop,String linea){
        String str="SELECT"
                + "FROM trips tr,stop_times st"
                + "st.stop_id=s.stop_id AND"
                + "st.trip_id=tr.trip_id"
                + "st.stop_id="+stop+""
                + "tr.route_id="+linea+""
                + "ORDER BY stop_sequence";
        return str;
    }
    
    @Override
    public String dbFindLinesAtBusStop( String stopId ){
        
        String str="SELECT route_short_name,route_long_name"
                + "FROM stop_times st,trips t,routes r"
                + "WHERE st.trip_id=t.trip_id AND"
                + "t.route_id=r.route_id"
                + "st.stop_id="+stopId+""
                + "ORDER BY stop_sequence";
        
        return "str";
    }
    
    @Override
    public String dbTimesDelay(String line,String stop){
        String str=null;
        return str;
    };
    
    @Override
    public String dbSetDelay(String delay,String line,String stop){
        String str=null;
        return str;
    }
    
}