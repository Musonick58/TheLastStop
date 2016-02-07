/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;


import andoridserver.androidData.*;
import andoridserver.database.*;
import CSVReader.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


/**
 *
 * @author Nicola
 */
public class AndoridServer {
   
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
    DBConnector.getIstance(); //Inizializzazione del singleton del database

    ServerSocket serverSocket = new ServerSocket(PORTNUMBER);
    System.out.println("Listen on port: "+PORTNUMBER);
    ArrayList<Thread> createdThread = new ArrayList<>();
        while (true) {
          Socket clientSocket = serverSocket.accept();
          AcceptDataRequest serviceThread = new AcceptDataRequest(clientSocket);
          serviceThread.start();
          //createdThread.add(serviceThread);
          //AndoridServer.emptyDeadThread(createdThread);
          //System.out.println(AndoridServer.numberOfThreadActive(createdThread));
        }
    }

}
