/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author nichi
 */
public class AcceptDataRequest extends Thread {
    
    public final int PORTNUMBER=1313;
    public final String IPADDRESS="localhost";
    public Socket socket;
    public ServerSocket serverSocket;
    
    public AcceptDataRequest() throws IOException{
        socket = new Socket(IPADDRESS,PORTNUMBER);    
    }
    
    
    public void manageRequest(String request){
        String temp=request;
        if(request.startsWith("DataReques:")){
            temp=request.substring(request.length());
            switch(temp){
                case "AllLinesNumber":
                    //chidere al db le info
                    break;
                case "AllStopName":
                    //chidere al db le info
                    break;
            
            }
        
        }   
    }
    
    
    
    @Override
    public void run(){
    
    }
    
    
}
