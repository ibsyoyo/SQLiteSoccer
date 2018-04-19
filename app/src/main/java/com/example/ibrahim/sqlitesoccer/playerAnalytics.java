package com.example.ibrahim.sqlitesoccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class playerAnalytics extends AppCompatActivity {

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
        setContentView(R.layout.player_analytics);

        helper = new DataBaseHelper(this);
    }
    public void onEnterP1(View v){
        if (v.getId() == R.id.enter1) {

        }
    }
    public void onEnterP2(View v){
        if (v.getId() == R.id.enter2) {

        }
    }
    public void onEnterP3(View v){
        if (v.getId() == R.id.enter3) {

        }
    }
    public void onEnterP4(View v){
        if (v.getId() == R.id.enter4) {

        }
    }
    public void onEnterP5(View v){
        if (v.getId() == R.id.enter5) {

        }
    }
}
