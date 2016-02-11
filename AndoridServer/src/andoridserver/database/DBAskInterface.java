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
public interface DBAskInterface {
    
    public String dbLinee();
    
    public String dbStops(String linea);
    
    public String dbTime(String stop,String linea);
    
    public String dbFindLinesAtBusStop(String stopId);
    
    public String dbTimesDelay(String line,String stop);
    
    public String dbSetDelay(String delay,String line,String stop);
    
}
