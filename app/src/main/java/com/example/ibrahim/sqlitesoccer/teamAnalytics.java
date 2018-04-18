package com.example.ibrahim.sqlitesoccer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class teamAnalytics extends AppCompatActivity{
    @Override
    String query;
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE ="WHERE";
    public static final String GROUP_BY = "GROUP BY";
    public static final String HAVING = "HAVING";
    DataBaseHelper helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_analytics);
        helper = new DataBaseHelper(this, )
    }
    public void onEnter1(View v){
        EditText team = (EditText)findViewById(R.id.editTextQuery1);
        String teamstr = team.getText().toString();
        if (v.getId() == R.id.enter1) {
            query = "SELECT AVG(skills_table.overall_rating) team_overall " +
                    "FROM skills_table JOIN player_table ON skills_table.player_id = player_table.player_id JOIN team_table ON player_table.player_id = team_table.player_id " +
                    "HAVING team_table.team_name = " + teamstr;



        }
    }
    public void onEnter2(View v){
        if (v.getId() == R.id.enter2) {

        }
    }
    public void onEnter3(View v){
        if (v.getId() == R.id.enter3) {

        }
    }
    public void onEnter4(View v){
        if (v.getId() == R.id.enter4) {

        }
    }
    public void onEnter5(View v){
        if (v.getId() == R.id.enter5) {

        }
    }
    public void onEnter6(View v){
        if (v.getId() == R.id.enter6) {

        }
    }

}
