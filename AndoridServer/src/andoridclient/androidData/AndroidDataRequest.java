/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridclient.androidData;

/**
 *
 * @author nichi
 */
interface AndroidDataRequest {
    
    //per la richiesta potete mandarmi una stringa,
    //formalmente potrebbe essere formatata così
    //DataRequest:AllLinesNumber
    //DataRequest:LinesNumber Like (numero/nome linea) (bus/navig)
    //DataRequest:TimeTable Like (numero/nome linea) (bus/navig)
    //DataRequest:AllStopName
    //DataRequest:Delay Like (numero/nome linea) (bus/navig)
    //Es: DataRequest: Delay Like 2 bus
    
    
    
    
    public void askLines(); //tutte le linee
      
    public void askLines(String prefered); //la linea preferita
    
    public void askTimeTable(String linesNumber); //orario della linea selezionata
    
    public void askAllStops(); //lista di tutte le fermate!
    
    public void askAvgDelay(String linesNumber); //orario medio di ritardo
    
    
    
}
