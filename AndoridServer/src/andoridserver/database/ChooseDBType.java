/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.database;

/**
 *
 * @author Nicola
 */
public class ChooseDBType implements DatabaseTypes {
    
    private String[] agency={"text","text","text","text","text","text","text"};
    private String[] calendar={"text","int","int","int","int","int","int","int","text","text"};
    private String[] calendar_dates={"text","text","text"};
    private String[] routes={"int","text","text","text","text","int","text","text","text"};
    private String[] shapes={"text","int","double","double","int","double"};
    private String[] stops={"int","text","text","text","double","double","text","text","text","text","text","text"};
    private String[] trips={"int","text","int","text","text","int","int","text","text"};
    private String[] stop_times={"int","text","text","int","int","text","int","text","text"};
    
    @Override
    public String getType(int fieldPosition, String tableName) {
        String ret=new String("");
        switch(tableName){
            case "agency": 
                ret=agency[fieldPosition];
                break;
            case "calendar": 
                ret=calendar[fieldPosition];
                break;
            case "calendar_dates": 
                ret=calendar_dates[fieldPosition];
                break;
            case "routes": 
                //System.out.println(fieldPosition);
               // try{
                    ret=routes[fieldPosition];
                //}catch(ArrayIndexOutOfBoundsException ex){
                
               // }
                break;
            case "shapes": 
                ret=shapes[fieldPosition];
                break;
            case "stops": 
                ret=stops[fieldPosition];
                break;
            case "trips": 
                ret=trips[fieldPosition];
                break;
            case "stop_times": 
                ret=stop_times[fieldPosition];
                break;
            default:
                throw new IllegalArgumentException(tableName+": table not supported");
        }
        return ret;
    }
    
}
