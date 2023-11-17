package com.example.apphiking;

import androidx.appcompat.app.AppCompatActivity;

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
                int selectedIdParking = parking_radioGroup.getCheckedRadioButtonId();
                int selectedIdDiff = difficulty_radioGroup.getCheckedRadioButtonId();

                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addHikeSession(location_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        (parking_choice = findViewById(selectedIdParking)).getText().toString().trim(),
                        length_input.getText().toString().trim(),
                        (difficulty_choice = findViewById(selectedIdDiff)).getText().toString().trim(),
                        description_input.getText().toString().trim()
                );
            }
        });
    }
}