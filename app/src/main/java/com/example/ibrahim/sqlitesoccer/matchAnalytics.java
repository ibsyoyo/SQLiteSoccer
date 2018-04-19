package com.example.ibrahim.sqlitesoccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class matchAnalytics extends AppCompatActivity {

    String query;
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE ="WHERE";
    public static final String GROUP_BY = "GROUP BY";
    public static final String HAVING = "HAVING";
    DataBaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_analytics);

        helper = new DataBaseHelper(this);
    }
    public void onEnterM1(View v){
        if (v.getId() == R.id.enterM1) {

        }
    }
    public void onEnterM2(View v){
        if (v.getId() == R.id.enterM2) {

        }
    }
    public void onEnterM3(View v){
        if (v.getId() == R.id.enterM3) {

        }
    }
    public void onEnterM4(View v){
        if (v.getId() == R.id.enterM4) {

        }
    }
    public void onEnterM5(View v){
        if (v.getId() == R.id.enterM5) {

        }
    }
}
