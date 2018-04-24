package com.example.ibrahim.sqlitesoccer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_analytics);

        helper = new DataBaseHelper(this);
    }

    //List all the teams that are playing on ____ date.
    public void onEnterM1(View v){
        if (v.getId() == R.id.enterM1) {
            EditText date = (EditText)findViewById(R.id.editTextQuerym1);
            String datestr = date.getText().toString();
            query = SELECT+" team_id " +
                    FROM+" team_tables, match_table " +
                    WHERE+" (team_table.team_id = match_table.team_id1 OR team_table.team_id = match_table.team_id2) " +
                    "AND match_table.date = " +datestr + ";";
            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("team_name : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("All teams playing ", buffer.toString());



        }
    }

    //How many matches has ___ referee participated in?
    public void onEnterM2(View v){
        if (v.getId() == R.id.enterM2) {
            EditText ref = (EditText)findViewById(R.id.editTextQuerym2);
            String refstr = ref.getText().toString();
            query = SELECT+" COUNT(referee) " +
                    FROM+" match_table " +
                    WHERE+" referee = " + refstr + ";";
            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("count : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Count of games ", buffer.toString());


        }
    }

    //Where have the ___ team vs ___team played at
    public void onEnterM3(View v){
        if (v.getId() == R.id.enterM3) {
            EditText team1 = (EditText)findViewById(R.id.editTextQuerym3a);
            EditText team2 = (EditText)findViewById(R.id.editTextQuerym3b);

            String team1str = team1.getText().toString();
            String team2str = team2.getText().toString();

            query = SELECT+" stadium " +
                    FROM+" match_table " +
                    WHERE+" team_id1 = "+ team1str +" AND team_id2 = "+ team2str+";";

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("stadium : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("stadiums played at", buffer.toString());

        }
    }

    //How many goals were scored in each match on ___ date
    public void onEnterM4(View v){
        if (v.getId() == R.id.enterM4) {
            EditText date = (EditText)findViewById(R.id.editTextQuerym4);
            String datestr = date.getText().toString();

            query = SELECT+" id1_score + id2_score AS goals, match_id " +
                    FROM+" match_table " +
                    WHERE+" date = " +datestr + " " +
                    GROUP_BY+" match_id, goals;";
            ;

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("goals: " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("goals in each match", buffer.toString());

        }
    }

    //How many games did each referee officiate
    public void onEnterM5(View v){
        if (v.getId() == R.id.enterM5) {
            query = SELECT+" COUNT(referee), referee " +
                    FROM+" match_table "+
                    GROUP_BY+" referee;";


            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("count " + results.getString(0) + "\n");
                buffer.append("referee" + results.getString(1) + "\n");
            }

            //show all data
            showMessage("games reffed by each ref", buffer.toString());

        }
    }
}
