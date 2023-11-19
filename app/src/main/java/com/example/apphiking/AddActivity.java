package com.example.apphiking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.apphiking.db.DatabaseHelper;

public class AddActivity extends AppCompatActivity {

    EditText location_input, date_input, length_input, description_input;
    Button add_button;
    RadioGroup parking_radioGroup, difficulty_radioGroup;
    RadioButton parking_choice, difficulty_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Create New");

        location_input = findViewById(R.id.location_input);
        date_input = findViewById(R.id.date_input);
        length_input = findViewById(R.id.length_input);
        difficulty_radioGroup = findViewById(R.id.difficulty_radioGroup);
        description_input = findViewById(R.id.description_input);
        parking_radioGroup = (findViewById(R.id.parking_radioGroup));
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                int selectedIdParking = parking_radioGroup.getCheckedRadioButtonId();
                int selectedIdDiff = difficulty_radioGroup.getCheckedRadioButtonId();

                final String location = location_input.getText().toString().trim();
                final String date = date_input.getText().toString().trim();
                final String parking = (parking_choice = findViewById(selectedIdParking)).getText().toString().trim();
                final String length = length_input.getText().toString().trim();
                final String difficulty = (difficulty_choice = findViewById(selectedIdDiff)).getText().toString().trim();
                final String description = description_input.getText().toString().trim();

                myDB.addHikeSession(location, date, parking, length, difficulty, description);
                finish();
            }
        });
    }


}