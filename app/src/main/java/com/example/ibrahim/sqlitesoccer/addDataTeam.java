package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataTeam extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editteamid, editleague, editteamname, editwin, editdraw, editloss;
    Button buttonaddteam;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_team);


        myDb = new DataBaseHelper(this);

        editteamid = findViewById(R.id.editText_teamid);
        editleague = findViewById(R.id.editText_league);
        editteamname = findViewById(R.id.editText_teamname);
        editwin = findViewById(R.id.editText_win);
        editdraw = findViewById(R.id.editText_draw);
        editloss = findViewById(R.id.editText_loss);

        buttonaddteam = findViewById(R.id.button_addteam);
        AddTeam();
    }

    public void AddTeam() {
        buttonaddteam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertTeam(
                                editteamid.getText().toString() ,
                                editleague.getText().toString(),
                                editteamname.getText().toString(),
                                editwin.getText().toString(),
                                editdraw.getText().toString(),
                                editloss.getText().toString());

                        if(isInserted == true)
                            Toast.makeText(addDataTeam.this, "Inserted new team profile.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addDataTeam.this, "Could NOT insert new team profile.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}
