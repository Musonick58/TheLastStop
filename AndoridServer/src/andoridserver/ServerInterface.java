/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andoridserver;
import andoridserver.androidData.AndroidDataInterface;
import java.io.*;
import java.net.*;
/**
 *
 * @author Nicola
 */
public interface ServerInterface {
    //utiliziamo questa forma per semplicita'
    Socket openSocket(int port,String address);
    //se apriamo una connessione la dobbiamo anche chiudere
    void closeSocket(Socket s);
    //true richiesta accettata, false rifiutata
    boolean acceptRequest(InputStream is);
    
    //ritorna il numero di byte inviati
    int sendResponse(AndroidDataInterface adi);
    
    
}
