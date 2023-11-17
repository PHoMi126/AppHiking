package com.example.apphiking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apphiking.adapter.HikeAdapter;
import com.example.apphiking.db.DatabaseHelper;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> hike_id, hike_location, hike_date, hike_parking, hike_length, hike_difficulty, hike_desc;
    HikeAdapter hikeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new DatabaseHelper(ViewDataActivity.this);
        hike_id = new ArrayList<>();
        hike_location = new ArrayList<>();
        hike_date = new ArrayList<>();
        hike_parking = new ArrayList<>();
        hike_length = new ArrayList<>();
        hike_difficulty = new ArrayList<>();
        hike_desc = new ArrayList<>();

        storeDataInArrays();

        hikeAdapter = new HikeAdapter(ViewDataActivity.this, this, hike_id, hike_location, hike_date, hike_parking, hike_length, hike_difficulty, hike_desc);
        recyclerView.setAdapter(hikeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewDataActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    public void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.",Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()) { //Read all data from cursor
                hike_id.add(cursor.getString(0));
                hike_location.add(cursor.getString(1));
                hike_date.add(cursor.getString(2));
                hike_parking.add(cursor.getString(3));
                hike_length.add(cursor.getString(4));
                hike_difficulty.add(cursor.getString(5));
                hike_desc.add(cursor.getString(6));
            }
        }
    }
}