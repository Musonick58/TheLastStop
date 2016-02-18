/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver.androidData;

import andoridserver.database.DBConnector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nichi
 */
public class fakeClient {
    
    private Socket s;
    private String json;
    public fakeClient() {
        try {
            this.s = new Socket("52.33.218.151",1313);
        } catch (IOException ex) {
            Logger.getLogger(fakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void request(){
        try {
            String request="DataRequest:TimeTable:2:S. Basilio:bus";//"DataRequest:Timetable:2:Sacca Fisola DX:navig";
            OutputStream os = this.s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = s.getInputStream();
            Scanner isobj = new Scanner(is);
            json = isobj.nextLine();
            isobj.close();
            ps.close();
            os.close();
            System.out.println("***************************************************************************");
            System.out.println(json);
            System.out.println("***************************************************************************");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(fakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public static void main(String[] args){
        System.out.println("fake client started");
            fakeClient fake = new fakeClient();
            fake.request();
        
    }
    
    
}
