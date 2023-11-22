package com.example.apphiking.observation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphiking.R;
import com.example.apphiking.ViewDataActivity;
import com.example.apphiking.adapter.ObservationAdapter;
import com.example.apphiking.db.ObserveDatabaseHelper;

import java.util.ArrayList;

public class ViewObservationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ObserveDatabaseHelper oDB;
    ArrayList<String> observe_id, observe_observation, observe_date, observe_comment;
    ObservationAdapter observationAdapter;
    ImageView empty_imageView;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_observation);
        setTitle("Diary");

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageView = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_data);

        oDB = new ObserveDatabaseHelper(ViewObservationActivity.this);
        observe_id = new ArrayList<>();
        observe_observation = new ArrayList<>();
        observe_date = new ArrayList<>();
        observe_comment = new ArrayList<>();

        storeDataInArrays();

        observationAdapter = new ObservationAdapter(ViewObservationActivity.this, this, observe_id, observe_observation, observe_date, observe_comment);
        recyclerView.setAdapter(observationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewObservationActivity.this));
    }

    public void storeDataInArrays() {
        Cursor cursor = oDB.readAllObservedData();
        if(cursor.getCount() == 0) {
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else{
            while (cursor.moveToNext()) { //Read all data from cursor
                observe_id.add(cursor.getString(0));
                observe_observation.add(cursor.getString(1));
                observe_date.add(cursor.getString(2));
                observe_comment.add(cursor.getString(3));
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
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
                ObserveDatabaseHelper oDB = new ObserveDatabaseHelper(ViewObservationActivity.this);
                oDB.deleteAllObservedData();
                //Refresh Activity
                Intent intent = new Intent(ViewObservationActivity.this, ViewDataActivity.class);
                startActivity(intent);
                Toast.makeText(ViewObservationActivity.this, "Delete", Toast.LENGTH_SHORT).show();
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