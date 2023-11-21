package com.example.apphiking.observation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphiking.R;
import com.example.apphiking.db.ObserveDatabaseHelper;

public class UpdateObservationActivity extends AppCompatActivity {

    EditText observation_input_update, date_input_update, comment_input_update;
    Button update_button, delete_button;
    String id, observation, date, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_observation);
        setTitle("Make Observation Changes");

        observation_input_update = findViewById(R.id.observation_input_update);
        date_input_update = findViewById(R.id.date_input_update);
        comment_input_update = findViewById(R.id.comment_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Call first
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call later
                ObserveDatabaseHelper oDB = new ObserveDatabaseHelper(UpdateObservationActivity.this);

                observation = observation_input_update.getText().toString();
                date = date_input_update.getText().toString();
                comment = comment_input_update.getText().toString();

                oDB.updateObservedData(id, observation, date, comment);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });
    }

    private void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("observation") && getIntent().hasExtra("date")
                && getIntent().hasExtra("comment")){
            //Get data from Intent
            id = getIntent().getStringExtra("id");
            observation = getIntent().getStringExtra("observation");
            date = getIntent().getStringExtra("date");
            comment = getIntent().getStringExtra("comment");

            //Set Intent data
            observation_input_update.setText(observation);
            date_input_update.setText(date);
            comment_input_update.setText(comment);
        } else {
            Toast.makeText(this, "No data.",Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + observation + " ?");
        builder.setMessage("Are you sure you want to delete " + observation + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ObserveDatabaseHelper oDB = new ObserveDatabaseHelper(UpdateObservationActivity.this);
                oDB.deleteOneObservedRow(id);
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