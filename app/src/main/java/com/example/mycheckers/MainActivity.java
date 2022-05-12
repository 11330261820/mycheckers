package com.example.mycheckers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button classicgame;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.ll);
        classicgame = findViewById(R.id.classicgame);
        classicgame.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyCheckers.class);
            startActivity(intent);
        });
    }
}