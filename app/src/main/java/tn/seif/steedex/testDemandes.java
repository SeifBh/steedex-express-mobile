package tn.seif.steedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import tn.seif.steedex.Utils.SaveSharedPreference;
import tn.seif.steedex.Utils.fetchData;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.w3c.dom.Text;

public class testDemandes extends AppCompatActivity {
    public static TextView data ;
    public static TextView idUser ;
    private SwipeRefreshLayout refreshLayout;
    Button logoutBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_demandes);
        data = (TextView)findViewById(R.id.textView);
        idUser = (TextView)findViewById(R.id.idUser);
        idUser.setText("Bonjour" + SaveSharedPreference.getUserId(getApplicationContext()));
        logoutBT = (Button) findViewById(R.id.logoutBT);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);


        fetchData process = new fetchData();
        process.execute();



        logoutBT.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logout();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshItems();
            }
        });


    }


    public void refreshItems() {

        fetchData process = new fetchData();

        process.execute();


        refreshLayout.setRefreshing(false);
    }


    /**
     * Logout
     * TODO: Please modify according to your need it is just an example
     */
    public void logout() {
        SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
