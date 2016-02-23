package androidclient;

import android.os.AsyncTask;
import android.util.Log;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import org.json.*;

/**
 * Created by Nicola on 16/02/2016.
 * Updated by John on 17/02/2016
 */

public class AsyncDownload extends AsyncTask< String, Integer, ArrayList<String> > {

    private String json;

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        try {
            //Log.d("ziojack:","creo errore socket");
            Socket s = new Socket("52.33.218.151", 1313);
            // s.setSoTimeout(100);
            String request = params[0];
            //params[0];
            Log.d("json:", "chiedo dati");
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
            Log.d("json:", json);
            Log.d("json:", "***************************************************************************");
            s.close();
        } catch (Exception e) {

            //StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getMainPointer());
            //e.printStackTrace();
            Log.d("ziojack: ", e.getMessage());
            //e.printStackTrace();
            // this.cancel(true);
            return null;


        }
        Log.d("ciao", ((Boolean) (json != null)).toString());
        return jsonToArrayList(json);
    }

    protected ArrayList<String> jsonToArrayList(String input) {
        ArrayList<String> stringArray = new ArrayList<>();
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(input);
            for (int i = 0; i < jArray.length(); i++) {
                stringArray.add(jArray.getString(i));
                // Log.d("jsonArray "+i+":", stringArray.toString());
            }
        } catch (Exception e) {
            //  Log.d("Exception: ", e.getMessage());
            e.printStackTrace();
        }
        Log.d("jsonArray:", stringArray.toString());
        return stringArray;
    }//Metodo pr lo spacchettamento del JSON in ingresso in un ArrayList
    
    protected String systemTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        // you can get seconds by adding  "...:ss" to it
           date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String localTime = date.format(currentLocalTime);
        //int currentHour = cal.get(Calendar.HOUR);
        //int currentMinutes = cal.get(Calendar.MINUTE);
        //int currentSeconds = cal.get(Calendar.SECOND);
        return localTime;
    }




       /* protected void onProgressUpdate(Integer... progress) {
            setProgressPercent(progress[0]);
        }*/

       /* protected void onPostExecute(Long result) {
            showDialog("Downloaded " + result + " bytes");
        }*/

}

