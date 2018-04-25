package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataLeague extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editleague, editleaguename, editcountry;
    String leagueid, leaguename, country;
    Button buttonaddleague;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_league);

        this.myDb = new DataBaseHelper(this);

        editleague = findViewById(R.id.editText_lid);
        editleaguename = findViewById(R.id.editText_lname);
        editcountry = findViewById(R.id.editText_country);

        leagueid = editleague.getText().toString();
        leaguename = editleaguename.getText().toString();
        country = editcountry.getText().toString();

        buttonaddleague = findViewById(R.id.button_addleague);
        AddLeague();

    }

    public void AddLeague() {
        buttonaddleague.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(leagueid.equals(null)){
                            Toast.makeText(addDataLeague.this, "League Id must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(leaguename.equals(null)){
                            Toast.makeText(addDataLeague.this, "League Name must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(country.equals(null)){
                            Toast.makeText(addDataLeague.this, "Country must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean isInserted = myDb.insertLeague(editleague.getText().toString() ,
                                editleaguename.getText().toString(),
                                editcountry.getText().toString() );

                        if(isInserted == true)
                            Toast.makeText(addDataLeague.this, "Inserted new league info.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addDataLeague.this, "Could NOT insert new league info.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}
