package com.emc.thye;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db";
    private static final int SCHEMA = 1;
    static String TABLE = "Notes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TEXT = "text";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE + "(" + COLUMN_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME
                    + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TEXT + " TEXT);");
        }
        catch (SQLiteException e){
            if (e.getMessage().contains("no such table")){
                Log.e("SQLExc", "Creating table " + TABLE + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public void addNote(SQLiteDatabase db, String title, String time){
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_DATE + ", " + COLUMN_TEXT + ")" +
                "VALUES ('" + title + "', '" + time + "', '');");
    }

    public void cleanTable(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE);
    }
}
