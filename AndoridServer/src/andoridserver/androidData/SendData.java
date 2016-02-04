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
    
    public AndroidDataInterface getDataFromDB(){
        AndroidDataInterface dataToSend = null;
        dataToSend = database.executeQuery(query);
        return dataToSend;
    }
    
    
    public void send(){
        
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream osobj = new ObjectOutputStream(os);
            osobj.writeObject(getDataFromDB());
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    
    
    }
    
    
    
    
    
}
