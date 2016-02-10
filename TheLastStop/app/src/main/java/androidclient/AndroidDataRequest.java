package androidclient;

/**
 * Created by nichi on 10/02/2016.
 */
public interface AndroidDataRequest {

     void askLines(); //tutte le linee

     void askLines(String prefered); //la linea preferita se si vuole usare per una linea salvata

     void askTimeTable(String linesNumber); //orario della linea selezionata

     void askAllStops(String linesNumber); //lista di tutte le fermate!

     void askAvgDelay(String linesNumber); //orario medio di ritardo

}
