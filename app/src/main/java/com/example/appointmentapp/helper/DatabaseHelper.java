package com.example.appointmentapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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

    public DatabaseHelper(@Nullable Context context) {
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

    //create new user data
    public void addUser(String username, String name, String preferredTimezone){
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

    //Update existed user data
    public void updateUser(String row_id, String username, String name, String preferredTimezone){
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

    //Check if username already exist
    public boolean isValueExist(String username){
        String query = "SELECT * FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_USERNAME_USER + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    //Get username

}

//public class DatabaseHelper{
//    private static final String TAG = "DatabaseHelper";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String DATABASE_NAME = "AppointmentApp.db";
//
//    private static final String TABLE_USER = "user";
//
//    private static final String COLUMN_USER_ID = "user_id";
//    private static final String COLUMN_USER_NAME = "user_name";
//    private static final String COLUMN_USER_USERNAME = "user_username";
//    private static final String COLUMN_USER_PREFERRED_TIMEZONE = "user_preferred_timezone";
//
//    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
//            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUMN_USER_NAME + " STRING,"
//            + COLUMN_USER_USERNAME + " STRING,"
//            + COLUMN_USER_PREFERRED_TIMEZONE + " DATETIME" + ")";
//
//    private final Context context;
//    private AppDatabaseHelper appDB;
//    private SQLiteDatabase db;
//
//    public DatabaseHelper(Context ctx) {
//        this.context = ctx;
//        appDB = new AppDatabaseHelper(context);
//    }
//
//    // Open the database connection.
//    public DatabaseHelper open() {
//        db = appDB.getWritableDatabase();
//        return this;
//    }
//
//    public boolean checkIfUserExit(String tableName,String username) {
//        String where = COLUMN_USER_USERNAME+" LIKE '%"+username+"%'";
//        Cursor c = db.query(true, tableName, null,
//                where, null, null, null, null, null);
//        if(c.getCount()>0)
//            return true;
//        else
//            return false;
//    }
//
//    public int GetUserID(String tableName,String username) {
//        String where = COLUMN_USER_USERNAME+" LIKE '%"+username+"%'";
//        Cursor c = db.query(true, tableName, null,
//                where, null, null, null, null, null);
//        if(c.getCount()>0)
//            return c.getInt(0);
//        else
//            return 0;
//    }
//
//    public String GetUserUserName(String tableName,String username) {
//        String where = COLUMN_USER_USERNAME+" LIKE '%"+username+"%'";
//        Cursor c = db.query(true, tableName, null,
//                where, null, null, null, null, null);
//        if(c.getCount()>0)
//            return c.getString(1);
//        else
//            return null;
//    }
//
//    public static class AppDatabaseHelper extends SQLiteOpenHelper {
//        AppDatabaseHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase _db) {
//            _db.execSQL(CREATE_USER_TABLE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
//            Log.w(TAG, "Upgrading application's database from version " + oldVersion
//                    + " to " + newVersion + ", which will destroy all old data!");
//            // Destroy old database:
//            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//            // Recreate new database:
//            onCreate(_db);
//        }
//    }
//    void addUser(String username, String name, String preferredTimezone){
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_USER_USERNAME, username);
//        cv.put(COLUMN_USER_NAME, name);
//        cv.put(COLUMN_USER_PREFERRED_TIMEZONE, preferredTimezone);
//
//        long result = db.insert(TABLE_USER, null, cv);
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//        void updateUser(String row_id, String username, String name, String preferredTimezone){
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_USER_USERNAME, username);
//        cv.put(COLUMN_USER_NAME, name);
//        cv.put(COLUMN_USER_PREFERRED_TIMEZONE, preferredTimezone);
//
//        long result = db.update(TABLE_USER, cv, "_id=?", new String[]{row_id});
//        if (result == -1){
//            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//}
