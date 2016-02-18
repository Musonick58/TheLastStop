package androidclient;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nicola on 16/02/2016.
 * Updated by John on 17/02/2016
 */
public class AsyncDownload extends AsyncTask<Socket, Integer, String> {

    private String json;

    @Override
    protected String doInBackground(Socket... params) {
        try {
            Socket s = new Socket("52.33.218.151",1313);
            String request="DataRequest:Lines:bus";
            OutputStream os = s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            InputStream is = s.getInputStream();
            Scanner isobj = new Scanner(is);
            json = isobj.next();
            isobj.close();
            ps.close();
            os.close();
            System.out.println("***************************************************************************");
            System.out.println(json!=null);
            //throw new UnsupportedOperationException(json);
            System.out.println("***************************************************************************");

            //if (isCancelled()) break;
        } catch (IOException e) {
                e.printStackTrace();

            }
        Log.d("ciao",((Boolean)(json!=null)).toString());
        return json;
    }

    protected ArrayList<String> JSONParser(String input){ //Metodo pr lo spacchettamento del JSON in ingresso in un ArrayList
        return null;
    }


   /* protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }*/

   /* protected void onPostExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
    }*/

}
