package andoridserver.database;

import java.util.Date;

/**
 *
 * @author Jack
 */
public class DBAsk implements DBAskInterface{
    
    Date date= new Date();
    public int getDate(){
        return date.getDay();
    }
    
    private String getStringDay(){
        String str=new String();
        switch(getDate()){
            case 0: 
                str="saturday";
                break;
            case 1:
                str="monday";
                break;
            case 2:
                str="tuesday";
                break;
            case 3:
                str="wednesday";
                break;
            case 4:
                str="thursday";
                break;
            case 5:
                str="friday";
                break;
            case 6:
                str="satuday";
                break;
        }
        return str;
    }
    private String getServiceId(){
        
        String sql="SELECT service_id" +
                   "FROM    calendar" +
                   "WHERE   "+getStringDay()+"='1'";
        
        return sql;
    }
    
    public String CreaViewGetServiceId(){
        String sql;
        sql = "CREATE VIEW serviceIdGiorno AS"
                + ""+getServiceId()+";";
        return sql;
    }
    
    public String DropViewServiceId(){
        String sql;
        sql ="DROP VIEW derviceIdGiorno AS"
                + ""+getServiceId()+";";
        return sql;
    }
    
    //devo rivederle
    @Override
    public String dbLinee(){
        String str="SELECT route_short_name\n"
                +  "FROM routes r,trips t\n"
                +  "WHERE r.route_id=t.route_id AND"
                +  "t.service_id IN("+getServiceId()+")"
                +  "GROUP BY route_short_name\n"
                +  "ORDER BY route_short_name;";
        
        return str;
                
    }
    
    /*inserisco il nome o numero dellla linea*/
    @Override
    public String dbStops(String linea){
        String day=getStringDay();
        String str="SELECT s.stop_id,s.stop_name" +
"                    FROM trips tr,stop_times st,stops s,routes r" +
"                    WHERE r.route_id=tr.route_id AND" +
"                    st.stop_id=s.stop_id   AND" +
"                    st.trip_id=tr.trip_id   AND" +
"                    r.route_short_name='"+linea+"'  AND" +
"                    tr.service_id IN(SELECT service_id" +
"          FROM  calendar" +
"          WHERE  "+day+"='1')" +
"                    GROUP BY s.stop_id,s.stop_name" +
"                    ORDER BY s.stop_name;";
        
        return str;
    }
 
    @Override
    public String dbTime(String stop,String linea){
        
        String str="SELECT st.trip_id,st.arrival_time,st.departure_time"
                +    "FROM trips tr,stop_times st,stops s,routes r"
                +    "WHERE r.route_id=tr.route_id AND"
                +    "st.stop_id=s.stop_id   AND"
                +    "st.trip_id=tr.trip_id   AND"
                +    "r.route_short_name='"+linea+"'  AND"
                +   "tr.service_id IN("+ getServiceId() +")"+
                    "s.stop_name='"+stop+"';";
        return str;
    }
    
    public String dbTempoLinea(int trip_id){
        String str="SELECT st.trip_id,st.arrival_time,st.departure_time" +
                    "FROM stop_times st"+
                    "WHERE st.trip_id='"+trip_id+"';";
        return str;
    }
    
    /*la rivedro più anvanti*/
    @Override
    public String dbFindLinesAtBusStop( String stopId ){
        
        String str="SELECT route_short_name,route_long_name"
                + "FROM stop_times st,trips t,routes r"
                + "WHERE st.trip_id=t.trip_id AND"
                + "t.route_id=r.route_id"
                + "st.stop_id="+stopId+""
                + "ORDER BY stop_sequence";
        
        return str;
    }
    
    @Override
    public String dbTimesDelay(String linea,String stop){
        String str="SELECT st.departure_time"
                +    "FROM trips tr,stop_times st,stops s,routes r"
+    "WHERE r.route_id=tr.route_id AND"
                +    "st.stop_id=s.stop_id   AND"
                +    "st.trip_id=tr.trip_id   AND"
                +    "r.route_short_name='"+linea+"'AND"
                +    "s.stop_name='"+stop+"' "
                +   "tr.service_id IN ("+getServiceId()+")";
        return str;
    };
    
    
    public String dbSetDelay(String arrival_time,String delay_time,String line,String stop){
        String str="UPDATE stop_times\n"
                +    "SET departure_time='"+delay_time+"'\n"
                +    "WHERE arrival_time='"+arrival_time+"' AND\n"
                +    "trip_id IN (SELECT st.trip_id\n"
                +    "           FROM trips tr,stop_times st,stops s,routes r\n"
                +    "           WHERE r.route_id=tr.route_id AND\n"
                +    "           st.stop_id=s.stop_id   AND\n"
                +    "           st.trip_id=tr.trip_id   AND\n"
                +    "           r.route_short_name='"+line+"'  AND\n"
                +    "    s.stop_name='"+stop+"'"
                + "             tr.service_id IN("+getServiceId()+")";
        return str;
    }
}