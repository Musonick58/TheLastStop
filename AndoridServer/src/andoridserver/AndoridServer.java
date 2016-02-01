/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;

import CSVReader.CSVReader;
import andoridserver.androidData.*;
import andoridserver.database.*;
import json.*;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Nicola
 */
public class AndoridServer implements ServerInterface{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
            CSVReader.main(null);
    }

    @Override
    public Socket openSocket(int port, String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeSocket(Socket s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean acceptRequest(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int sendResponse(AndroidDataInterface adi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
