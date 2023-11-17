package com.example.apphiking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "HikingActions.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_hike_session";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOCATION = "hike_location";
    private static final String COLUMN_DATE = "hike_date";
    private static final String COLUMN_PARKING = "hike_parking";
    private static final String COLUMN_LENGTH = "hike_length";
    private static final String COLUMN_DIFFICULTY = "hike_difficulty";
    private static final String COLUMN_DESCRIPTION = "hike_description";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LOCATION + " TEXT," +
                COLUMN_DATE + " INTEGER," +
                COLUMN_PARKING + " TEXT," +
                COLUMN_LENGTH + " INTEGER," +
                COLUMN_DIFFICULTY + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addHikeSession(String location, String date, String parking, String length, String difficulty, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PARKING, parking);
        cv.put(COLUMN_LENGTH, length);
        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_DESCRIPTION, desc);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) { //App failed to insert data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        //SQL query
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String location, String date, String parking, String length, String difficulty, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PARKING, parking);
        cv.put(COLUMN_LENGTH, length);
        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_DESCRIPTION, desc);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + " =? ", new String[]{row_id});
        if (result == -1) { //App failed to update data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + " =? ", new String[]{row_id});
        if (result == -1) { //App failed to delete data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}