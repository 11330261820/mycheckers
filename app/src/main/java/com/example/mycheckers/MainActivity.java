package com.example.mycheckers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button choose, about, manual;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.ll);
        choose = findViewById(R.id.choose);
        about = findViewById(R.id.about);
        manual = findViewById(R.id.manual);
        choose.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Choose.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        });
        manual.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Manual.class);
            startActivity(intent);
        });
    }
}