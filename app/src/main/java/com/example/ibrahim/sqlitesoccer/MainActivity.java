package com.example.ibrahim.sqlitesoccer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
