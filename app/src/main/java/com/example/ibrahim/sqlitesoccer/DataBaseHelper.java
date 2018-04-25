package com.example.ibrahim.sqlitesoccer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ibrahim and kevin on 4/16/18.
 * test
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";

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
    private static final String COL_4s = "def_rate";
    private static final String COL_5s = "position";

    // table skill
    private static final String TABLE_SALARY = "salary_table";
    private static final String COL_1sa = "player_id";
    private static final String COL_2sa = "salary";

    // Table league
    private static final String TABLE_LEAGUE = "league_table";
    private static final String COL_1l = "league_id";
    private static final String COL_2l = "league_name";
    private static final String COL_3l = "country";

    //table match
    private static final String TABLE_MATCHS = "match_table";
    private static final String COL_1m = "match_id";
    private static final String COL_2m = "team_id1";
    private static final String COL_3m = "team_id2";
    private static final String COL_4m = "referee";
    private static final String COL_5m = "id_score1";
    private static final String COL_6m = "id_score2";
    private static final String COL_7m = "date";
    private static final String COL_8m = "stadium";

    //private string create tables
    private static final String CREATE_PLAYER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_2 + " INTEGER, "
            + COL_3 + " TEXT, "
            + COL_4 + " INTEGER, "
            + " FOREIGN KEY(" + COL_2 + ") REFERENCES " + TABLE_TEAM + " (" + COL_1t + "));";

    private static final String CREATE_TEAM_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TEAM + "("
            + COL_1t + " INTEGER PRIMARY KEY, "
            + COL_2t + " INTEGER, "
            + COL_3t + " TEXT, "
            + COL_4t + " INTEGER, "
            + COL_5t + " INTEGER, "
            + COL_6t + " INTEGER, "
            + " FOREIGN KEY(" + COL_2t + ") REFERENCES " + TABLE_LEAGUE + " (" + COL_1l + "));";

    private static final String CREATE_SKILLS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SKILLS + "("
            + COL_1s + " INTEGER PRIMARY KEY, "
            + COL_2s + " INTEGER, "
            + COL_3s + " INTEGER, "
            + COL_4s + " INTEGER, "
            + COL_5s + " TEXT,"
            + " FOREIGN KEY(" + COL_1s + ") REFERENCES " + TABLE_NAME + " (" + COL_1 + "));";

    private static final String CREATE_SALARY_TABLE = "CREATE TABLE " + TABLE_SALARY + "("
            + COL_1sa + " INTEGER PRIMARY KEY, "
            + COL_2sa + " INTEGER,"
            + " FOREIGN KEY(" + COL_1sa + ") REFERENCES " + TABLE_NAME + " (" + COL_1 + "));";

    private static final String CREATE_LEAGUE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LEAGUE + "("
            + COL_1l + " INTEGER PRIMARY KEY, "
            + COL_2l + " TEXT, "
            + COL_3l + " TEXT)";

    private static final String CREATE_MATCHS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MATCHS + "("
            + COL_1m + " INTEGER PRIMARY KEY, "
            + COL_2m + " INTEGER, "
            + COL_3m + " INTEGER, "
            + COL_4m + " TEXT, "
            + COL_5m + " INTEGER, "
            + COL_6m + " INTEGER, "
            + COL_7m + " INTEGER, "
            + COL_8m + " TEXT, "
            + "FOREIGN KEY(" + COL_2m + ") REFERENCES " + TABLE_TEAM + " (" + COL_1t + "), "
            + "FOREIGN KEY(" + COL_3m + ") REFERENCES " + TABLE_TEAM + " (" + COL_1t + "));";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 8);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        db.execSQL(CREATE_SALARY_TABLE);
        db.execSQL(CREATE_LEAGUE_TABLE);
        db.execSQL(CREATE_MATCHS_TABLE);


        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAGUE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHS);
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
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertPlayer(String playername, String jerseynum, String teamid) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, teamid);
        contentValues.put(COL_3, jerseynum);
        contentValues.put(COL_4, playername);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
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
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertSkills(String playerid, String ovrrate, String attrate, String defrate, String pos) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1s, playerid);
        contentValues.put(COL_2s, ovrrate);
        contentValues.put(COL_3s, attrate);
        contentValues.put(COL_4s, defrate);
        contentValues.put(COL_5s, pos);

        long result = db.insert(TABLE_SKILLS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertMatchs(String matchid, String teamid1, String teamid2, String referee,
                                String idscore1, String idscore2, String date, String stadium) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1m, matchid);
        contentValues.put(COL_2m, teamid1);
        contentValues.put(COL_3m, teamid2);
        contentValues.put(COL_4m, referee);
        contentValues.put(COL_5m, idscore1);
        contentValues.put(COL_6m, idscore2);
        contentValues.put(COL_7m, date);
        contentValues.put(COL_8m, stadium);

        long result = db.insert(TABLE_MATCHS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertLeague(String leagueid, String leaguename, String country) {
        this.db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1l, leagueid);
        contentValues.put(COL_2l, leaguename);
        contentValues.put(COL_3l, country);

        long result = db.insert(TABLE_LEAGUE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_NAME, null);
        return results;
    }

    public Cursor getAllDataTeam() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_TEAM, null);
        return results;
    }

    public Cursor getAllDataSkills() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_SKILLS, null);
        return results;
    }

    public Cursor getAllDataSalary() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_SALARY, null);
        return results;
    }

    public Cursor getAllDataLeague() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_LEAGUE, null);
        return results;
    }

    public Cursor getAllDataMatchs() {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery("select * from " + TABLE_MATCHS, null);
        return results;
    }

    public Cursor getSpecifiedData(String query) {
        this.db = this.getWritableDatabase();

        Cursor results = this.db.rawQuery(query, null);
        return results;
    }
    public void createNewView(String queryView){
        this.db = this.getWritableDatabase();
        db.execSQL(queryView);
    }


}