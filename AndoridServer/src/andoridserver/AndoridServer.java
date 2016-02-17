/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;

import CSVReader.CSVThread;
import andoridserver.androidData.*;
import andoridserver.database.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import webpagereader.Checker;
import webpagereader.PageReader;

/**
 *
 * @author Nicola
 */
public class AndoridServer{
   
    public synchronized static void emptyDeadThread(List<Thread> x) {
        for (Thread t : x) {
            if (t != null && !t.isAlive()) {
                x.remove(t);
            }
        }
    }
    
    public synchronized static int numberOfThreadActive(List<Thread> x) {
            return x.size();
    }

    public static void main(String[] args) throws Exception {
    System.out.println("Server Started! v0.3");
    final int PORTNUMBER = 1313;
    System.out.println(DBConnector.getIstance()); //Inizializzazione del singleton del database
    //scarico i file per la prima volta e gli estraggo al loro posto
    /*PageReader pr = new PageReader("http://www.actv.it/opendata/navigazione", "nav");
    String navlink = pr.parse();
    System.out.println("[MAIN]: LINK NAVIGAZIONE -> "+pr.download(navlink));
    pr.updateFiles(); //SI OCCUPA DI UNZIPARE I FILE
    pr = new PageReader("http://www.actv.it/opendata/automobilistico", "bus");
    String buslink = pr.parse();
    System.out.println("[MAIN]: LINK AUTOMOBILISTICO -> "+pr.download(buslink));
    pr.updateFiles(); //SI OCCUPA DI UNZIPARE I FILE
    CSVThread csvstart = new CSVThread();//faccio partire i primi csv da trasformare
    csvstart.start();
    Checker updater = new Checker("CSV online Checker",navlink,buslink);// avvio il thread che check il sito
    //Inizializzo la classe la quale si occupa di attendere una connessione alla porta PORTNUMBER*/
    ServerSocket serverSocket = new ServerSocket(PORTNUMBER); 
    System.out.println("[MAIN]: Listen on port: "+PORTNUMBER);
    //updater.start();//avvio il thread per scansionare la pagina web, per vedere se i dati sono cambiati
        while (true) {
                Socket clientSocket = serverSocket.accept();//attendo che qualcuno si connetta
                //Inizializzano il gestore delle richieste della connessione
                AcceptDataRequest serviceThread = new AcceptDataRequest(clientSocket);
                //Avvio il thread per la gestione delle richieste
                serviceThread.start();
        }
    }

}
