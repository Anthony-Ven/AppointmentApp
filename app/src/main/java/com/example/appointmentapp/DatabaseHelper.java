package com.example.appointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    //DBS Declaration
    private static final String DATABASE_NAME = "AppointmentApp.db";
    private static final int DATABASE_VERSION = 1;

    //Table User
    private static final String TABLE_NAME_USER = "user";
    private static final String COLUMN_ID_USER = "id";
    private static final String COLUMN_NAME_USER = "name";
    private static final String COLUMN_USERNAME_USER = "username";
    private static final String COLUMN_PREFERRED_TIMEZONE_USER = "preferredTimezone";

    //Table Appointment
    private static final String TABLE_NAME_APPOINTMENT = "appointment";
    private static final String COLUMN_ID_APPOINTMENT = "id";
    private static final String COLUMN_CREATOR_ID_APPOINTMENT = "id_creator";
    private static final String COLUMN_TITLE_APPOINTMENT = "title";
    private static final String COLUMN_START_APPOINTMENT = "start";
    private static final String COLUMN_END_APPOINTMENT = "end";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Declare sqlite query
        //Create Table User
        String createTable_User =
                "CREATE TABLE " + TABLE_NAME_USER +
                        " (" + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_USER + " STRING, " +
                        COLUMN_USERNAME_USER + " STRING, " +
                        COLUMN_PREFERRED_TIMEZONE_USER + " STRING);";

        String createTable_Appointment =
                "CREATE TABLE " + TABLE_NAME_APPOINTMENT +
                        " (" + COLUMN_ID_APPOINTMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_CREATOR_ID_APPOINTMENT + "INTEGER, " +
                        COLUMN_TITLE_APPOINTMENT + "STRING, " +
                        COLUMN_START_APPOINTMENT + "DATETIME, " +
                        COLUMN_END_APPOINTMENT + "DATETIME);";
        db.execSQL(createTable_User);
        db.execSQL(createTable_Appointment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_APPOINTMENT);
        onCreate(db);
    }

    void addUser(String username, String name, String preferredTimezone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME_USER, username);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PREFERRED_TIMEZONE_USER, preferredTimezone);

        long result = db.insert(TABLE_NAME_USER, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void updateUser(String row_id, String username, String name, String preferredTimezone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME_USER, username);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PREFERRED_TIMEZONE_USER, preferredTimezone);

        long result = db.update(TABLE_NAME_USER, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }
}
