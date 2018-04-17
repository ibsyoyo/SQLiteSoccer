package com.example.ibrahim.sqlitesoccer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button buttonviewplayerprofile;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonviewplayerprofile = (Button)findViewById(R.id.buttonviewplayer);
        myDb = new DataBaseHelper(this, "Players.db");
        viewAll();

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
}
