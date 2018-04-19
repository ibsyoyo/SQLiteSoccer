package com.example.ibrahim.sqlitesoccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class leagueAnalytics extends AppCompatActivity {

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
        setContentView(R.layout.league_analytics);

        helper = new DataBaseHelper(this);
    }

    public void onEnterL1(View v){
        if(v.getId() == R.id.enterL1) {

        }
    }
    public void onEnterL2(View v){
        if (v.getId() == R.id.enterL2) {

        }
    }
    public void onEnterL3(View v){
        if (v.getId() == R.id.enterL3) {

        }
    }
}
