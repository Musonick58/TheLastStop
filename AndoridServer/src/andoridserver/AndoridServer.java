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
    
    public AndoridServer(){
        
        try {
            serverSocket = new ServerSocket(PORTNUMBER);
            createdThread = new ArrayList<>();
            System.out.println("Server Started! v0.1");
        } catch (IOException ex) {
            //Logger.getLogger(AndoridServer.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            //socket.close();
        
        }
    }
    
    public void run(){
        String clientIp;
        Scanner textReader;
        String request;
        Thread t;
        while(true){
            try {
                this.socket=serverSocket.accept();
                clientIp  = this.socket.getInetAddress().getHostAddress();
                System.out.println("Indirizzo del client: "+clientIp);
                textReader = new Scanner(this.socket.getInputStream());
                request = textReader.nextLine();
                t = new AcceptDataRequest(this.socket,request);
                createdThread.add(t);
                t.start();
                //System.out.println(request);
                //System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(AndoridServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        }
    }
    
    
    public static void main(String[] args) throws IOException{
        
    AndoridServer s = new AndoridServer();
    Thread mainServer = new Thread(s,"Andorid Server");
    mainServer.start();
    
    
    
    }
    
    
}
