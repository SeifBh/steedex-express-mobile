package tn.seif.stedeex;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginrestapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tn.seif.stedeex.Models.Anime;
import tn.seif.stedeex.Models.Demande;
import tn.seif.stedeex.Utils.ConnectionDetector;
import tn.seif.stedeex.Utils.SaveSharedPreference;
import tn.seif.stedeex.adapters.RecyclerViewAdapter;

public class MainActivityDemandes extends AppCompatActivity {


    public static String connectIdUser ="";
    public static String connectIdRole ="";
    public  String JSON_URL = "https://steedex.herokuapp.com/api/demandes" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Demande> lstAnime ;
    private RecyclerView recyclerView ;
    private SwipeRefreshLayout refreshLayout;
    String testRoleAdmin = "ROLE_ADMIN";
    String testRoleLivreur = "ROLE_LIVREUR";
    String testRoleClient = "ROLE_CLIENT";
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_main);
        String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());
        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());
        Boolean isAdmin = connectedIdRole.toLowerCase().contains(testRoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(testRoleLivreur.toLowerCase());
        Boolean isClient = connectedIdRole.toLowerCase().contains(testRoleClient.toLowerCase());

        cd = new ConnectionDetector(this);

        if (cd.isConnected())
        {

            if ( isAdmin){
                Log.d("result contain", connectIdRole.contains("ROLE_ADMIN") +"");

                JSON_URL = "https://steedex.herokuapp.com/api/demandes" ;

            }
            else  {
                if (isClient){
                    JSON_URL = "https://steedex.herokuapp.com/api/demande/"+connectedIdUser ;

                }
                if (isLivreur){
                    JSON_URL = "https://steedex.herokuapp.com/api/demande_livreur/"+connectedIdUser ;

                }

            }





            lstAnime = new ArrayList<>() ;
            recyclerView = findViewById(R.id.recyclerviewid);
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

    public void jsonrequest() {
        lstAnime = new ArrayList<>() ;

        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());
        Boolean isAdmin = connectedIdRole.toLowerCase().contains(testRoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(testRoleLivreur.toLowerCase());
        Boolean isClient = connectedIdRole.toLowerCase().contains(testRoleClient.toLowerCase());

        cd = new ConnectionDetector(this);


      if (cd.isConnected())
      {
          JSON_URL = "https://steedex.herokuapp.com/api/demandes" ;

          if ( isAdmin){
              Log.d("result contain", connectIdRole.contains("ROLE_ADMIN") +"");

              JSON_URL = "https://steedex.herokuapp.com/api/demandes" ;

          }
          else  {
              if (isLivreur){
                  String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());

                  JSON_URL = "https://steedex.herokuapp.com/api/demande_livreur/"+connectedIdUser ;

              }
              if (isClient){
                  String connectedIdUser  = SaveSharedPreference.getUserId(getApplicationContext());

                  JSON_URL = "https://steedex.herokuapp.com/api/demande/"+connectedIdUser ;

              }

          }



          refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container2);

          refreshLayout.setRefreshing(false);

          Log.d("executed url JSON",JSON_URL);
          request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {

                  JSONObject jsonObject  = null ;

                  for (int i = 0 ; i < response.length(); i++ ) {


                      try {
                          jsonObject = response.getJSONObject(i) ;
                          Demande demande = new Demande() ;
                          demande.setId(jsonObject.getInt("id"));
                          demande.setTitre(jsonObject.getString("titre"));
                          demande.setEtat(jsonObject.getString("etat"));
                          demande.setType(jsonObject.getString("type"));
                          demande.setNom_prenom_recept(jsonObject.getString("nom_prenom_recept"));
                          demande.setTelephone_recept(jsonObject.getString("telephone_recept"));
                          demande.setMontant(jsonObject.getString("montant"));
                          demande.setNote(jsonObject.getString("note"));
                          demande.setLieu(jsonObject.getString("lieu"));
                         demande.setDescription_produit(jsonObject.getString("desc_prod"));
                          demande.setNote(jsonObject.getString("note"));
                          // demande.id(jsonObject.getInt("id"));

                          lstAnime.add(demande);

                      } catch (JSONException e) {
                          e.printStackTrace();
                      }


                  }

                  setuprecyclerview(lstAnime);

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

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


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
        ProgressBar progressBar= findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


    }
}
