package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addData extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editname, editjersey, editteam;
    String team, jersey, name;
    Button buttonaddplayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        this.myDb = new DataBaseHelper(this);

        editname = findViewById(R.id.editText_name);
        editjersey = findViewById(R.id.editText_jersey);
        editteam = findViewById(R.id.editText_team);

        name = editname.getText().toString();
        jersey = editname.getText().toString();
        team = editname.getText().toString();

        buttonaddplayer = findViewById(R.id.button_addplayer);
        AddPlayer();
    }

    public void AddPlayer() {
        buttonaddplayer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(name.equals(null)){
                            Toast.makeText(addData.this, "Player name must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(jersey.equals(null)){
                            Toast.makeText(addData.this, "Jersey number must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(jersey.equals(null)){
                            Toast.makeText(addData.this, "Team must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean isInserted = myDb.insertPlayer(editname.getText().toString() ,
                                editjersey.getText().toString(),
                                editteam.getText().toString() );

                        if(isInserted == true)
                            Toast.makeText(addData.this, "Inserted new player profile.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addData.this, "Could NOT insert new player profile.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}
