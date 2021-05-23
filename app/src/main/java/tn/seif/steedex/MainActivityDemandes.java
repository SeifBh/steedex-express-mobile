package tn.seif.steedex;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import tn.seif.steedex.Models.Demande;
import tn.seif.steedex.Models.Anime;

import tn.seif.steedex.Utils.ConnectionDetector;
import tn.seif.steedex.Utils.SaveSharedPreference;
import tn.seif.steedex.Adapters.RecyclerViewAdapter;




public class MainActivityDemandes extends AppCompatActivity {

    private static final String TAG = "MainActivityDemandes";
    String url2 ="";

    public static String connectIdUser ="";
    public static String connectIdRole ="";

    public String BASE_URL = SteedexApplication.BASE_URL;



    public  String JSON_URL = "http://steedex.herokuapp.com/api/demandes" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private RecyclerView recyclerView ;
    private SwipeRefreshLayout refreshLayout;
    String testRoleAdmin = "ROLE_ADMIN";
    String testRoleLivreur = "ROLE_LIVREUR";
    String testRoleClient = "ROLE_CLIENT";
    ConnectionDetector cd;

    RecyclerViewAdapter myadapter;
    //filter

    private ArrayList<Demande> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Demande> filteredList ;
    public ArrayList<Demande> lstAnime ;
    private Toolbar toolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_main);





        String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());
        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());
        Boolean isAdmin = connectedIdRole.toLowerCase().contains(testRoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(testRoleLivreur.toLowerCase());
        Boolean isClient = connectedIdRole.toLowerCase().contains(testRoleClient.toLowerCase());
        String cNom = SaveSharedPreference.getUserNom(getApplicationContext());
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Bonjour "+cNom);
        cd = new ConnectionDetector(this);

        String token = FirebaseInstanceId.getInstance().getToken();

//        Log.d("tokenseif",token);

        if (cd.isConnected())
        {

            if ( isAdmin){
                Log.d("result contain", connectIdRole.contains("ROLE_ADMIN") +"");

                JSON_URL = "http://steedex.herokuapp.com/api/demandes" ;



            }
            else  {
                if (isClient){
                    JSON_URL = "http://steedex.herokuapp.com/api/demandes/" +connectedIdUser ;


                }
                if (isLivreur){
                    JSON_URL = BASE_URL + "api/demande_livreur/" +connectedIdUser ;



                }

            }





            lstAnime = new ArrayList<>() ;
            recyclerView = findViewById(R.id.recyclerviewid);


            int resId = R.anim.layout_animation_fall_down;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);


            recyclerView.setLayoutAnimation(animation);


            jsonrequest();

            refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container2);

            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
            {
                @Override
                public void onRefresh()
                {
                    jsonrequest();
                }
            });

        }
        else{
            buildDialog(MainActivityDemandes.this).show();
        }





    }

    public   void jsonrequest() {
        lstAnime = new ArrayList<>() ;

        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());
        Boolean isAdmin = connectedIdRole.toLowerCase().contains(testRoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(testRoleLivreur.toLowerCase());
        Boolean isClient = connectedIdRole.toLowerCase().contains(testRoleClient.toLowerCase());

        cd = new ConnectionDetector(this);


        if (cd.isConnected())
        {
            JSON_URL = "http://steedex.herokuapp.com/api/demandes" ;


            if ( isAdmin){
                Log.d("result contain", connectIdRole.contains("ROLE_ADMIN") +"");

                JSON_URL = "http://steedex.herokuapp.com/api/demandes" ;



            }
            else  {
                if (isLivreur){
                    String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());

                    JSON_URL = BASE_URL + "api/demande_livreur/" +connectedIdUser ;





                }
                if (isClient){
                    String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());

                    JSON_URL = BASE_URL + "api/demande/" +connectedIdUser ;





                }

            }



            refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container2);

            refreshLayout.setRefreshing(false);

            Log.d("executed url JSON test",JSON_URL);
            request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject  = null ;

                    for (int i = 0 ; i < response.length(); i++ ) {


                        try {

                            jsonObject = response.getJSONObject(i) ;


                            Demande demande = new Demande() ;
                            demande.setId(jsonObject.getInt("id"));
                            if (jsonObject.has("quoi"))
                            {
                                demande.setQuoi(jsonObject.getString("quoi"));
                                demande.setTypeDC(jsonObject.getString("type_d_c"));


                            }
                            else{
                                demande.setTitre(jsonObject.getString("titre"));
                                demande.setType(jsonObject.getString("type"));
                                demande.setTelephone_recept(jsonObject.getString("telephone_recept"));
                                demande.setNote(jsonObject.getString("note"));
                                demande.setMontant(jsonObject.getString("montant"));
                                demande.setDescription_produit(jsonObject.getString("desc_prod"));


                            }





                            demande.setEtat(jsonObject.getString("etat"));
                            demande.setNom_prenom_recept(jsonObject.getString("nom_prenom_recept"));

                            demande.setLieu(jsonObject.getString("addresse_recept"));


                            demande.setDateCreation(jsonObject.getString("date_emission"));
                            demande.setAddresse_recept(jsonObject.getString("addresse_recept"));

                            demande.setNomLivreur(jsonObject.getJSONObject("id_livreur").getString("nom"));
                            demande.setPrenomLivreur(jsonObject.getJSONObject("id_livreur").getString("prenom"));
                            demande.setTelLivreur(jsonObject.getJSONObject("id_livreur").getString("tel"));

                            lstAnime.add(demande);



                        } catch (JSONException e) {

                            e.printStackTrace();
                        }


                    }

                    for (int i5 = 0; i5 < lstAnime.size(); i5++)
                    {
                        Log.d("entity","-->"+lstAnime.get(i5));
                    }

                    setuprecyclerview(lstAnime);

                    int resId = R.anim.layout_animation_fall_down;
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());

                    myadapter = new RecyclerViewAdapter(getApplicationContext(),lstAnime) ;
                    recyclerView.setLayoutManager(mLayoutManager);



                    recyclerView.setAdapter(myadapter);


                    EditText editText = findViewById(R.id.et_search);
                    editText.clearFocus();
                    editText.setSelected(false);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                            filter(s.toString());
                        }
                    });



                    ProgressBar progressBar= findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.GONE);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                error.getMessage();

                }
            });


            requestQueue = Volley.newRequestQueue(MainActivityDemandes.this);
            requestQueue.add(request) ;

        }

        else{
            buildDialog(MainActivityDemandes.this).show();
        }

    }

    public AlertDialog.Builder buildDialog(Context  c){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("No Internet connection");
        builder.setMessage("You need to have mobile data or wifi connection. Press Ok to exit");
        final AlertDialog alertDialog=  builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create();
        ;

        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("red"));

            }
        });

        return builder;
    }

    private void setuprecyclerview(List<Demande> lstAnime) {




    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(MainActivityDemandes.this,StartActivity.class);
        startActivity(intent);
    }


    private void filter(String text) {
        filteredList = new ArrayList<Demande>();
        for (Demande item : lstAnime) {

            if (item.getTitre() != null ){

                if (item.getTitre().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }

            }

            if (item.getQuoi() != null ){

                if (item.getQuoi().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }

            }



        }



        Log.d("size",filteredList.size()+"");
        myadapter.filterList(filteredList);
    }



