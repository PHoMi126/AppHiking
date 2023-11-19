package com.example.apphiking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add_button, view_data_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.add_button);
        view_data_button = findViewById(R.id.view_data_button);
        setTitle("Hiking App");

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        MainActivity.this,
                        AddActivity.class
                );
                startActivity(i);
            }
        });

        view_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        MainActivity.this,
                        ViewDataActivity.class
                );
                startActivity(i);
            }
        });
    }
}