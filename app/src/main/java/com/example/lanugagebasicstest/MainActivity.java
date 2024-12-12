package com.example.lanugagebasicstest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        long startTimer = System.currentTimeMillis();
        System.out.println("startTimer: " + startTimer + " milliseconds");

        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTimer = System.currentTimeMillis();
        System.out.println("endTimer: " + endTimer + " milliseconds");

        long difference = endTimer - startTimer;

        long differenceInSeconds = difference / 1000;
        long differenceInMinutes = difference / (1000 * 60);

        System.out.println("Difference in milliseconds: " + difference);
        System.out.println("Difference in seconds: " + differenceInSeconds);
        System.out.println("Difference in minutes: " + differenceInMinutes);

    }
}