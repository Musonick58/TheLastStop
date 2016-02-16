package androidclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import andoridserver.androidData.*;
import java.util.*;

import com.example.laststop.thelaststop.linee;


/**
 * Created by nichi on 10/02/2016.
 * Updated by John on 15/02/2016
 */
public class SendRequest implements AndroidDataRequest {

    private Socket socket;
    private linee ln = new linee();
    public SendRequest(){
        try {
            socket = new Socket("52.33.218.151",1313);
        } catch (IOException e) {
            e.printStackTrace();
            ln.newErrDialog();
        }

    }

    public void closeSendData(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String askLines(String servizio){ //tutte le linee di tipo 'servizio'
        OutputStream os;
        String inputData=null;
        try {
            String request="DataRequest:Lines:"+servizio;
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            Scanner isobj = new Scanner(is);
            inputData= isobj.nextLine();
            isobj.close();
            ps.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputData;
    }

    public String  askTimeTable(String linesNumber, String servizio, String fermata){//orario della linea selezionata
        OutputStream os;
        String inputData=null;
        try {
            //Es request -> DataRequest:Timetable:2:fermata:navig
            String request="DataRequest:TimeTable:"+linesNumber+":"+fermata+":"+servizio;
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            Scanner isobj = new Scanner(is);
            inputData= isobj.nextLine();
            isobj.close();
            ps.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputData;
    }

    public String askAllStops(String linesNumber, String servizio){ //lista di tutte le fermate!
        OutputStream os;
        String inputData=null;
        try {
            //Es request3 -> DataRequest:Stops:32:navig
            String request="DataRequest:Stops:"+linesNumber+":"+servizio;
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            Scanner isobj = new Scanner(is);
            inputData= isobj.nextLine();
            isobj.close();
            ps.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputData;
    }

    public String askAvgDelay(String linesNumber, String fermata, String servizio){ //orario medio di ritardo
        OutputStream os;
        String inputData=null;
        try {
            //Es request -> DataRequest:Delay:2:Stop:navig
            String request="DataRequest:Delay:"+linesNumber+":"+fermata+":"+servizio;
            os = this.socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = this.socket.getInputStream();
            Scanner isobj = new Scanner(is);
            inputData= isobj.nextLine();
            isobj.close();
            ps.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputData;
    }


    public static void main(String[] args){
        SendRequest send = new SendRequest();
        //test vari invio dati da client a server
        send.askLines("bus");
        send.askAllStops("","");
        send.askAvgDelay("","","");
        send.askTimeTable("","","");
        send.closeSendData();
    }
}
