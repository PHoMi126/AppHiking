package com.example.apphiking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apphiking.db.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText location_input, date_input, length_input, description_input;
    Button update_button, delete_button;
    RadioGroup parking_radioGroup, difficulty_radioGroup;
    RadioButton parking_choice, difficulty_choice;
    String id, location, date, parking, length, difficulty, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Make Changes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        location_input = findViewById(R.id.location_input_update);
        date_input = findViewById(R.id.date_input_update);
        length_input = findViewById(R.id.length_input_update);
        difficulty_radioGroup = findViewById(R.id.difficulty_radioGroup_update);
        description_input = findViewById(R.id.description_input_update);
        parking_radioGroup = (findViewById(R.id.parking_radioGroup_update));
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Call first
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIdParking = parking_radioGroup.getCheckedRadioButtonId();
                int selectedIdDiff = difficulty_radioGroup.getCheckedRadioButtonId();

                //Call later
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);

                location = location_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                parking = (parking_choice = findViewById(selectedIdParking)).getText().toString().trim();
                length = length_input.getText().toString().trim();
                difficulty = (difficulty_choice = findViewById(selectedIdDiff)).getText().toString().trim();
                desc = description_input.getText().toString().trim();

                myDB.updateData(id, location, date, parking, length, difficulty, desc);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("location") && getIntent().hasExtra("date")
                && getIntent().hasExtra("parking") && getIntent().hasExtra("length") && getIntent().hasExtra("difficulty")
                && getIntent().hasExtra("description")){
            //Get data from Intent
            id = getIntent().getStringExtra("id");
            location = getIntent().getStringExtra("location");
            date = getIntent().getStringExtra("date");
            parking = getIntent().getStringExtra("parking");
            length = getIntent().getStringExtra("length");
            difficulty = getIntent().getStringExtra("difficulty");
            desc = getIntent().getStringExtra("desc");

            //Set Intent data
            location_input.setText(location);
            date_input.setText(date);
            parking_choice.setText(parking);
            length_input.setText(length);
            difficulty_choice.setText(difficulty);
            description_input.setText(desc);
        } else {
            Toast.makeText(this, "No data.",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + location + " ?");
        builder.setMessage("Are you sure you want to delete " + location + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
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