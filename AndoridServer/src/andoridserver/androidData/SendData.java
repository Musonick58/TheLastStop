/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.net.*;
import andoridserver.database.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
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
    
    public String toJson(){
        List<String> list = data.getDataAsList();
        String json = data.getNameObject()+":[";
        for(String txt : data.getDataAsList()){        
            json=json+"\""+txt+"\",";
        }
        json=json.substring(0, json.length()-1);
        json+="]";
        return json;
    } 

    public void send(){
        System.out.println("you call Send");
        try {
            OutputStream os = socket.getOutputStream();
            PrintStream networkWriter = new PrintStream(os);
            String str = toJson();
            networkWriter.println(str);
            networkWriter.flush();
            System.out.println("data Sent");
            closeAll();
        } catch (IOException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    

    
    public static void main(String... args){
        AndroidDataLinee linee = new AndroidDataLinee();
        for(int i=0;i<10;i++)
            linee.addData(""+i);
        SendData send = new SendData(null,null,null,null,null);
        send.toSend(linee);
        System.out.println(send.toJson());
    }

}
