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

    public Socket socket;
    private String request;

    public AcceptDataRequest(Socket socket,String request) throws IOException {
        this.socket = socket;
        this.request = request;
        System.out.println("sono il thread che accetta i dati");
        
    }
    
    
    public void run(){
        manageRequest();    
    
    }

    public void manageRequest() {
        String temp = request;
        int lineNum = -1;
        String lineStr = null;
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
                    temp = request.substring("TimeTable:".length());
                    String[] lines = temp.split(":");
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                        System.out.println("Integer number:"+" "+lineNum);
                    } else {
                        lineStr = lines[0];
                        System.out.println("String number:"+" "+lineStr);
                    }
                    if (lineStr == null) {
                        //qui cerco la linea come intero
                    } else {
                        //qui cerco la linea per nome
                    }
                }
                if (request.startsWith("LinesNumber:")) {
                    temp = request.substring("LinesNumber:".length());
                    String[] lines = temp.split(":");
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                    } else {
                        lineStr = lines[0];
                    }
                    if (lineStr == null) {
                        //qui cerco la linea come intero
                    } else {
                        //qui cerco la linea per nome
                    }
                }
                if (request.startsWith("AllStopName:")) {
                    temp = request.substring("AllStopName:".length());
                    String[] lines = temp.split(":");
                    if (isNumber(lines[0])) {
                        lineNum = Integer.parseInt(lines[0]);
                    } else {
                        lineStr = lines[0];
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
    
}
