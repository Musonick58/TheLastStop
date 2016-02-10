/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.net.*;
import andoridserver.database.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nichi
 */
public class SendData implements Serializable {
    private Socket socket;
    private String query;
    private DBInterface database;
    private AndroidDataInterface data;
    
    public SendData(Socket socket, String query,DBInterface db){
        this.query=query;
        this.socket=socket;
        this.database=db;
    }
    
        public void closeAll(){
        try {
            this.socket.close();
           
        } catch (IOException ex) {
            Logger.getLogger(AcceptDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AndroidDataInterface getDataFromDB(){
        AndroidDataInterface dataToSend = null;
        dataToSend = new AndroidOrariData();//database.executeQuery(query);
        for(int i=5;i<22;i++)
            dataToSend.addData("%h:30".replaceAll("%h",""+i));
        return dataToSend;
    }

    public void send(){
        System.out.println("you call Send");
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream osobj = new ObjectOutputStream(os);
            //FileOutputStream fos = new FileOutputStream("temp.dat");
            //ObjectOutputStream oout = new ObjectOutputStream(fos);
            //oout.writeObject(getDataFromDB());
            osobj.writeObject(getDataFromDB());
            osobj.flush();
            //oout.flush();
            System.out.println("data Sent");
            //oout.close();
            closeAll();
        } catch (IOException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    
}