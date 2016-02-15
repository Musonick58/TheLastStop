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
        AndroidDataInterface info;
        if (request != null && request.startsWith("DataRequest:")) {
            System.out.println("[ACCEPTDATAREQUEST] Data Request: Processing");
            if (!request.equalsIgnoreCase("DataRequest:AllLinesNumber")) {
                temp = request.substring("DataRequest:".length());
                System.out.println("[ACCEPTDATAREQUEST] " + temp);
                if (temp.startsWith("TimeTable:")) {
                    temp = temp.substring("TimeTable:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    lineStr = lines[0];
                    stopName = lines[1];
                    serviceType = lines[2];
                                      SendData send = new SendData(socket, serviceType, null, lineStr, DBConnector.getIstance());
                    DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    info = DBConnector.getIstance().executeQuery(send.query.dbTime(stopName,lineStr));
                }
                if (temp.startsWith("Stops:")) {
                    temp = temp.substring("Stops:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    lineStr = lines[0];
                    serviceType = lines[1];
                    SendData send = new SendData(socket, serviceType, null, lineStr, DBConnector.getIstance());
                    DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    info = DBConnector.getIstance().executeQuery(send.query.dbStops(lineStr));
                }
                if (temp.startsWith("Delay:")) {
                    temp = temp.substring("Delay:".length());
                    System.out.println("temp: " + temp);
                    lines = temp.split(":");
                    System.out.println("lunghezza array" + lines.length);
                    for (String x : lines) {
                        System.out.println("contenuto" + x);
                    }
                    lineStr = lines[0];
                    stopName = lines[1];
                    serviceType = lines[2];
                    SendData send = new SendData(socket, serviceType, stopName, lineStr, DBConnector.getIstance());
                    DBConnector.getIstance().disconnect();
                    if(serviceType.equals("bus"))
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    else 
                        DBConnector.getIstance().connect(DBConnector.DRIVER, DBConnector.ADDRESS, DBConnector.POSTGRESPORT, "autobus" );
                    info = DBConnector.getIstance().executeQuery(send.query.dbTimesDelay(lineStr,stopName));
                }               
               
            }
        }
    }

    public static void main(String[] args) {

    }
}
