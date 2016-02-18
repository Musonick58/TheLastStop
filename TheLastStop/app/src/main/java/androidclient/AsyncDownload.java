package androidclient;

import android.os.AsyncTask;
import android.util.Log;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.example.laststop.thelaststop.R;
=======
>>>>>>> origin/master

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
<<<<<<< HEAD
public class AsyncDownload extends AsyncTask<String, Integer, ArrayList<String>>{
=======
public class AsyncDownload extends AsyncTask<Socket, Integer, String> {
>>>>>>> origin/master

    private String json;

    @Override
<<<<<<< HEAD
    protected ArrayList<String> doInBackground(String... params) {
=======
    protected String doInBackground(Socket... params) {
>>>>>>> origin/master
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
        return jsonToArrayList(json);
    }

<<<<<<< HEAD

    protected ArrayList<String> jsonToArrayList(String input){
        ArrayList<String> stringArray=null;
        try {
            stringArray = new ArrayList<>();
            JSONArray jArray = null;

                jArray = new JSONArray(input);

            for (int i = 0; i < jArray.length(); i++) {
                stringArray.add(jArray.getString(i));
                Log.d("jsonArray "+i+":", stringArray.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("jsonArray:", stringArray.toString());
        return stringArray;
    }//Metodo pr lo spacchettamento del JSON in ingresso in un ArrayList
=======
    protected ArrayList<String> JSONParser(String input){ //Metodo pr lo spacchettamento del JSON in ingresso in un ArrayList
        return null;
    }
>>>>>>> origin/master


   /* protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }*/

   /* protected void onPostExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
    }*/

}
