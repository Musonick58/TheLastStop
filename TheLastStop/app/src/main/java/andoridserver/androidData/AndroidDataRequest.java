package andoridserver.androidData;

/**
 * Created by nichi on 10/02/2016.
 */
public interface AndroidDataRequest {
     //Es -> DataRequest:Lines:bus
     //Es -> DataRequest:Lines:navig
     String askLines(String servizio); //tutte le linee navigazione o autobus

     //Es -> DataRequest:Stops:2:bus
     //Es -> DataRequest:Stops:32:navig
     String  askAllStops(String linesNumber, String servizio); //lista di tutte le fermate!

     //Es -> DataRequest:Timetable:2:fermata:bus
     //Es -> DataRequest:Timetable:2:fermata:navig
     String  askTimeTable(String linesNumber, String servizio, String fermata); //orario della linea selezionata

     //Es -> DataRequest:Delay:2:Stop:bus
     //Es -> DataRequest:Delay:2:Stop:navig
     String  askAvgDelay(String linesNumber, String fermata, String servizio); //orario medio di ritardo

     //Es -> DataRequest:NextStops:Linea:Capolinea:Fermata:Orario:Servizio
     String  askNextStops(String linesNumber, String fermata, String servizio); //orario medio di ritardo

}
