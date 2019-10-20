package tn.seif.stedeex.Notif;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hussienalrubaye on 3/6/16.
 * this services send  broadcast messages every 50000ms
 */
public class ServiceNotification extends IntentService {
    public static boolean ServiceIsRun=false;
    public static int CommnetID=0;
    public ServiceNotification() {
        super("MyWebRequestService");
    }
    protected void onHandleIntent(Intent workIntent) {

        // continue sending the messages
        while ( ServiceIsRun) {
            //* get new news





            String myurl = "http://steedex.herokuapp.com/api/demandes";
            try
            {
                URL url = new URL(myurl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                NewsData=Stream2String(in);
                in.close();
                String msg="";

                // read jsn data
                JSONArray json = new JSONArray(NewsData);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject newDataItem = json.getJSONObject(i);

                    msg+= newDataItem.getString("titre")+ ","+ newDataItem.getString("nom_prenom_recept") +"!!";
                    CommnetID=  newDataItem.getInt("id");
                }

                // creat new intent
                Intent intent = new Intent();
                //set the action that will receive our broadcast
                intent.setAction("com.example.Broadcast");
                // add data to the bundle
                intent.putExtra("msg", msg);
                // send the data to broadcast
                sendBroadcast(intent);
                //delay for 50000ms

            } catch (Exception e) {
            }

            try{
                Thread.sleep(20000);
            }catch (Exception ex){}


        }
    }

    String NewsData="";

    public String Stream2String(InputStream inputStream) {
        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String Text="";
        try{
            while((line=bureader.readLine())!=null) {
                Text+=line;
            }
            inputStream.close();
        }catch (Exception ex){}
        return Text;
    }
}