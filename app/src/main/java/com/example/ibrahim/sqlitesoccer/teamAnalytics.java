package com.example.ibrahim.sqlitesoccer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class teamAnalytics extends AppCompatActivity{

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
        setContentView(R.layout.team_analytics);
        helper = new DataBaseHelper(this);
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //Enter a team for overall rating
    public void onEnter1(View v){

        if (v.getId() == R.id.enter1) {
            EditText team = (EditText)findViewById(R.id.editTextQuery1);
            String teamstr = team.getText().toString();
            query = SELECT + " AVG(ovr_rate) "
                    + FROM + " skills_table NATURAL JOIN players_table NATURAL JOIN team_table "
                    + WHERE + " team_name = '" + teamstr+"';";
            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("ovr_rate : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Overall Rating for team ", buffer.toString());




        }
    }
    //Enter two teams to see how many times they played each other
    public void onEnter2(View v){
        if (v.getId() == R.id.enter2) {
            EditText team1 = (EditText)findViewById(R.id.editTextQuery2Team1);
            String teamstr1 = team1.getText().toString();
            EditText team2 = (EditText)findViewById(R.id.editTextQuery2Team2);
            String teamstr2 = team2.getText().toString();
            String queryView = "CREATE VIEW same_match_up AS " +
                    SELECT + " DISTINCT " + "match_id" + " "+
                    FROM + " " + "team_table" + " AS t1, " + "team_table" + " AS t2, " + "team_table" + " AS matchs " +
                    WHERE + " t1.team_id <> t2.team_id AND (t1.team_id = matchs.team_id1 OR t1.team_id = matchs.team_id2) " +
                    "AND (t2.team_id = matchs.team_id1 OR t2.team_id = matchs.team_id2);";

            query= SELECT + "COUNT(*) " +
                    FROM + " same_match_up JOIN match_table " +
                    WHERE + " (team_id1 = " + teamstr1 + " AND team_id2 = " + teamstr2 + ") OR (team_id1 = " +
                     teamstr2 + " AND team_id2 = " + teamstr1 + ");";

                helper.createNewView(queryView);
                Cursor results = helper.getSpecifiedData(query);
                if(results.getCount() == 0 ){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("count of times played : " + results.getString(0) + "\n");
                }

                //show all data
                showMessage("Times played each other ", buffer.toString());
        }
    }
    //Enter a team to find their record
    public void onEnter3(View v){
            EditText team = (EditText)findViewById(R.id.editTextQuery3);
            String teamstr = team.getText().toString();
            if (v.getId() == R.id.enter3) {
                query = SELECT + " win, loss, draw "
                + FROM + " team_table "
                + WHERE + " team_name = '" + teamstr+"';";
                Cursor results = helper.getSpecifiedData(query);
                if(results.getCount() == 0 ){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("W : " + results.getString(0) + "\n");
                    buffer.append("L : " + results.getString(1) + "\n");
                    buffer.append("D : " + results.getString(2) + "\n");
                }

                    //show all data
                showMessage("Win, Loss, Draw Record ", buffer.toString());

            }

    }
    //If you would like to find the record of the team above after a certain date enter the team above and the date below
    //Not finished
    public void onEnter4(View v){
            EditText team = (EditText)findViewById(R.id.editTextQuery3);
            EditText date = (EditText)findViewById(R.id.editTextQuery4);
            String teamstr = team.getText().toString();
            String datestr = team.getText().toString();
            if (v.getId() == R.id.enter4) {
                query = SELECT+" AVG(ovr_rate) team_overall "
                        + FROM +"skills_table skills JOIN players_table player ON skills.player_id = player.player_id JOIN team_table team ON player.player_id = team.player_id "
                        + HAVING+" team_name = '" + teamstr+"';";
                Cursor results = helper.getSpecifiedData(query);
                if(results.getCount() == 0 ){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()){
                    buffer.append(" : " + results.getString(0) + "\n");
                }

                //show all data
                showMessage("Overall Rating for team ", buffer.toString());




            }
    }
        //Enter a team to see the salary of all the player
        public void onEnter5(View v){
            EditText team = (EditText)findViewById(R.id.editTextQuery5);
            String teamstr = team.getText().toString();
            if (v.getId() == R.id.enter5) {
                query = SELECT +" player_name, salary "
                    + FROM + " salary_table NATURAL JOIN players_table "
                    + WHERE + " team_id = '" + teamstr+"';";

                Cursor results = helper.getSpecifiedData(query);
                if(results.getCount() == 0 ){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("salary : " + results.getString(0) + "\n");
                    buffer.append("player_name : " + results.getString(1)+ "\n");
                }

                //show all data
                showMessage("Salary for each player on a team ", buffer.toString());




            }
        }
    //Press enter to see what team has the most amount of wins
    public void onEnter6(View v){
        if (v.getId() == R.id.enter6) {
            query =SELECT + "team_name" +
                    FROM +"team_table" +
                    WHERE +"won = ("+SELECT+" MAX(won) "+FROM+" team_table);";

            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                    buffer.append("Team with most wins : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Team with the most wins ", buffer.toString());

        }
    }
    //Enter a team to find their best defender
    public void onEnter7(View v){
        if (v.getId() == R.id.enter7) {
            EditText team = (EditText)findViewById(R.id.editTextQuery6);
            String teamstr = team.getText().toString();
            query = SELECT +" player_name " +
                    FROM +" players_table player NATURAL JOIN team_table team NATURAL JOIN skills_table skills " +
                    WHERE +" def_rate = ("+SELECT+" MAX(def_rate) "+FROM+" skills_table) AND team_name = '" + teamstr+"';";


            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("Best defender for " + teamstr+" : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Best defender ", buffer.toString());

        }
    }
    //Enter a stadium to find teams that have never played there
    public void onEnter8(View v){
        if (v.getId() == R.id.enter8) {
            EditText stadium = (EditText)findViewById(R.id.editTextQuery5);
            String stadiumstr = stadium.getText().toString();
            query = SELECT+" team_name " +
                    FROM +" team_table, match_table " +
                    WHERE+" (team_id = team_id1 OR team_id = team_id2) " +
                        "AND stadium <> '" + stadiumstr+"';";


            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("Teams never played at " + stadiumstr+" : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Teams never played at ", buffer.toString());

        }
    }
    //Press enter to find how many teams have more than 3 strikers
    public void onEnter9(View v) {
        if(v.getId()== R.id.enter9) {
            query = SELECT+" COUNT(position) AS count_st, team_name " +
                    FROM+" team_table team JOIN players_table player JOIN skills_table skills " +
                    WHERE+" position = st " +
                    GROUP_BY+" team_name " +
                    HAVING+" count_st >3;";



            Cursor results = helper.getSpecifiedData(query);
            if(results.getCount() == 0 ){
                //show message
                showMessage("Error", "Nothing Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (results.moveToNext()){
                buffer.append("Team with more than 3 strikers : " + results.getString(0) + "\n");
            }

            //show all data
            showMessage("Teams with more than 3 strikers ", buffer.toString());

        }
    }

}
