package tn.seif.stedeex.Notif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // get the bundles in the message
        final Bundle bundle = intent.getExtras();
        // check the action equal to the action we fire in broadcast,
        if   (   intent.getAction().equalsIgnoreCase("com.example.Broadcast"))
        //read the data from the intent
        { NewMessageNotification notfilyme= new NewMessageNotification();
            notfilyme.notify(context,bundle.getString("msg"),223);}
        //  Toast.makeText(context,bundle.getString("msg"),Toast.LENGTH_LONG).show();
    }
}