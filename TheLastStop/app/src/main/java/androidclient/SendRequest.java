package androidclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import andoridserver.androidData.*;


/**
 * Created by nichi on 10/02/2016.
 */
public class SendRequest implements AndroidDataRequest {

    private Socket socket;
    public SendRequest(){
        try {
            socket = new Socket("52.36.66.44",1313);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void askLines(){
        OutputStream os = null;
        try {
            String request="DataRequest:TimeTable:2:bus";
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            ObjectInputStream isobj = new ObjectInputStream(is);
            //System.out.println(isobj.available());
            AndroidDataInterface inputData= (AndroidDataInterface) isobj.readObject();
            System.out.println(inputData.getNameObject());
            for(int i=0; i<inputData.getDataAsList().size();i++)
                System.out.println("ora: "+inputData.getDataAsList());
        } catch (Exception e) {
            e.printStackTrace();
        }


    } //tutte le linee

    public void askLines(String prefered){} //la linea preferita se si vuole usare per una linea salvata

    public void askTimeTable(String linesNumber){} //orario della linea selezionata

    public void askAllStops(String linesNumber){} //lista di tutte le fermate!

    public void askAvgDelay(String linesNumber){} //orario medio di ritardo

    public static void main(String[] args){
        SendRequest send = new SendRequest();
        send.askLines();
    }
}
