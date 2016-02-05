/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;

import CSVReader.CSVReader;
import andoridserver.androidData.*;
import andoridserver.database.*;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.sun.webkit.dom.EventImpl;
import java.io.IOException;
import json.*;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */
public class AndoridServer {
   
    public static void emptyDeadThread(List<Thread> x) {
        for (Thread t : x) {
            if (t != null && !t.isAlive()) {
                x.remove(t);
            }
        }
    }
    
    public static int numberOfThreadActive(List<Thread> x) {
            return x.size();
    }

    public static void main(String[] args) throws Exception {
    final int PORTNUMBER = 1313;
    ServerSocket serverSocket = new ServerSocket(PORTNUMBER);
    System.out.println("Server Started! v0.2");
    ArrayList<Thread> createdThread = new ArrayList<>();
        while (true) {
          Socket clientSocket = serverSocket.accept();
          AcceptDataRequest serviceThread = new AcceptDataRequest(clientSocket);
          serviceThread.start();
          createdThread.add(serviceThread);
          AndoridServer.emptyDeadThread(createdThread);
          System.out.println(AndoridServer.numberOfThreadActive(createdThread));
        }
    }

}
