package com.example.apphiking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ObserveDatabaseHelper extends SQLiteOpenHelper{
    private Context context;
    private static final String DATABASE_NAME = "ObservingActions.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_observe_session";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_OBSERVATION = "observation";
    private static final String COLUMN_DATE = "observe_date";
    private static final String COLUMN_COMMENT = "observe_comment";


    public ObserveDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_OBSERVATION + " TEXT," +
                COLUMN_DATE + " INTEGER," +
                COLUMN_COMMENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addObservation(String observation, String date, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_OBSERVATION, observation);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COMMENT, comment);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) { //App failed to insert data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllObservedData() {
        //SQL query
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateObservedData(String row_id, String observation, String date, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_OBSERVATION, observation);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COMMENT, comment);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + " =? ", new String[]{row_id});
        if (result == -1) { //App failed to update data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneObservedRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + " =? ", new String[]{row_id});
        if (result == -1) { //App failed to delete data
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllObservedData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
