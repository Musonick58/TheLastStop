/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

/**
 *
 * @author Nicola
 */
public class ParseString {
    
    private String toParse;
    private String delimiters; //delimitatori
    private String[] splitedElement;
    private int tokenPos=0;

    public ParseString(String toParse, String delimiters) {
        this.toParse = toParse;
        this.delimiters = delimiters;
        this.splitedElement = tokens();
    }

    public String getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(String delimiters) {
        this.delimiters = delimiters;
    }
    
    
    private String[] tokens(){
        String[] ret = null;
        ret = this.toParse.replace(",,", ", ,").split(delimiters);
        return ret;
    }
    
    public String nextToken(){
        
        return splitedElement[tokenPos++];
    
    }
    
    public boolean hasMoreTokens(){
        return tokenPos < splitedElement.length;
    }
    
    public static void main(String... args){
        String str="stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station,stop_timezone,wheelchair_boarding";
        ParseString ps = new ParseString(str, ",");
       int i=0;
        while(ps.hasMoreTokens())
            System.out.println("token:"+ps.nextToken()+" "+i++);
        
        System.out.println(ps.tokenPos);
    
    }
    
}
