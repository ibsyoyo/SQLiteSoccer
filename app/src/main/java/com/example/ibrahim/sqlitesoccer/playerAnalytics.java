package com.example.ibrahim.sqlitesoccer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        setContentView(R.layout.player_analytics);

        helper = new DataBaseHelper(this);
    }

    //What team is ____ player on?
    public void onEnterP1(View v){
        if (v.getId() == R.id.enterP1) {
            EditText player = (EditText)findViewById(R.id.editTextQueryp1);
            String playerstr = player.getText().toString();
            query =
                    SELECT + " team_name " +
                    FROM + " team_table " +
                    WHERE + " team_id = " +
                    "(" + SELECT+" team_id " +
                    FROM+" players_table " +
                    //something is wrong here by = sign. this is what the debugger says.
                    WHERE + " player_name = '"+playerstr+"' );";

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

    //Find the names of players who do not have a team
    public void onEnterP2(View v){
        if (v.getId() == R.id.enterP2) {
            query = SELECT+" player_name " +
                    FROM+"  players_table " +
                    WHERE+" team_id = 0;";

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("player_name : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Players without teams ", buffer.toString());

        }
    }

    //What country does ___ name play in
    public void onEnterP3(View v){
        if (v.getId() == R.id.enterP3) {
            EditText player = (EditText)findViewById(R.id.editTextQueryP2);
            String playerstr = player.getText().toString();
            query = SELECT+" country " +
                    FROM+" league_table league JOIN team_table team ON league.leag_id = team.leag_id JOIN player_table player" +
                    "ON player.team_id = team.team_id " +
                    WHERE+" player_name = "+playerstr+";";


            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("country : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Country ", buffer.toString());
        }
    }

    //How much money does __ name make per week
    public void onEnterP4(View v){
        if (v.getId() == R.id.enterP4) {
            EditText player = (EditText)findViewById(R.id.editTextQueryP3);
            String playerstr = player.getText().toString();
            query = SELECT+" salary/26 AS salary_per_week " +
                    FROM+" salary_table salary JOIN player_table player ON salary.player_id = player.player_id " +
                    WHERE+" player_name = " +playerstr +";";



            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("salary : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("salary per week ", buffer.toString());
        }
    }

    //What team does __ name play for, what is his number, what is player id, what is his overall rating and what is his position?
    public void onEnterP5(View v){
        if (v.getId() == R.id.enterP5) {
            EditText player = (EditText)findViewById(R.id.editTextQueryP4);
            String playerstr = player.getText().toString();
            query = SELECT+" player_name, jersey_num, player_id, " +
                        "overall_rating, position "+
                    FROM+" player_table player JOIN skills_table skills ON player.player_id = skills.player_id " +
                    WHERE+" player_name = " +playerstr +";";

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("player_name : " + results.getString(0) + "\n");
                buffer.append("jersey_num : " + results.getString(1) + "\n");
                buffer.append("player_id : " + results.getString(2) + "\n");
                buffer.append("overall_rating : " + results.getString(3) + "\n");
                buffer.append("position : " + results.getString(4) + "\n");
            }

            //show all data
            showMessage("Player information ", buffer.toString());
        }
    }
}
