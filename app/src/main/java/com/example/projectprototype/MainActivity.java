package com.example.projectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onExerciseClick(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("sets", 0);
        intent.putExtra("nums", 0);
        startActivity(intent);
    }

    public void onDiaryClick(View view) {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);
    }

    public void onVitalClick(View view) {
        Intent intent = new Intent(this, VitalsActivity.class);
        startActivity(intent);
    }

    public void onNewsClick(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void onQuicklineClick(View view) {
        Intent intent = new Intent(this, QuickLineActivity.class);
        startActivity(intent);
    }

}