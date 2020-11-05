package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiaryActivity extends AppCompatActivity {
    EditText diaryDate;
    EditText diaryTitle;
    EditText diaryStory;
    Button diaryButton;
    DatabaseReference diaryData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        diaryDate = findViewById(R.id.editTextDate);
        diaryTitle = findViewById(R.id.editTextTitle);
        diaryStory = findViewById(R.id.editTextStory);
        diaryButton = findViewById(R.id.buttonSubmit);
        diaryData = FirebaseDatabase.getInstance().getReference("diary_entries");
        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDiaryEntry();
            }
        });
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void addDiaryEntry() {
        String date = diaryDate.getText().toString().trim();
        String title = diaryTitle.getText().toString().trim();
        String story = diaryStory.getText().toString();
        String id = diaryData.push().getKey();
        DiaryEntry diary = new DiaryEntry(date, title, story);
        Task setValueTask = diaryData.child(id).setValue(diary);
        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                diaryDate.setText("");
                diaryTitle.setText("");
                diaryStory.setText("");
                Toast.makeText(DiaryActivity.this,
                        "Successfully Added Diary.\n" ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DiaryActivity.this,
                        "Failed to Add Diary.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}