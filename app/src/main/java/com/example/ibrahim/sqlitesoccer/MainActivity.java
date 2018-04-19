package com.example.ibrahim.sqlitesoccer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button buttonviewplayerprofile;
    DataBaseHelper myDb;
    Button buttonviewteamprofile;
    Button buttonviewskillsprofile;
    Button buttonviewsalaryprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonviewplayerprofile = findViewById(R.id.buttonviewplayer);
        myDb = new DataBaseHelper(this);

        viewAll();
        buttonviewteamprofile = findViewById(R.id.buttonviewteam);
        buttonviewteamprofile = (Button)findViewById(R.id.buttonviewteam);
        viewAllTeam();
        buttonviewskillsprofile = findViewById(R.id.buttonviewskills);
        viewAllSkills();
        buttonviewsalaryprofile = findViewById(R.id.buttonviewsalary);
        viewAllSalary();

    }

    public void viewAllSalary(){
        buttonviewsalaryprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllDataSkills();
                        if(results.getCount() == 0 ){
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("player_id : " + results.getString(0) + "\n");
                            buffer.append("salary : " + results.getString(1) + "\n\n");

                        }

                        //show all data from skills
                        showMessage("Salary Table", buffer.toString());

                    }
                }
        );
    }

    public void viewAllSkills(){
        buttonviewskillsprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllDataSkills();
                        if(results.getCount() == 0 ){
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("player_id : " + results.getString(0) + "\n");
                            buffer.append("ovr_rate : " + results.getString(1) + "\n");
                            buffer.append("att_rate : " + results.getString(2) + "\n");
                            buffer.append("def_rate : " + results.getString(3) + "\n");
                            buffer.append("position : " + results.getString(4) + "\n\n");

                        }

                        //show all data from skills
                        showMessage("Skills Table", buffer.toString());

                    }
                }
        );
    }

    public void viewAllTeam(){
        buttonviewteamprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllDataTeam();
                        if(results.getCount() == 0 ){
                            //show message!
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("team_id : " + results.getString(0) + "\n");
                            buffer.append("league_id : " + results.getString(1) + "\n");
                            buffer.append("team_name : " + results.getString(2) + "\n");
                            buffer.append("win : " + results.getString(3) + "\n");
                            buffer.append("draw : " + results.getString(4) + "\n");
                            buffer.append("loss : " + results.getString(5) + "\n\n");

                        }

                        //show all data from team
                        showMessage("Team Table", buffer.toString());

                    }
                }
        );
    }

    public void viewAll(){
        buttonviewplayerprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Cursor results = myDb.getAllData();
                       if(results.getCount() == 0 ){
                           //show message
                           showMessage("Error", "Nothing Found");
                           return;
                       }

                       StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("player_id : " + results.getString(0) + "\n");
                            buffer.append("player_name : " + results.getString(1) + "\n");
                            buffer.append("jersey_num : " + results.getString(2) + "\n");
                            buffer.append("team_id : " + results.getString(3) + "\n\n");

                        }

                        //show all data
                        showMessage("Player Table", buffer.toString());

                    }
                }
        );
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /** Called when the user taps the Send button */
    public void onClickaddplayer(View v) {
        if (v.getId() == R.id.gotoaddplayer){
            Intent intent = new Intent(this, addData.class);
            startActivity(intent);
        }

    }
    public void onClickaddteam(View v) {
        if (v.getId() == R.id.gotoaddteam){
            Intent intent = new Intent(this, addDataTeam.class);
            startActivity(intent);
        }

    }


    public void onAnalytics(View v) {
        if(v.getId() == R.id.analytics_button){
            Intent intent = new Intent(this, analyticsHome.class);
            startActivity(intent);
        }
    }

    public void onClickaddskills(View v) {
        if (v.getId() == R.id.gotoaddskills){
            Intent intent = new Intent(this, addDataSkills.class);
            startActivity(intent);
        }

    }

    public void onClickaddsalary(View v) {
        if (v.getId() == R.id.gotoaddsalary){
            Intent intent = new Intent(this, addDataSalary.class);
            startActivity(intent);
        }

    }


}
