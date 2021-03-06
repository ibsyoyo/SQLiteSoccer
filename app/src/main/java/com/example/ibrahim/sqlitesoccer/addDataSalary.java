package com.example.ibrahim.sqlitesoccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDataSalary extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editpid, editsalary;
    String pid, salary;
    Button buttonaddsalary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_salary);

        this.myDb = new DataBaseHelper(this);

        editpid = findViewById(R.id.editText_pid);
        editsalary = findViewById(R.id.editText_salary);

        pid = editpid.getText().toString();
        salary = editsalary.getText().toString();

        buttonaddsalary = findViewById(R.id.button_addsalary);
        AddSalary();
    }

    public void AddSalary() {
        buttonaddsalary.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(pid.equals(null)){
                            Toast.makeText(addDataSalary.this, "Player Id Id must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(salary.equals(null)) {
                            Toast.makeText(addDataSalary.this, "Salary must be entered", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean isInserted = myDb.insertSalary(editpid.getText().toString() ,
                                editsalary.getText().toString()) ;

                        if(isInserted == true)
                            Toast.makeText(addDataSalary.this, "Inserted new specific salary.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addDataSalary.this, "Could NOT insert new salary set.", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}
