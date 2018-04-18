package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataSkills extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editplayerid, editovrrate, editattrate, editdefrate, editpos;
    Button buttonaddskills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_data_skills);


        myDb = new DataBaseHelper(this);

        editplayerid = findViewById(R.id.editText_pid);
        editovrrate = findViewById(R.id.editText_ovr);
        editattrate = findViewById(R.id.editText_att);
        editdefrate = findViewById(R.id.editText_def);
        editpos = findViewById(R.id.editText_pos);

        buttonaddskills = findViewById(R.id.button_addskills);
        AddSkills();
    }

    public void AddSkills() {
        buttonaddskills.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
