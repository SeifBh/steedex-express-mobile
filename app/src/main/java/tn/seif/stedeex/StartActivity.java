package tn.seif.stedeex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginrestapi.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import tn.seif.stedeex.Utils.SaveSharedPreference;

public class StartActivity extends AppCompatActivity {






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
                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

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

}