/*
    @Override
    protected void onResume() {
        super.onResume();
        final LoadToast loadToast = new LoadToast(MainActivityDemandes.this);

        final String login = tn.seif.steedex.Utils.SaveSharedPreference.getUserName2(getApplicationContext());
        final String password = tn.seif.steedex.Utils.SaveSharedPreference.getUserPassword(getApplicationContext());




        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try
                {
                    FirebaseInstanceId.getInstance().deleteInstanceId();

                    Log.d(TAG,"reload verif");
                    JSONObject obj = new JSONObject();

                    obj.put("username", login);
                    obj.put("password", password);

                    Log.d(TAG, "login string="+login);
                    Log.d(TAG, "password string="+password);


                    Log.d(TAG, "obj string="+obj.toString());

                    final String response = MainActivity.checkLoginPassword("http://steedex.herokuapp.com/json_login",
                            obj.toString(), getApplicationContext());
                    if (response == "ERROR") {
                        Log.d(TAG," verif=not ok");



                        runOnUiThread(new Runnable() {
                            public void run() {
                                loadToast.hide();
                                Log.d("Failure Login","login or password wrong ");
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong! ", duration);







                                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                                final Toast[] mToast = {null};

                                CountDownTimer timer = new CountDownTimer(5000, 1000) {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        if (mToast[0] != null) mToast[0].cancel();
                                        mToast[0] = Toast.makeText(getApplicationContext(), "Erreur d'authentification : Deconnexion automatique :" + millisUntilFinished/1000, Toast.LENGTH_SHORT);
                                        mToast[0].show();
                                    }

                                    @Override
                                    public void onFinish() {
                                        if (mToast[0] != null) mToast[0].cancel();
                                        mToast[0] = Toast.makeText(getApplicationContext(), "Veuillez vérifier votre login et mot de passe", Toast.LENGTH_SHORT);
                                        mToast[0].show();
                                    }
                                }.start();





















                                url2 = "http://steedex.herokuapp.com/api/delete_token/"+FirebaseInstanceId.getInstance().getToken();
                                StringRequest postRequest = new StringRequest(Request.Method.DELETE, url2,
                                        new Response.Listener<String>()
                                        {
                                            @Override
                                            public void onResponse(String response) {
                                                // response
                                                Log.d(TAG,"f woset on resume if error  deletet"+ response);

                                                int duration = Toast.LENGTH_SHORT;
                                                Toast toast = Toast.makeText(getApplicationContext(), "Something wrong ", duration);

                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);

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

                                        Log.d(TAG,"f woset del 2true");

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                                Log.d("wfe el queue","true");


                            }
                        });


                    } else {
                        Log.d("response string : ", response.toString());
                        Log.d(TAG," verif= ok");

                        int duration = Toast.LENGTH_SHORT;


                        runOnUiThread(new Runnable() {
                            public void run() {

                                Context context = getApplicationContext();
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, "Bonjour ", duration);



                            }
                        });

                    }


                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                catch (JSONException k)
                {
                    k.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Call your Activity where you want to land after log out
            }
        }.execute();



    }
*/

    @Override
    protected void onResume() {
        super.onResume();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getApplicationContext(), "onResume MainActivityDemandes! ", duration);

    }
}
