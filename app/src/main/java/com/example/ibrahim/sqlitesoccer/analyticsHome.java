package com.example.ibrahim.sqlitesoccer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class analyticsHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics_home);
    }

    public void onTeamAnalytics(View v) {
        if (v.getId() == R.id.buttonTeamAnalytics) {
            Intent intent = new Intent(this, teamAnalytics.class);
            startActivity(intent);
        }
    }
    public void onPlayerAnalytics(View v) {
        if (v.getId() == R.id.playerAnalytics) {
            Intent intent = new Intent(this, playerAnalytics.class);
            startActivity(intent);
        }
    }
    public void onMatchAnalytics(View v) {
        if (v.getId() == R.id.buttonMatchAnalytics) {
            Intent intent = new Intent(this, matchAnalytics.class);
            startActivity(intent);
        }
    }
    public void onLeagueAnalytics(View v) {
        if (v.getId() == R.id.buttonLeagueAnalytics) {
            Intent intent = new Intent(this, leagueAnalytics.class);
            startActivity(intent);
        }
    }
}
