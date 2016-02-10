package androidclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by nichi on 10/02/2016.
 */
public class SendRequest implements AndroidDataRequest{

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
            os = this.socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("DataRequest:TimeTable:2:bus");
            pw.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            ObjectInputStream isobj = new ObjectInputStream(is);
            AndroidDataInterface inputData = (AndroidOrariData) isobj.readObject();
            isobj.close();
            System.out.println(inputData.getNameObject());
            pw.close();
            os.close();

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
