package tn.seif.steedex;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.auth0.android.jwt.JWT;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import io.jsonwebtoken.Jwts;
import tn.seif.steedex.Config.JWTUtils;
import tn.seif.steedex.Models.User;
import tn.seif.steedex.Service.MyFirebaseMessagingService;
import tn.seif.steedex.Utils.ConnectionDetector;


public class MainActivity extends AppCompatActivity {

public String BASE_URL = SteedexApplication.BASE_URL;



    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";

    RelativeLayout loginForm;
    //Test connection
    ConnectionDetector cd;
    String url2;

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cd = new ConnectionDetector(this);



        RequestQueue queue = Volley.newRequestQueue(this);


        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        loginForm = findViewById(R.id.loginForm);
        Button clickButton = (Button) findViewById(R.id.button_login_login);
        final TextView editText_login_username = (TextView) findViewById(R.id.editText_login_username);
        final LoadToast loadToast = new LoadToast(MainActivity.this);
        final TextView editText_login_password = (TextView) findViewById(R.id.editText_login_password);
        ServiceNoDelay mSensorService = new ServiceNoDelay(getApplicationContext());
        Intent mServiceIntent = new Intent(getApplicationContext(), mSensorService.getClass());


        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash


        if (tn.seif.steedex.Utils.SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
        } else {
            tn.seif.steedex.Utils.SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            loginForm.setVisibility(View.VISIBLE);
        }



        if (clickButton != null) {

            clickButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (cd.isConnected()) {


                        loadToast.setText("Connecting...");
                        loadToast.show();




                        final String login = editText_login_username.getText().toString();
                        final String password = editText_login_password.getText().toString();

                        new AsyncTask<String, String, String>() {

                            @Override
                            protected String doInBackground(String... params) {
                                try {

                                    JSONObject obj = new JSONObject();

                                    obj.put("username", login);
                                    obj.put("password", password);
                                    Log.d("OBJ RESULT ", obj.toString());

                                    final String response = checkLoginPassword("http://steedex.herokuapp.com/json_login",
                                            obj.toString(), getApplicationContext());
                                    if (response == "ERROR") {


                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                loadToast.hide();
                                                Log.d("Failure Login","login or password wrong ");

                                                Toast.makeText(getApplicationContext(), "Username or password is incorrect ! ", Toast.LENGTH_LONG).show();
                                            }
                                        });


                                    } else {
                                        Log.d("response string : ", response.toString());

                                      /*  String ojson ;
                                        ojson = response.toString();

                                        try {

                                            JSONObject obj5 = new JSONObject(ojson);

                                            Log.d("My AppToken5", obj5.getString("token"));

                                          String t2=  JWTUtils.decoded(obj5.getString("token"));

                                            JSONObject parsedUserJwt = new JSONObject(t2);


                                            Log.d("My parsed username", parsedUserJwt.getString("username"));



                                        } catch (Throwable t) {
                                            Log.e("My App", "Could not parse malformed JSON: \"" + ojson + "\"");
                                        }
*/



                                        final JSONObject resp = new JSONObject(response.toString());

                                        String parsedId = resp.getString("id");
                                        String parsedNom = resp.getString("nom");
                                        String parsedPreom = resp.getString("prenom");
                                        String parsedTel = resp.getString("tel");
                                        String parsedRole = resp.getString("roles");
                                        String parsedAdresse = resp.getString("adresse");
                                        String parsedEmail = resp.getString("email");
                                        String parsedFrais = resp.getString("frais");
                                        String parsedUsername = resp.getString("username");
                                        String parsedFiscale = resp.getString("fiscal");


                                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                                        startActivity(intent);


                                        tn.seif.steedex.Utils.SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                                        tn.seif.steedex.Utils.SaveSharedPreference.setUserCredentials(getApplicationContext(), parsedId, parsedNom, parsedTel, parsedRole,parsedPreom,parsedAdresse,parsedEmail,parsedFrais,parsedUsername,parsedFiscale,password);

                                        Intent launchactivity = new Intent(MainActivity.this, StartActivity.class);
                                        launchactivity.putExtra(EXTRA_TEXT, parsedNom);
                                        startActivity(launchactivity);

                                        Log.d("Succes Login","Bonjour "+parsedNom);
                                        Log.d("======>", parsedNom + " #### " + parsedTel);














                                        /*herrrrrrrrrrrrrrrrrrrrrrrrrrrre*/



                                      //  FirebaseMessaging.getInstance().subscribeToTopic("news");

                                        //FirebaseInstanceId.getInstance().deleteToken(FirebaseInstanceId.getInstance().getId(), FirebaseMessaging.INSTANCE_ID_SCOPE);

                                        String token = FirebaseInstanceId.getInstance().getToken();
                                        while(token == null) {
                                            token = FirebaseInstanceId.getInstance().getToken();
                                        }

                                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());




                                        url2 = "http://steedex.herokuapp.com/api/create_token/"+parsedId+"/"+token;
                                        StringRequest postRequest = new StringRequest(Request.Method.POST, url2,
                                                new Response.Listener<String>()
                                                {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        // response
                                                        Log.d("Response", response);
                                                    }
                                                },
                                                new Response.ErrorListener()
                                                {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        // error
                                                        Log.d("Error.Response", response);
                                                    }
                                                }
                                        ) {
                                            @Override
                                            protected Map<String, String> getParams()
                                            {
                                                Map<String, String>  params = new HashMap<String, String>();
                                                params.put("user_id", "99");
                                                params.put("token", "ABCD-Z");
                                                Log.d("wfe el ajout","true");

                                                return params;
                                            }
                                        };
                                        queue.add(postRequest);

                                        Log.d("wfe el queue","true");







                                        /*hereeeeeeeeeeeeeeeeeeeeeee*/

                                        runOnUiThread(new Runnable() {
                                            public void run() {

                                                Context context = getApplicationContext();
                                                int duration = Toast.LENGTH_SHORT;


                                                try {
                                                    loadToast.hide();

                                                    Toast toast = Toast.makeText(context, "Bonjour " + resp.getString("nom"), duration);


                                                } catch (JSONException e) {
                                                    //ssssss
                                                }

                                            }
                                        });

                                    }


                                    return null;

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    return "";
                                } catch (JSONException k) {
                                    return "";
                                }
                            }

                        }.execute("");


                    } else if (!haveNetwork()) {
                        Toast.makeText(getApplicationContext(), "Network Connection is not available ! ", Toast.LENGTH_LONG).show();

                    }



                }
            });


        }



    }


    private boolean haveNetwork() {
        boolean have_Wifi = false;
        boolean have_mobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkIfnos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkIfnos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_Wifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_mobileData = true;


        }
        return have_Wifi | have_mobileData;
    }


    public static String checkLoginPassword(String stringUrl, String payload,
                                         Context context) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        String line;
        StringBuffer jsonString = new StringBuffer();

        uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        uc.setRequestMethod("POST");
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
        writer.write(payload);
        writer.close();
        int responseCode = uc.getResponseCode();


        if (responseCode == HttpsURLConnection.HTTP_OK) {

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                while ((line = br.readLine()) != null) {
                    jsonString.append(line);
                }


                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            return "ERROR";


        }


        uc.disconnect();


        return jsonString.toString();
    }




}
