package androidclient;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Nicola on 16/02/2016.
 */
public class AsyncDownload extends AsyncTask<Socket, Integer, String> {



    @Override
    protected String doInBackground(Socket... params) {
        try {
            int count = params.length;

            Socket s = new Socket("52.33.218.151",1313);
            String request="DataRequest:Lines:bus";
            OutputStream os = s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(request);
            ps.flush();
            os.flush();
            //if (isCancelled()) break;
        } catch (IOException e) {
                e.printStackTrace();
            }

        return "ciao";
    }

   /* protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }*/

   /* protected void onPostExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
    }*/

}
