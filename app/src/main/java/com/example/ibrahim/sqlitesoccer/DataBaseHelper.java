package com.example.ibrahim.sqlitesoccer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ibrahim on 4/16/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Players.db";
    public static final String TABLE_NAME = "players_table";
    public static final String COL_1 = "player_id";
    public static final String COL_2 = "player_name";
    public static final String COL_3 = "jersey_num";
    public static final String COL_4 = "team_id";


    public static final String DATABASE_TEAM = "Team.db";
    public static final String TABLE_TEAM = "team_table";
    public static final String COL_5 = "team_id";
    public static final String COL_6 = "league_id";
    public static final String COL_7 = "team_name";
    public static final String COL_8 = "win";
    public static final String COL_9 = "draw";
    public static final String COL_10 = "loss";


    public DataBaseHelper(Context context, String temp) {
        super(context, temp, null, 1);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table " + TABLE_NAME + "(player_id INTEGER PRIMARY KEY ," +
                    " player_name TEXT, jersey_num INTEGER, team_id INTEGER PRIMARY KEY)");


            db.execSQL("create table " + TABLE_TEAM + "(team_id INTEGER PRIMARY KEY ," +
                    " league_id INTEGER PRIMARY KEY, team_name TEXT, win INTEGER, draw INTEGER, loss INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    public boolean insertTeam(String teamid, String leagueid, String teamname, String win, String draw, String loss) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, teamid);
        contentValues.put(COL_6, leagueid);
        contentValues.put(COL_7, teamname);
        contentValues.put(COL_8, win);
        contentValues.put(COL_9, draw);
        contentValues.put(COL_10, loss);
        long result = db.insert(TABLE_TEAM, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
    }

    public boolean insertPlayer(String playername, String jerseynum, String teamid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, playername);
        contentValues.put(COL_3, jerseynum);
        contentValues.put(COL_4, teamid);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

}