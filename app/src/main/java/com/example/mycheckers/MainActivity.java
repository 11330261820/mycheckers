package com.example.mycheckers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button classicgame, mode, owngame, howtoplay, settings;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.ll);
        classicgame = findViewById(R.id.classicgame);
        mode = findViewById(R.id.mode);
        owngame = findViewById(R.id.owngame);
        howtoplay = findViewById(R.id.howtoplay);
        settings = findViewById(R.id.settings);
        classicgame.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyCheckers.class);
            startActivity(intent);
        });
        mode.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChooseMode.class);
            startActivity(intent);
        });
    }
}