package tn.seif.steedex;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import org.w3c.dom.Text;

import tn.seif.steedex.Utils.SaveSharedPreference;

public class DemandeActivity extends AppCompatActivity {
/*
    @Override
    public void onBackPressed() {

    }*/

    public String BASE_URL = SteedexApplication.BASE_URL;




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
        //getSupportActionBar().hide();

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



                    String namex  = getIntent().getExtras().getString("demande_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://steedex.herokuapp.com/api/demande/update/"+namex+"/EnCours";

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




                    String namex  = getIntent().getExtras().getString("demande_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://steedex.herokuapp.com/api/demande/update/"+namex+"/EnTraitement";

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




                    String namex  = getIntent().getExtras().getString("demande_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://steedex.herokuapp.com/api/demande/update/"+namex+"/Valide";

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




                    String namex  = getIntent().getExtras().getString("demande_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://steedex.herokuapp.com/api/demande/update/"+namex+"/Cloture";


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




                    String namex  = getIntent().getExtras().getString("demande_id");

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url ="http://steedex.herokuapp.com/api/demande/update/"+namex+"/Retour";


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





        String id  = getIntent().getExtras().getString("demande_id");
        String addresse =  getIntent().getExtras().getString("demande_adr");
        String name  = getIntent().getExtras().getString("demande_titre");
        String name2  = getIntent().getExtras().getString("demande_quoi");
        String description = getIntent().getExtras().getString("demande_description");
        String etat = getIntent().getExtras().getString("demande_etat") ;
        String type = getIntent().getExtras().getString("demande_type") ;
        String typeDC = getIntent().getExtras().getString("demande_typeDC") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;


        String nomL = getIntent().getExtras().getString("demandeNomLivreur") ;
        String prenomL = getIntent().getExtras().getString("demandePreNomLivreur") ;
        String telL = getIntent().getExtras().getString("demandeTelLivreur") ;




        // ini views
/*
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
*/
        TextView tv_id = findViewById(R.id.aa_demande_id);
        TextView tv_name = findViewById(R.id.aa_demande_titre);
        TextView tv_type = findViewById(R.id.aa_type) ;
        TextView tv_typeDC = findViewById(R.id.aa_type) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_etat  = findViewById(R.id.aa_etat) ;
        TextView tv_adr = findViewById(R.id.addresse5);
       // ImageView img = findViewById(R.id.aa_thumbnail);

        TextView tv_livreur = findViewById(R.id.livreur);
        // setting values to each view

        tv_id.setText(id);
        if (name != null)
        {
            tv_name.setText(name);
            tv_type.setText(type);
           // collapsingToolbarLayout.setTitle(name);

            getSupportActionBar().setTitle(name);


        }
        else{
            tv_name.setText(name2);
            tv_typeDC.setText(typeDC);
        //    collapsingToolbarLayout.setTitle(name2);

            getSupportActionBar().setTitle(name2);

        }

        tv_etat.setText(etat);
        tv_description.setText(description);
        if (addresse != null){
            tv_adr.setText(addresse);

        }
        else{
            tv_adr.setText("-");

        }

        if (description != null){
            tv_description.setText(description);

        }
        else{
            tv_description.setText("-");

        }

        tv_livreur.setText(nomL +" "+prenomL+" ("+telL+") ");



        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
      //  Glide.with(this).load("http://cdn.onlinewebfonts.com/svg/img_568435.png").apply(requestOptions).into(img);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        initToolbar();

    }


    @Override
    public void onBackPressed(){

        Intent intent = new Intent(DemandeActivity.this,MainActivityDemandes.class);
        startActivity(intent);
    }

    private void initToolbar() {

    }

}
