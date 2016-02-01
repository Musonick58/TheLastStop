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
        int lineNum=-1;
        String lineStr=null;
        if(request.startsWith("DataRequest:")){
            temp=request.substring("DataRequest:".length());
            AndroidDataInterface info;
            switch(temp){
                case "AllLinesNumber":
                    //chidere al db le info
                    
                    break;
                case "AllStopName":
                    //chidere al db le info
                    break;
            
            }
            if(request.startsWith("Delay Like")){
            temp=request.substring("Delay Like:".length());
                if(isNumber(temp)){
                    lineNum=Integer.parseInt(temp);
                }else{
                    lineStr=temp;
                }
            }
        
        }   
    }
    
     private static boolean isNumber(String n){
       double c;
       boolean ret=true;
       if(n.equals("000000") || n.equals("'000000'"))
           ret=false;
       try{
        c=Double.parseDouble(n);
       }catch(NumberFormatException nfe){   
        ret=false;
       } 
       return ret; 
    }
    
    
    @Override
    public void run(){
    
    }
    
    
}
