/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nichi
 */
public class fakeClient {
    
    private Socket s;

    public fakeClient() {
        try {
            this.s = new Socket("localhost",1313);
        } catch (IOException ex) {
            Logger.getLogger(fakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void request(){
        try {
            String request="DataRequest:TimeTable:2:bus";
            OutputStream os = this.s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.s.getInputStream();
            ObjectInputStream isobj = new ObjectInputStream(is);
            //System.out.println(isobj.available());
            AndroidDataInterface inputData= (AndroidDataInterface) isobj.readObject();
            System.out.println(inputData.getNameObject());
            for(String a : inputData.getDataAsList())
                    System.out.println("ora: " + a);
           
        } catch (IOException ex) {
            Logger.getLogger(fakeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public static void main(String[] args){
        System.out.println("fake client started");
        
        for(int i=0;i<15;i++){
            fakeClient fake = new fakeClient();
            fake.request();
        }
    }
    
    
}
