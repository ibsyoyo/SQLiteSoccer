package com.example.ibrahim.sqlitesoccer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ibrahim on 4/16/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String SELECT_QUERY = " ";
    public static SQLiteDatabase db;
    public static final String DATABASE_NAME = "Soccer.db";
    private static final String TABLE_NAME = "players_table";
    private static final String COL_1 = "player_id";
    private static final String COL_2 = "team_id";
    private static final String COL_3 = "jersey_num";
    private static final String COL_4 = "player_name";


    //public static final String DATABASE_TEAM = "Team.db";
    private static final String TABLE_TEAM = "team_table";
    private static final String COL_1t = "team_id";
    private static final String COL_2t = "league_id";
    private static final String COL_3t = "team_name";
    private static final String COL_4t = "win";
    private static final String COL_5t = "draw";
    private static final String COL_6t = "loss";

   // public static final String DATABASE_SKILLS = "Skills.db";
    private static final String TABLE_SKILLS = "skills_table";
    private static final String COL_1s = "player_id";
    private static final String COL_2s = "ovr_rate";
    private static final String COL_3s = "att_rate";
    private static final String COL_4s= "def_rate";
    private static final String COL_5s= "position";

    private static final String TABLE_SALARY = "salary_table";
    private static final String COL_1sa = "player_id";
    private static final String COL_2sa = "salary";



    //private string create tables
    private static final String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2  + " INTEGER, " + COL_3 + " INTEGER, " + COL_4 + " TEXT/*, FOREIGN KEY("+COL_2+") REFERENCES " +TABLE_TEAM +" ("+COL_1t +")*/);";

    //THIS TABLE IS NOT CORRECT
    private static final String CREATE_TEAM_TABLE = "CREATE TABLE " + TABLE_TEAM + "(" + COL_1t + " INTEGER, " +
            COL_2t + " INTEGER, " + COL_3t + " TEXT, " + COL_4t + " INTEGER, " + COL_5t + " INTEGER, " + COL_6t +" INTEGER);";


    //THIS TABLE IS NOT CORRECT... re written

    private static final String CREATE_SKILLS_TABLE = "CREATE TABLE " + TABLE_SKILLS + "(" + COL_1s + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2s  + " INTEGER, " + COL_3s + " INTEGER, " + COL_4s + " TEXT, " + COL_5s + " TEXT);";


    private static final String CREATE_SALARY_TABLE = "CREATE TABLE " + TABLE_SALARY + "(" + COL_1sa + " INTEGER PRIMARY KEY, " +
            COL_2sa  + " INTEGER);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        db.execSQL(CREATE_SALARY_TABLE);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALARY);

        onCreate(db);

    }

    public boolean insertTeam(String teamid, String leagueid, String teamname, String win, String draw, String loss) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1t, teamid);
        contentValues.put(COL_2t, leagueid);
        contentValues.put(COL_3t, teamname);
        contentValues.put(COL_4t, win);
        contentValues.put(COL_5t, draw);
        contentValues.put(COL_6t, loss);

        long result = db.insert(TABLE_TEAM, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertPlayer(String playername, String jerseynum, String teamid) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, playername);
        contentValues.put(COL_3, jerseynum);
        contentValues.put(COL_4, teamid);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertSalary(String playerid, String sal) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1sa, playerid);
        contentValues.put(COL_2sa, sal);


        long result = db.insert(TABLE_SALARY, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertSkills(String playerid, String ovrrate, String attrate, String defrate, String position) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1s, playerid);
        contentValues.put(COL_2s, ovrrate);
        contentValues.put(COL_3s, attrate);
        contentValues.put(COL_4s, defrate);
        contentValues.put(COL_5s, position);

        long result = db.insert(TABLE_SKILLS, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor getAllData(){
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_NAME, null );
        return results;
    }

    public Cursor getAllDataTeam(){
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_TEAM, null );
        return results;
    }
    public Cursor getSpecifiedData(String query){
        SELECT_QUERY = query;
        this.db = this.getWritableDatabase();
        Cursor result = this.db.rawQuery(query, null);
        return result;
    }

    public Cursor getAllDataSkills(){
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_SKILLS, null );
        return results;
    }

    public Cursor getAllDataSalary(){
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_SALARY, null );
        return results;
    }


}
