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
    
    public String dbNomeLineeDatoNumero(String numeroLinea);
    
    public String dbStops(String linea,String headSign);
    
    public String dbTime(String stop,String linea,String headSign);
    
    public String dbFindLinesAtBusStop(String stopId);
    
    public String dbTimesDelay(String linea,String stop,String deadSign);
    
    public String dbSetDelay(String arrival_time,String delay_time,String line,String stop,String headSign);
    
}
