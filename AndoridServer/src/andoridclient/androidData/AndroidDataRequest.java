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
/**
 * Created by nichi on 10/02/2016.
 */

public interface AndroidDataRequest {
     //Es -> DataRequest:Lines:bus
     //Es -> DataRequest:Lines:navig  
    //  queste ok!
     AndroidDataInterface askLines(String servizio); //tutte le linee navigazione o autobus

     //Es -> DataRequest:Stops:2:headsign:bus
     //Es -> DataRequest:Stops:32:headsign:navig
     
     AndroidDataInterface askAllStops(String linesNumber, String servizio); //lista di tutte le fermate!

     //Es -> DataRequest:Timetable:2:headsign:fermata:navig
     AndroidDataInterface askTimeTable(String linesNumber, String servizio, String fermata); //orario della linea selezionata

     //Es -> DataRequest:Delay:2:Stop:bus
     //Es -> DataRequest:Delay:2:headsign:Stop:navig
     /**
     * @param linesNumber stringa che contiene il nome abbreviato della linea
     * @param fermata stringa che contiene il nome della fermata
     * @return un'oggetto popolato con l'interfaccia dei dati android
     **/
     AndroidDataInterface askAvgDelay(String linesNumber, String fermata, String servizio); //orario medio di ritardo
     
     //Es -> DelaySet:linea:fermata:minuti (decidiamo domani se mettere orari o altro)
     /**
     * @param minutes stringa contente i minuti di ritardo della linea
     * @param linesNumber stringa che contiene il nome abbreviato della linea
     * @param fermata stringa che contiene il nome della fermata
     * @return true se l'operazione da parte del server è andata bene, false in caso contrario (popup in caso di connessione)
     **/
     boolean sendDelay(String linesNumber, String fermata, String minutes);

}
