package tn.seif.steedex.Service;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import tn.seif.steedex.MainActivity;
import tn.seif.steedex.MainActivityDemandes;
import tn.seif.steedex.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "MyFirebaseMessaging";


    String type = "";

    public   String title2 = "";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0){
            type="json";
            sendNotification(remoteMessage.getData().toString(),remoteMessage.getData().toString());
            Log.d(TAG,">0");
            Log.d(TAG,"toString=>"+remoteMessage.getNotification().getBody());

        }
        if (remoteMessage.getNotification()  != null){
            type="json";

            Log.d(TAG,"not null");

            sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

            Log.d(TAG,"body=>"+remoteMessage.getNotification().getBody());
            Log.d(TAG,"title=>"+remoteMessage.getNotification().getTitle());

        }







        super.onMessageReceived(remoteMessage);
    }



    private void sendNotification(String messageBody,String titleNotification){

        String id="",message="",title="";

        if (type.equals("json")){
            Log.d(TAG,"JSON OK");

            try {
                JSONObject jsonObject = new JSONObject(messageBody);
                //id = jsonObject.getString("data");




            }
            catch (JSONException e){
                Log.d(TAG,"catch excepetion"+e.getMessage());

            }
            Log.d(TAG,"title ou of try="+title);

        }
        else if (type.equals("message")){
            Log.d(TAG,"type = message");

            message = messageBody;
            Log.d(TAG," =message="+message);

        }
        else{
            Log.d(TAG,"else for all");

        }


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);






        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(alarmSound);

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.ic_launcher));


        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);

        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody+""));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder.setContentTitle(""+titleNotification);
        notificationBuilder.setContentText(""+messageBody);

        notificationManager.notify(0,notificationBuilder.build());


    }






}
