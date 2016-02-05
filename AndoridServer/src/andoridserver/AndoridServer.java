/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;

import CSVReader.CSVReader;
import andoridserver.androidData.*;
import andoridserver.database.*;
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
public class AndoridServer implements Runnable {

    public final int PORTNUMBER = 1313;
    public final String IPADDRESS = "localhost";
    public Socket socket;
    public ServerSocket serverSocket;
    public ArrayList<Thread> createdThread = null;

    public AndoridServer() {
        try {
            serverSocket = new ServerSocket(PORTNUMBER);
            createdThread = new ArrayList<>();
            //System.out.println("Server Started! v0.1");
        } catch (IOException ex) {
            //Logger.getLogger(AndoridServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //socket.close();
        }
    }

    private void emptyDeadThread() {
        synchronized (this) {
            for (Thread t : createdThread) {
                if (t != null && !t.isAlive()) {
                    createdThread.remove(t);
                }
            }
        }
    }

    public int numberOfThreadActive() {
        synchronized (this) {
            return createdThread.size();
        }
    }

    public void run() {
        System.out.println("Server Started! v0.1");
        String clientIp;
        Scanner textReader;
        String request;
        Thread t;
        boolean isRunning=true;
        while (isRunning) {
            try {
                this.socket = serverSocket.accept();
                t = new AcceptDataRequest(new Socket(this.socket.getInetAddress(), this.socket.getPort()));
                createdThread.add(t);
                t.start();
                emptyDeadThread();
                System.out.println("number of thread: " + numberOfThreadActive());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                isRunning=false;
                //System.out.println("closing server");
            }
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(AndoridServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {

        AndoridServer s = new AndoridServer();
        DBInterface database = DBConnector.getIstance();
        Thread mainServer = new Thread(s, "Andorid Server");
        mainServer.start();

    }

}
