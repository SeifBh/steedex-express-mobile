package tn.seif.steedex;

import android.app.Application;

public class SteedexApplication  extends Application {


    public static final String BASE_URL = "http://steedex.herokuapp.com/";


    public String getBASE_URL() {
        return BASE_URL ;
    }


}
