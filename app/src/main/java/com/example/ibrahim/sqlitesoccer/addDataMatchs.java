package com.example.ibrahim.sqlitesoccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataMatchs extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editmid, edittid, edittid2, editref, edithsco, editasco, editdate, editstadium;
    String mid, tid1, tid2, ref, t1sco, t2sco, date, stadium;
    Button buttonaddmatch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_matchs);

        this.myDb = new DataBaseHelper(this);

        editmid = findViewById(R.id.editText_mid);
        edittid = findViewById(R.id.editText_tid);
        edittid2 = findViewById(R.id.editText_tid2);
        editref = findViewById(R.id.editText_ref);
        edithsco = findViewById(R.id.editText_hsco);
        editasco = findViewById(R.id.editText_asco);
        editdate = findViewById(R.id.editText_date);
        editstadium = findViewById(R.id.editText_stadium);

        mid = editmid.getText().toString();
        tid1 = edittid.getText().toString();
        tid2 = edittid2.getText().toString();
        ref = editref.getText().toString();
        t1sco = edithsco.getText().toString();
        t2sco = editasco.getText().toString();
        date = editdate.getText().toString();
        stadium = editstadium.getText().toString();

        buttonaddmatch = findViewById(R.id.button_addmatch);
        AddMatch();
    }

    public void AddMatch() {
        buttonaddmatch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(mid)){
                            Toast.makeText(addDataMatchs.this, "Match Id Id must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(tid1)){
                            Toast.makeText(addDataMatchs.this, "Home team must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(tid2)){
                            Toast.makeText(addDataMatchs.this, "Away team must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(ref)){
                            Toast.makeText(addDataMatchs.this, "Referee must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(t1sco)){
                            Toast.makeText(addDataMatchs.this, "Home team score must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(t2sco)){
                            Toast.makeText(addDataMatchs.this, "Away team score must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(date)){
                            Toast.makeText(addDataMatchs.this, "Date must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(TextUtils.isEmpty(stadium)){
                            Toast.makeText(addDataMatchs.this, "Stadium must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean isInserted = myDb.insertMatchs(editmid.getText().toString() ,
                                edittid.getText().toString(),
                                edittid2.getText().toString(),
                                editref.getText().toString(),
                                edithsco.getText().toString(),
                                editasco.getText().toString(),
                                editdate.getText().toString(),
                                editstadium.getText().toString() );

                        if(isInserted == true)
                            Toast.makeText(addDataMatchs.this, "Inserted new match profile.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addDataMatchs.this, "Could NOT insert new match profile.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}
