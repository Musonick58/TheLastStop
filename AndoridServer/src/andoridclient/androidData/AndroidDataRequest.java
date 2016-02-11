/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridclient.androidData;

import andoridserver.androidData.AndroidDataInterface;
/**
 *
 * @author nichi
 */
interface AndroidDataRequest {
    
      //Es -> DataRequest:Lines:bus
     //Es -> DataRequest:Lines:navig
     AndroidDataInterface askLines(String servizio); //tutte le linee navigazione o autobus

     //Es -> DataRequest:Stops:2:bus
     //Es -> DataRequest:Stops:2:navig
     AndroidDataInterface askAllStops(String linesNumber, String servizio); //lista di tutte le fermate!

     //Es -> DataRequest:Timetable:2:bus
     //Es -> DataRequest:Timetable:2:navig
     AndroidDataInterface askTimeTable(String linesNumber, String servizio, String fermata); //orario della linea selezionata

     //Es -> DataRequest:Delay:2:Stop:bus
     //Es -> DataRequest:Delay:2:Stop:navig
     AndroidDataInterface askAvgDelay(String linesNumber, String fermata, String servizio); //orario medio di ritardo
    
    
    
}
