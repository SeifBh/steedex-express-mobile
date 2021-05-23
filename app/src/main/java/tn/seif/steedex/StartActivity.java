package tn.seif.steedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tn.seif.steedex.Utils.SaveSharedPreference;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    String url2 ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        CardView cardView = (CardView) findViewById(R.id.card_demandes);
        CardView cardViewLogout = (CardView) findViewById(R.id.card_logout);
        CardView cardViewProfile = (CardView) findViewById(R.id.cardProfole);
        CardView cardViewUtilisation = (CardView) findViewById(R.id.cardUtilisation);
        CardView cardViewInfo = (CardView) findViewById(R.id.cardInfo);
        CardView cardViewReclamation = (CardView) findViewById(R.id.cardReclamation);

        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this,MainActivityDemandes.class);
                startActivity(intent);

            }
        });

        cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Log.d("trylog",FirebaseInstanceId.getInstance().getToken());



                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());




                url2 = "http://steedex.herokuapp.com/api/delete_token/"+FirebaseInstanceId.getInstance().getToken();
                StringRequest postRequest = new StringRequest(Request.Method.DELETE, url2,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d(TAG, "response="+response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();

                        Log.d(TAG,"true");

                        return params;
                    }
                };
                queue.add(postRequest);

                Log.d(TAG,"wfe el queue");


                new AsyncTask<Void,Void,Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try
                        {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            Log.d(TAG,"token deleted");





                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }



                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        // Call your Activity where you want to land after log out
                    }
                }.execute();




                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Profile_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        cardViewReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://steedex.herokuapp.com"));
                startActivity(browserIntent);


            }
        });




        cardViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),  "Page Info - En cours de construction.. ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ContactUs.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });



        cardViewUtilisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });









    }

    protected String doInBackground(String... params) {
        String url=params[0];

        // Dummy code
        for (int i = 0; i <= 100; i += 5) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "All Done!";
    }





    @Override
    protected void onResume() {
        super.onResume();
        final LoadToast loadToast = new LoadToast(StartActivity.this);


    }


}
