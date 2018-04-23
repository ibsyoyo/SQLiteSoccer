package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataLeague extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editleague, editleaguename, editcountry;
    Button buttonaddleague;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_league);

        this.myDb = new DataBaseHelper(this);

        editleague = findViewById(R.id.editText_lid);
        editleaguename = findViewById(R.id.editText_lname);
        editcountry = findViewById(R.id.editText_country);

        buttonaddleague = findViewById(R.id.button_addleague);
        AddLeague();

    }

    public void AddLeague() {
        buttonaddleague.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
