package com.example.apphiking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphiking.adapter.HikeAdapter;
import com.example.apphiking.db.DatabaseHelper;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> hike_id, hike_location, hike_date, hike_parking, hike_length, hike_difficulty, hike_desc;
    HikeAdapter hikeAdapter;
    ImageView empty_imageView;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        setTitle("View Hikes");

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageView = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_data);

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
        if(requestCode == 0) {
            recreate();
        }
    }

    public void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
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
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        MenuItem item = menu.findItem(R.id.search_button);
        SearchView searchView = (SearchView) item.getActionView();

        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {
            confirmDeleteAll();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDeleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDB = new DatabaseHelper(ViewDataActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ViewDataActivity.this, ViewDataActivity.class);
                startActivity(intent);
                Toast.makeText(ViewDataActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}