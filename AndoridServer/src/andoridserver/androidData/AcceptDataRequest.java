/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import andoridserver.database.DBConnector;
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
        System.out.println("Indirizzo del client: " + clientIp + "port number: " + this.socket.getPort());
    }

    private void setRequest() {
        try {
            textReader = new Scanner(this.socket.getInputStream());
            this.request = textReader.nextLine();
        } catch (java.util.NoSuchElementException ex) {

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void closeAll() {
        try {

            this.textReader.close();

        } catch (Exception ex) {
            Logger.getLogger(AcceptDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        manageRequest();
    }

    public void manageRequest() {
        setRequest();
        String temp = "";
        int lineNum = -1;
        String lineStr = null;
        String[] lines = null;
        String serviceType = null;
        String stopName = null;
        String headsign = null;
        AndroidDataInterface info;
        if (request != null && request.startsWith("DataRequest:")) {
            System.out.println("[ACCEPTDATAREQUEST] Data Request: Processing");
            if (request.startsWith("DataRequest:")) {
                temp = request.substring("DataRequest:".length());
                System.out.println("[ACCEPTDATAREQUEST] " + temp);
                if (temp.startsWith("TimeTable:")) {
                    System.out.println("time table richiesta ");
                    temp = temp.substring("TimeTable:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    //2:headsign:fermata:navig
                    
                    lineStr = lines[0];
                    headsign = lines[1];
                    stopName = lines[2];
                    serviceType = lines[3];
                    
                    SendData send = new SendData(socket, serviceType, null, lineStr, DBConnector.getIstance());
                    //DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "battelli" );
                        System.out.println(stopName+"******"+lineStr);
                        //System.out.println(send.query.dbTime(stopName,lineStr));
                        info = DBConnector.getIstance().executeTimetable(send.query.dbTime(lineStr,stopName,headsign));
                        send.toSend(info);
                        send.send();
                }//DataRequest:Stops:2:bus
                
                if (temp.startsWith("Stops:")) {
                    System.out.println("stop richiesta");
                    temp = temp.substring("Stops:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    //stopName = lines[0];
                    //DataRequest:Stops:32:headsign:bus
                    lineStr = lines[0];//32
                    headsign = lines[1];//headsign
                    serviceType = lines[2];//bus
                    SendData send = new SendData(socket, serviceType, null, lineStr, DBConnector.getIstance());
                    //DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "battelli" );
                        System.out.println("******"+lineStr);
                        info = DBConnector.getIstance().executeAllStopNames(send.query.dbStops(lineStr,headsign));
                        send.toSend(info);
                        send.send();
                }//DataRequest:Delay:2:Stop:bus
                 if (temp.startsWith("Delay:")) {
                    System.out.println("dealy richiesta");
                    temp = temp.substring("Delay:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    //2:headsign:Stop:navig
                    lineStr = lines[0];//2
                    headsign = lines[1];//headsign
                    stopName = lines[2];//stopname
                    serviceType = lines[3];//servicetype
                    System.out.println("Servizio: "+serviceType);
                    SendData send = new SendData(socket, serviceType, null, lineStr, DBConnector.getIstance());
                    //DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "battelli" );
                        System.out.println("******"+lineStr);//2:Stop:navig
                        info = DBConnector.getIstance().executeDelay(send.query.dbTimesDelay(lineStr,stopName,headsign));
                        send.toSend(info);
                        send.send();
                }
                
                
                if (temp.startsWith("Lines:")) {
                    temp = temp.substring("Lines:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    // lineStr = lines[0];
                    // stopName = lines[1];
                    serviceType = lines[0];//jj
                    SendData send = new SendData(socket, serviceType, null, null, DBConnector.getIstance());
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "battelli" );
                    info = DBConnector.getIstance().executeLines(send.query.dbLinee());
                    send.toSend(info);
                    send.send(); 
                }       
            }
        }
    }
   
    public static void main(String[] args) {

    }
}
