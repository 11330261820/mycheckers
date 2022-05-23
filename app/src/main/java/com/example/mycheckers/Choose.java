package com.example.mycheckers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Choose extends AppCompatActivity {
    Button classic, giveaway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        classic = findViewById(R.id.classic);
        giveaway = findViewById(R.id.giveaway);
        classic.setOnClickListener(v -> {
            Intent intent = new Intent(Choose.this, MyCheckers.class);
            startActivity(intent);
        });
        giveaway.setOnClickListener(v -> {
            Intent intent = new Intent(Choose.this, Giveaway.class);
            startActivity(intent);
        });
    }
}