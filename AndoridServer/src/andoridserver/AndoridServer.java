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

/**
 *
 * @author Nicola
 */
public class AndoridServer implements Runnable {
    
    public final int PORTNUMBER = 1313;
    public final String IPADDRESS = "localhost";
    public Socket socket;
    public ServerSocket serverSocket;

    public AndoridServer() throws IOException{
       serverSocket = new ServerSocket(PORTNUMBER);
    }
    
    public void run(){
    
    
    }
}
