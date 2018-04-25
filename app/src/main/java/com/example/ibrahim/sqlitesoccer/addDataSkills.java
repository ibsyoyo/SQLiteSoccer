package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataSkills extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editplayerid, editovrrate, editattrate, editdefrate, editpos;
    String pid, ovr, att, def, pos;
    Button buttonaddskills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_data_skills);


       this.myDb = new DataBaseHelper(this);

        editplayerid = findViewById(R.id.editText_pid);
        editovrrate = findViewById(R.id.editText_ovr);
        editattrate = findViewById(R.id.editText_att);
        editdefrate = findViewById(R.id.editText_def);
        editpos = findViewById(R.id.editText_pos);

        pid = editplayerid.getText().toString();
        ovr = editovrrate.getText().toString();
        att = editattrate.getText().toString();
        def = editdefrate.getText().toString();
        pos = editpos.getText().toString();

        buttonaddskills = findViewById(R.id.button_addskills);
        AddSkills();
    }

    public void AddSkills() {
        buttonaddskills.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(pid.equals(null)) {
                            Toast.makeText(addDataSkills.this, "Player Id must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(ovr.equals(null)) {
                            Toast.makeText(addDataSkills.this, "Overall rating must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(att.equals(null)) {
                            Toast.makeText(addDataSkills.this, "Attack rating must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(def.equals(null)) {
                            Toast.makeText(addDataSkills.this, "Defense rating must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(pos.equals(null)) {
                            Toast.makeText(addDataSkills.this, "Position must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean isInserted = myDb.insertSkills(editplayerid.getText().toString() ,
                                editovrrate.getText().toString(),
                                editattrate.getText().toString(),
                                editdefrate.getText().toString(),
                                editpos.getText().toString());

                        if(isInserted == true)
                            Toast.makeText(addDataSkills.this, "Inserted new skills to table.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addDataSkills.this, "Could NOT insert new skill set to table.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }



}
