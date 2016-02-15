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
        super("AcceptDataRequest");
        this.socket = socket;
        clientIp = this.socket.getInetAddress().getHostAddress();
        System.out.println("Indirizzo del client: " + clientIp+"port number: "+this.socket.getPort());
    }
    
    private void setRequest(){
        try {
            textReader = new Scanner(this.socket.getInputStream());   
            this.request = textReader.nextLine();
        } catch (java.util.NoSuchElementException ex) {
           
        } catch(IOException ex){
           System.out.println(ex.getMessage());
        }
    }
    
    public void closeAll(){
        try {
            
            this.textReader.close();
            
        } catch (Exception ex) {
            Logger.getLogger(AcceptDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        manageRequest();      
    }

    public void manageRequest() {
        setRequest();
        String query = "SELECT %l FROM %t WHERE %nf ILIKE %us";
        String temp = "";
        int lineNum = -1;
        String lineStr = null;
        String[] lines=null;
        String serviceType=null;
        AndroidDataInterface info;
         if (request!=null && request.startsWith("DataRequest:")) {
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
                    lines = temp.split(":");
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
                        new SendData(socket,"",null).send();
                    } else {
                        //qui cerco la linea per nome
                        new SendData(socket,"",null).send();
                    }
                }
                if (temp.startsWith("LinesNumber:")) {
                  //  System.out.println(request);
                    temp = temp.substring("LinesNumber:".length());
                    System.out.println("temp: "+temp);
                    lines = temp.split(":");
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
                    temp = temp.substring("AllStopName:".length());
                    System.out.println("temp: "+temp);
                    lines = temp.split(":");
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
