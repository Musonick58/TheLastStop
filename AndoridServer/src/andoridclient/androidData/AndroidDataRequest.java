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
    //DataRequest:AllLinesNumber restituisce la lista di tutte le fermate
    //DataRequest:LinesNumber:(numero/nome linea):(bus/navig) dovrebbe servire per info di una linea specifica con il cerca
    //DataRequest:TimeTable:(numero/nome linea):(bus/navig) chiede gli orari per una determinata linea
    //DataRequest:AllStopName:(numero/nome linea):(bus/navig) restituisce la lista di tutte le fermate di una certa linea
    //Es-> DataRequest:LinesNumber:2:bus
    //Es-> DataRequest:LinesNumber:2:navig
    //Es-> DataRequest:TimeTable:2:bus
    //Es-> DataRequest:AllStopName:2:bus
    //Es-> DataRequest:LinesNumber:2:navig
    
    public void askLines(); //tutte le linee
      
    public void askLines(String prefered); //la linea preferita se si vuole usare per una linea salvata
    
    public void askTimeTable(String linesNumber); //orario della linea selezionata
    
    public void askAllStops(String linesNumber); //lista di tutte le fermate!
    
    public void askAvgDelay(String linesNumber); //orario medio di ritardo
    
    
    
}
