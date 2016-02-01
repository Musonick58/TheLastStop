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
    //DataRequest:Lines Number Like (numero/nome linea)
    //DataRequest: TimeTable Like (numero/nome linea)
    //DataRequest: AllStopName
    
    
    
    public void askLines(); //tutte le linee
      
    public void askLines(String prefered); //la linea preferita
    
    public void askTimeTable(String linesNumber); //orario della linea selezionata
    
    public void askAllStops(); //lista di tutte le fermate!
    
    
    
}
