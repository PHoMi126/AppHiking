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
import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

    EditText location_input, date_input, length_input, description_input;
    Button add_button;
    RadioGroup parking_radioGroup, difficulty_radioGroup;
    RadioButton parking_choice, difficulty_choice;
    TextInputLayout layout_location, layout_date, layout_length, layout_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Create New Hike");

        location_input = findViewById(R.id.location_input);
        date_input = findViewById(R.id.date_input);
        length_input = findViewById(R.id.length_input);
        difficulty_radioGroup = findViewById(R.id.difficulty_radioGroup);
        description_input = findViewById(R.id.description_input);
        parking_radioGroup = (findViewById(R.id.parking_radioGroup));
        add_button = findViewById(R.id.add_button);
        layout_location = findViewById(R.id.layout_location);
        layout_date = findViewById(R.id.layout_date);
        layout_length = findViewById(R.id.layout_length);
        layout_description = findViewById(R.id.layout_description);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAdd();
            }
        });
    }

    private void validateAdd() {
        DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
        int selectedIdParking = parking_radioGroup.getCheckedRadioButtonId();
        int selectedIdDiff = difficulty_radioGroup.getCheckedRadioButtonId();

        final String location = location_input.getText().toString().trim();
        final String date = date_input.getText().toString().trim();
        final String parking = (parking_choice = findViewById(selectedIdParking)).getText().toString().trim();
        final String length = length_input.getText().toString().trim();
        final String difficulty = (difficulty_choice = findViewById(selectedIdDiff)).getText().toString().trim();
        final String description = description_input.getText().toString().trim();

        boolean isValid = true;
        if (location.isEmpty()) {
            layout_location.setError("Please input location");
            isValid = false;
        } else {
            layout_location.setErrorEnabled(false);
        }

        if(date.isEmpty()) {
            layout_date.setError("Please input date");
            isValid = false;
        } else {
            layout_date.setErrorEnabled(false);
        }

        if(length.isEmpty()) {
            layout_length.setError("Please input length");
            isValid = false;
        } else {
            layout_length.setErrorEnabled(false);
        }

        if (isValid) {
            myDB.addHikeSession(location, date, parking, length, difficulty, description);
            finish();
        }
    }
}