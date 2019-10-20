package tn.seif.stedeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loginrestapi.R;

import tn.seif.stedeex.Utils.SaveSharedPreference;

public class Profile_Activity extends AppCompatActivity {
    public TextView profileName;
    public TextView profileAdresse;
    public TextView profileFrais;
    public TextView profileTypeCompte;
    public TextView profileUsername2;
    public TextView profileEmail;
    public TextView profileTel;
    public TextView profileFiscale;

    String getConnectedUserName ="";
    String getConnectedUserAdresse ="";
    String getConnectedUserFrais ="";
    String getConnectedUserRoles ="";
    String getConnectedUserName2 ="";
    String getConnectedUserEmail="";
    String getConnectedUserTel="";
    String getConnectedUserFiscal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        profileName = (TextView)findViewById(R.id.tv_name);
        profileAdresse = (TextView)findViewById(R.id.tv_address);
        profileFrais = (TextView)findViewById(R.id.fraisLiv);
        profileTypeCompte = (TextView)findViewById(R.id.typerole);
        profileUsername2 = (TextView)findViewById(R.id.usernameC);
        profileEmail = (TextView)findViewById(R.id.emailC);
        profileTel = (TextView)findViewById(R.id.telC);
        profileFiscale = (TextView)findViewById(R.id.fisacle);

        getConnectedUserName = SaveSharedPreference.getUserNom(getApplicationContext()) + " " + SaveSharedPreference.getUserPrenom(getApplicationContext());
        getConnectedUserAdresse = SaveSharedPreference.getUserAdresse(getApplicationContext());
        getConnectedUserFrais = SaveSharedPreference.getUserFrais(getApplicationContext());
        getConnectedUserRoles = SaveSharedPreference.getUserRoles(getApplicationContext());
        getConnectedUserName2 = SaveSharedPreference.getUserName2(getApplicationContext());
        getConnectedUserEmail = SaveSharedPreference.getUserEmail(getApplicationContext());
        getConnectedUserTel = SaveSharedPreference.getUserTel(getApplicationContext());
        getConnectedUserFiscal = SaveSharedPreference.getUserFiscale(getApplicationContext());

        profileName.setText(getConnectedUserName);
        profileAdresse.setText(getConnectedUserAdresse);
        profileFrais.setText(getConnectedUserFrais);
        String connectedIdRole  = SaveSharedPreference.getUserRoles(getApplicationContext());

        String RoleClient = "ROLE_CLIENT";
        String RoleAdmin = "ROLE_ADMIN";
        String Role_Livreur = "ROLE_LIVREUR";

        Boolean isClient = connectedIdRole.toLowerCase().contains(RoleClient.toLowerCase());
        Boolean isAdmin = connectedIdRole.toLowerCase().contains(RoleAdmin.toLowerCase());
        Boolean isLivreur = connectedIdRole.toLowerCase().contains(Role_Livreur.toLowerCase());

        if (isAdmin)
            profileTypeCompte.setText("Admin");


        else if (isClient)
            profileTypeCompte.setText("Client");

        else if (isLivreur)
            profileTypeCompte.setText("Livreur");
        else
            profileTypeCompte.setText("User");


        //profileTypeCompte.setText(getConnectedUserRoles);
        profileUsername2.setText("@"+getConnectedUserName2);
        profileEmail.setText(getConnectedUserEmail);
        profileTel.setText(getConnectedUserTel);
        profileFiscale.setText(getConnectedUserFiscal);


    }
}
