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
    private String serviceType,stopName,lineNum;
    private DBInterface database;
    public DBAsk query;
    private AndroidDataInterface data;
    
    public SendData(Socket socket, String serviceType,String stopName,String lineNum, DBInterface db){
        this.socket=socket;
        this.serviceType=serviceType;
        this.stopName=stopName;
        this.lineNum=lineNum;
        this.database=db;
        query=new DBAsk();
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
    
    public void toSend(AndroidDataInterface adi){
        data=adi;
    }

    public void send(){
        System.out.println("you call Send");
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream osobj = new ObjectOutputStream(os);
            osobj.writeObject(data);
            osobj.flush();
            System.out.println("data Sent");
            closeAll();
        } catch (IOException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    
}
