package androidclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import andoridserver.androidData.*;
import android.app.AlertDialog;
import android.content.DialogInterface;


/**
 * Created by nichi on 10/02/2016.
 * Updated by John on 15/02/2016
 */
public class SendRequest implements AndroidDataRequest {

    private Socket socket;
    public SendRequest(){
        try {
            socket = new Socket("52.36.66.44",1313);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: aggiungere popup per errore connessione.
            AlertDialog.Builder err = new AlertDialog.Builder(this);
            err.setMessage("Controlla la tua connessione!");
            err.setTitle("Errore");

            err.setCancelable(false);
            err.setButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    tv.setText("Ho cliccato il tasto SI");
                }
            });

            err.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    tv.setText("Ho cliccato il tasto NO");
                }
            });

            AlertDialog alert = miaAlert.create();
            alert.show();
        }

    }

    public void closeSendData(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AndroidDataInterface askLines(String servizio){
        OutputStream os;
        AndroidDataInterface inputData=null;
        try {
            String request="DataRequest:Lines"+servizio;
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            ObjectInputStream isobj = new ObjectInputStream(is);
            //System.out.println(isobj.available());
            inputData = (AndroidDataInterface) isobj.readObject();
            System.out.println(inputData.getNameObject());
            List<String> dati = inputData.getDataAsList();
            System.out.println(dati);
            isobj.close();
            ps.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return inputData;

    } //tutte le linee

    public AndroidDataInterface askLines(String linesNumber, String servizio){return null;} //il numero della linea e il tipo di servizio

    public AndroidDataInterface askTimeTable(String linesNumber, String servizio, String fermata){return null;} //orario della linea selezionata

    public AndroidDataInterface askAllStops(String servizio){return null;} //lista di tutte le fermate!

    public AndroidDataInterface askAvgDelay(String linesNumber, String fermata, String servizio){return null;} //orario medio di ritardo


    public static void main(String[] args){
        SendRequest send = new SendRequest();
        send.askLines("bus");
    }
}
