/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nichi
 */
public class AcceptDataRequest extends Thread {

    public Socket socket;
    private String request;
    private String clientIp;
    private Scanner textReader;

    public AcceptDataRequest(Socket socket) {
        super("AccpetDataRequest");
        this.socket = socket;
        clientIp = this.socket.getInetAddress().getHostAddress();
        System.out.println("Indirizzo del client: " + clientIp+"port number: "+this.socket.getPort());
        setRequest();
    }
    
    private void setRequest(){
        try {
            textReader = new Scanner(this.socket.getInputStream());   
            request = textReader.nextLine();
        } catch (IOException ex) {
            Logger.getLogger(AcceptDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAll(){
        try {
            this.socket.close();
            this.textReader.close();
        } catch (IOException ex) {
            Logger.getLogger(AcceptDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        manageRequest();      
    }

    public void manageRequest() {
        String temp = request;
        int lineNum = -1;
        String lineStr = null;
        String serviceType=null;
        AndroidDataInterface info; 
        if (request.startsWith("DataRequest:")) {
            System.out.println("Data Request: Processing");
            //System.out.println(request);
            System.out.println(!request.equalsIgnoreCase("DataRequest:AllLinesNumber"));
            if (!request.equalsIgnoreCase("DataRequest:AllLinesNumber")) {              
                temp = request.substring("DataRequest:".length());
                System.out.println(temp);
                if (temp.startsWith("TimeTable:")) {
                  //  System.out.println(request);
                    temp = temp.substring("TimeTable:".length());
                    System.out.println("temp: "+temp);
                    String[] lines = temp.split(":");
                    System.out.println("lunghezza array"+lines.length);
                    for(String x : lines )
                        System.out.println("contenuto"+x);
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                        System.out.println("Integer number:"+" "+lineNum);
                        serviceType = lines[1];
                    } else {
                        lineStr = lines[0];
                        System.out.println("String number:"+" "+lineStr);
                        serviceType = lines[1];
                    }
                    if (lineStr == null) {
                        //qui cerco la linea come intero
                        
                    } else {
                        //qui cerco la linea per nome
                        
                    }
                }
                if (temp.startsWith("LinesNumber:")) {
                  //  System.out.println(request);
                    temp = temp.substring("LinesNumber:".length());
                    System.out.println("temp: "+temp);
                    String[] lines = temp.split(":");
                    System.out.println("lunghezza array"+lines.length);
                    for(String x : lines )
                        System.out.println("contenuto"+x);
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                        System.out.println("Integer number:"+" "+lineNum);
                        serviceType = lines[1];
                    } else {
                        lineStr = lines[0];
                        System.out.println("String number:"+" "+lineStr);
                        serviceType = lines[1];
                    }
                    if (lineStr == null) {
                        //qui cerco la linea come intero
                        
                    } else {
                        //qui cerco la linea per nome
                        
                    }
                }
                if (temp.startsWith("AllStopName:")) {
                  //  System.out.println(request);
                    temp = temp.substring("AllStopName:".length());
                    System.out.println("temp: "+temp);
                    String[] lines = temp.split(":");
                    System.out.println("lunghezza array"+lines.length);
                    for(String x : lines )
                        System.out.println("contenuto"+x);
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                        System.out.println("Integer number:"+" "+lineNum);
                        serviceType = lines[1];
                    } else {
                        lineStr = lines[0];
                        System.out.println("String number:"+" "+lineStr);
                        serviceType = lines[1];
                    }
                    if (lineStr == null) {
                        //qui cerco la linea come intero
                        
                    } else {
                        //qui cerco la linea per nome
                        
                    }
                }                
            }else{
                //chiedi al database le informazioni sulle linee
                System.out.println("tutte le linee");
            }
            closeAll();
        }
    }
    
    private static boolean isNumber(String n) {
        double c;
        boolean ret = true;
        if (n.equals("000000") || n.equals("'000000'")) {
            ret = false;
        }
        try {
            c = Double.parseDouble(n);
        } catch (NumberFormatException nfe) {
            ret = false;
        }
        return ret;
    }
    
    
    
    public static void main(String[] args){
        
        
    }
}
