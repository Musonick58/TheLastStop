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

    public final int PORTNUMBER = 1313;
    public final String IPADDRESS = "localhost";
    public Socket socket;
    public ServerSocket serverSocket;

    public AcceptDataRequest() throws IOException {
        socket = new Socket(IPADDRESS, PORTNUMBER);
    }

    public void manageRequest(String request) {
        String temp = request;
        int lineNum = -1;
        String lineStr = null;
        AndroidDataInterface info;
        if (request.startsWith("DataRequest:")) {
            if (!request.equals("AllLinesNumber")) {
                temp = request.substring("DataRequest:".length());
                if (request.startsWith("TimeTable")) {
                    temp = request.substring("TimeTable:".length());
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
