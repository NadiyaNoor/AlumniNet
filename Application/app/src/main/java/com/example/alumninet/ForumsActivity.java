package com.example.alumninet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ForumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String titleText = intent.getStringExtra("Title");
        String descriptionText = intent.getStringExtra("Description");

        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);

        title.setText(titleText);
        description.setText(descriptionText);

    }
}
