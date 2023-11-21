package com.example.apphiking.observation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apphiking.R;

public class ObservationActivity extends AppCompatActivity {

    Button add_button, view_data_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);

        add_button = findViewById(R.id.add_button);
        view_data_button = findViewById(R.id.view_data_button);
        setTitle("Your Observation");

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        ObservationActivity.this,
                        AddObservingActivity.class
                );
                startActivity(i);
            }
        });

        view_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        ObservationActivity.this,
                        ViewObservationActivity.class
                );
                startActivity(i);
            }
        });
    }
}