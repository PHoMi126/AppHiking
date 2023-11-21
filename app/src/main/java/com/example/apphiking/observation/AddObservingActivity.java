package com.example.apphiking.observation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apphiking.R;
import com.example.apphiking.db.ObserveDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

public class AddObservingActivity extends AppCompatActivity {

    EditText observation_input, date_input, comment_input;
    Button add_observation_button;
    TextInputLayout layout_observation, layout_date, layout_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observing);
        setTitle("Create New Observation");

        observation_input = findViewById(R.id.observation_input);
        date_input = findViewById(R.id.date_input);
        comment_input = findViewById(R.id.comment_input);
        layout_observation = findViewById(R.id.layout_observation);
        layout_date = findViewById(R.id.layout_date);
        layout_comment = findViewById(R.id.layout_comment);
        add_observation_button = findViewById(R.id.add_observation_button);

        add_observation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAdd();
            }
        });
    }

    private void validateAdd() {
        ObserveDatabaseHelper oDB = new ObserveDatabaseHelper(AddObservingActivity.this);

        final String observation = observation_input.getText().toString();
        final String date = date_input.getText().toString();
        final String comment = comment_input.getText().toString();

        boolean isValid = true;
        if (observation.isEmpty()) {
            layout_observation.setError("Please input observation");
            isValid = false;
        } else {
            layout_observation.setErrorEnabled(false);
        }

        if(date.isEmpty()) {
            layout_date.setError("Please input date");
            isValid = false;
        } else {
            layout_date.setErrorEnabled(false);
        }

        if (isValid) {
            oDB.addObservation(observation, date, comment);
            finish();
        }
    }
}