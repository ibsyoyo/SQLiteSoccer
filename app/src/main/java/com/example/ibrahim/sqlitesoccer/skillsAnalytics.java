package com.example.ibrahim.sqlitesoccer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class skillsAnalytics extends AppCompatActivity {

    String query;
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE ="WHERE";
    public static final String GROUP_BY = "GROUP BY";
    public static final String HAVING = "HAVING";
    DataBaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skills_analytics);

        helper = new DataBaseHelper(this);
    }
}
