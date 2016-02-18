package androidclient;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laststop.thelaststop.R;

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
public class AsyncDownload extends AsyncTask<String, Integer, String>{

    private String json;

    public String getJson(){


        return json;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Socket s = new Socket("52.33.218.151",1313);
            String request="DataRequest:Stops:2:bus";
                    //params[0];
            Log.d("json:","chiedo dati");
            OutputStream os = s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            Log.d("json:", "mandato request");
            ps.flush();
            os.flush();
            Log.d("json:", "flushato tutto");
            InputStream is = s.getInputStream();
            Scanner isobj = new Scanner(is);
            Log.d("json:", "inizializzo scanner");
            json = isobj.nextLine();
            Log.d("json:", "messo next line in json");
            isobj.close();
            ps.close();
            os.close();
            Log.d("json:", "chiuso tutto");
            Log.d("json:", "***************************************************************************");
            Log.d("json:", ((Boolean) (json != null)).toString());
            Log.d("json:",json);
            //throw new UnsupportedOperationException(json);
            Log.d("json:", "***************************************************************************");
            s.close();

           // TextView michele = (TextView)

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
