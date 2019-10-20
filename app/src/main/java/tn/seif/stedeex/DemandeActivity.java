package tn.seif.stedeex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.loginrestapi.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tn.seif.stedeex.Utils.SaveSharedPreference;

public class DemandeActivity extends AppCompatActivity {
/*
    @Override
    public void onBackPressed() {

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes);
        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());
        String testRoleClient = "ROLE_CLIENT";

        String RoleAdmin = "ROLE_ADMIN";
        String Role_Livreur = "ROLE_LIVREUR";


        Boolean isAdmin = connectedIdRole.toLowerCase().contains(RoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(Role_Livreur.toLowerCase());


        Boolean isClient = connectedIdRole.toLowerCase().contains(testRoleClient.toLowerCase());

        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data


        Spinner mySpinner = (Spinner)findViewById(R.id.spinner);

        if (isClient){
            mySpinner.setVisibility(View.GONE);

        }
        if (isAdmin)
        {
            mySpinner.setVisibility(View.VISIBLE);

        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DemandeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.etat));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        String cardStatusString;
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                String cardStatusString = parent.getItemAtPosition(pos).toString();
                if (cardStatusString.contains("EnCours"))
                {



                    String namex  = getIntent().getExtras().getString("anime_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://steedex.herokuapp.com/api/demande/update/"+namex+"/EnCours";

                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(),  "Etat Updated to Encours ", Toast.LENGTH_SHORT).show();
                                    TextView aa_etat = (TextView)findViewById(R.id.aa_etat);
                                    aa_etat.setText("EnCours");
                                    // Display the first 500 characters of the response string.
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    queue.add(stringRequest);






                }
              else  if (cardStatusString.contains("EnTraitement"))
                {




                    String namex  = getIntent().getExtras().getString("anime_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://steedex.herokuapp.com/api/demande/update/"+namex+"/EnTraitement";
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("succes response", response.substring(0,9));
                                    Toast.makeText(getApplicationContext(),  "Etat Updated to EnTraitement ", Toast.LENGTH_SHORT).show();
                                    TextView aa_etat = (TextView)findViewById(R.id.aa_etat);
                                    aa_etat.setText("EnTraitement");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    queue.add(stringRequest);







                }

                else  if (cardStatusString.contains("Valide"))
                {




                    String namex  = getIntent().getExtras().getString("anime_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://steedex.herokuapp.com/api/demande/update/"+namex+"/Valide";
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("succes response", response.substring(0,9));
                                    Toast.makeText(getApplicationContext(),  "Etat Updated to Valide ", Toast.LENGTH_SHORT).show();
                                    TextView aa_etat = (TextView)findViewById(R.id.aa_etat);
                                    aa_etat.setText("Valide");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    queue.add(stringRequest);







                }

                else  if (cardStatusString.contains("Cloture"))
                {




                    String namex  = getIntent().getExtras().getString("anime_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://steedex.herokuapp.com/api/demande/update/"+namex+"/Cloture";
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("succes response", response.substring(0,9));
                                    Toast.makeText(getApplicationContext(),  "Etat Updated to Cloture ", Toast.LENGTH_SHORT).show();
                                    TextView aa_etat = (TextView)findViewById(R.id.aa_etat);
                                    aa_etat.setText("Cloture");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    queue.add(stringRequest);







                }


                else  if (cardStatusString.contains("Retour"))
                {




                    String namex  = getIntent().getExtras().getString("anime_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="https://steedex.herokuapp.com/api/demande/update/"+namex+"/Retour";
                    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("succes response", response.substring(0,9));
                                    Toast.makeText(getApplicationContext(),  "Etat Updated to Retour ", Toast.LENGTH_SHORT).show();
                                    TextView aa_etat = (TextView)findViewById(R.id.aa_etat);
                                    aa_etat.setText("Retour");
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    queue.add(stringRequest);







                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });





        String id  = getIntent().getExtras().getString("anime_id");
        String name  = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio") ;
        String category = getIntent().getExtras().getString("anime_category");
//        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode") ;
        String rating = getIntent().getExtras().getString("anime_rating") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_id = findViewById(R.id.aa_demande_id);
        TextView tv_name = findViewById(R.id.aa_demande_titre);
        TextView tv_studio = findViewById(R.id.aa_idDemande);
        TextView tv_categorie = findViewById(R.id.aa_type) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_etat) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_id.setText(id);
        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load("https://pbs.twimg.com/profile_images/611913231792259072/fq2_ubdL.png").apply(requestOptions).into(img);





    }
}
