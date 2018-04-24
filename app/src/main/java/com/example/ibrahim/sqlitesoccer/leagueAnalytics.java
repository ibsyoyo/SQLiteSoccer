package com.example.ibrahim.sqlitesoccer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        setContentView(R.layout.league_analytics);

        helper = new DataBaseHelper(this);
    }

    //Which player has the highest overall rating in a league?
    public void onEnterL1(View v){
        if(v.getId() == R.id.enterL1) {
            EditText league = (EditText)findViewById(R.id.editTextQueryl1);
            String leaguestr = league.getText().toString();
                query = SELECT+" player_name " +
                        FROM+" player_table player JOIN team_table team ON player.team_id = team.team_id JOIN league_table league ON team.leag_id = league.leag_id JOIN "+
                        "skills_table skills ON player.player_id = skills.player_id " +
                        WHERE+" overall_rating = ("+SELECT+" MAX(overall_rating) "+FROM+" skills_table) AND leag_name = "+ leaguestr;
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
                showMessage("Player with the highest overall rating ", buffer.toString());

        }
    }

    //How many teams play in __ league
    public void onEnterL2(View v){
        if (v.getId() == R.id.enterL2) {
            EditText league = (EditText)findViewById(R.id.editTextQueryl2);
            String leaguestr = league.getText().toString();
            query = SELECT+" COUNT(team_id) " +
                    FROM+" team_table team JOIN league_table league ON team.leag_id = league.leag_id " +
                    WHERE+" leag_name = "+leaguestr +";";

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
            showMessage("count for teams in the league ", buffer.toString());

        }
    }

    //What league does each team play in
    public void onEnterL3(View v){
        if (v.getId() == R.id.enterL3) {
            query = SELECT+" team_name, leag_name " +
                    FROM+" team_table team JOIN league_table leagueON team.leag_id = league.leag_id " +
                    GROUP_BY+" leag_name, team_name;";
            ;

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("leag_name : " + results.getString(0) + "\n");
                buffer.append("team_name : " + results.getString(1) + "\n");
            }

            //show all data
            showMessage("Each team with its league ", buffer.toString());

        }
    }
}
