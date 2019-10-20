package tn.seif.stedeex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import tn.seif.stedeex.Models.User;

import com.example.loginrestapi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import tn.seif.stedeex.Notif.ServiceNotification;
import tn.seif.stedeex.Utils.ConnectionDetector;
import tn.seif.stedeex.Utils.SaveSharedPreference;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class MainActivity extends AppCompatActivity {

    public static String BASE_URL = "http://www.steedex-express.com/";
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    RelativeLayout loginForm;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    ConnectionDetector cd;


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

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash


        cd = new ConnectionDetector(this);


        loginForm = findViewById(R.id.loginForm);

        Button clickButton = (Button) findViewById(R.id.button_login_login);
        final TextView editText_login_username = (TextView) findViewById(R.id.editText_login_username);
        final LoadToast loadToast = new LoadToast(MainActivity.this);
        final TextView editText_login_password = (TextView) findViewById(R.id.editText_login_password);

        ServiceNoDelay mSensorService = new ServiceNoDelay(getApplicationContext());
        Intent mServiceIntent = new Intent(getApplicationContext(), mSensorService.getClass());


        if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
        } else {
            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            loginForm.setVisibility(View.VISIBLE);
        }


        if (clickButton != null) {

            clickButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (cd.isConnected()) {


                        loadToast.setText("Connecting...");
                        loadToast.show();

                        final Context context = getApplicationContext();

                        CharSequence text = "Hello whayyyed!";
                        int duration = Toast.LENGTH_SHORT;


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

                                    String response = makePostRequest(BASE_URL + "json_login",
                                            obj.toString(), getApplicationContext());
                                    if (response == "ERROR") {


                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                loadToast.hide();

                                                Toast.makeText(getApplicationContext(), "Username or password is incorrect ! ", Toast.LENGTH_LONG).show();
                                            }
                                        });


                                    } else {
                                        Log.d("response string : ", response.toString());

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
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);


                                        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                                        SaveSharedPreference.setUserCredentials(getApplicationContext(),
                                                parsedId, parsedNom, parsedTel, parsedRole,parsedPreom,parsedAdresse,parsedEmail,parsedFrais,parsedUsername,parsedFiscale);

                                        Intent launchactivity = new Intent(MainActivity.this, StartActivity.class);
                                        launchactivity.putExtra(EXTRA_TEXT, parsedNom);
                                        startActivity(launchactivity);


                                        Log.d("======>", parsedNom + " #### " + parsedTel);
                                        User user = new User(5, parsedNom, parsedTel);


                                        runOnUiThread(new Runnable() {
                                            public void run() {

                                                Context context = getApplicationContext();
                                                CharSequence text = "Hello whayyyed!";
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


                                    return "OK BB";

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





                    /*

                     */


                    //add here
                }
            });


        }

        if(ServiceNotification.ServiceIsRun==false ) {
            ServiceNotification.ServiceIsRun  = true;
            //register the services to run in background
            Intent intent = new Intent(this, ServiceNotification.class);
            // start the services
            startService(intent);

        }


    }

    public void buclick(View view) {
        String myurl = "http://www.steedex-express.com/api/demandes";
        new  MyAsyncTaskgetNews().execute(myurl);


    }

    String NewsData="";
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            NewsData="";

        }
        @Override
        protected String  doInBackground(String... params) {

            publishProgress("open connection" );
            try
            {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                publishProgress("start  read buffer" );
                NewsData=Stream2String(in);
                in.close();



            } catch (Exception e) {
                publishProgress("cannot connect to server" );
            }

            return null;

        }
        protected void onProgressUpdate(String... progress) {
            Log.d("on progress update","update new");

          //  txtv.setText(progress[0]);

        }
        protected void onPostExecute(String  result2){



            Log.d("on post execute","added new");
            // txtv.setText(NewsData);
        }




    }

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



    public void saveDate() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, "Bonjour Seif");
        editor.apply();


        Toast.makeText(this, "Data saved ! ", Toast.LENGTH_SHORT).show();

    }


    public static String makePostRequest(String stringUrl, String payload,
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




}
