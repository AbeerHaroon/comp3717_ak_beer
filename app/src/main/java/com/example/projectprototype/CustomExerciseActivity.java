package com.example.projectprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomExerciseActivity extends AppCompatActivity {
    EditText customName;
    EditText customSet;
    EditText customNum;
    DatabaseReference customdata;
    Button customSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_exercise);
        customName = findViewById(R.id.editTextcustom);
        customSet = findViewById(R.id.editTextsets);
        customNum = findViewById(R.id.editTextnum);
        customSubmit = findViewById(R.id.buttoncustom);
        customdata = FirebaseDatabase.getInstance().getReference("exercises");
        customSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
    }

    private void addExercise() {
        String exercise = customName.getText().toString().trim();
        String setval = customSet.getText().toString().trim();
        final int finalset = Integer.parseInt(setval);
        String numval = customNum.getText().toString().trim();
        final int finalnum = Integer.parseInt(numval);
        Exercise exer = new Exercise(exercise, finalset, finalnum);
        String id = customdata.push().getKey();
        Task setValueTask = customdata.child(id).setValue(exer);
        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                customName.setText("");
                customSet.setText("");
                customNum.setText("");
                Toast.makeText(CustomExerciseActivity.this,
                        "Successfully Added Exercise.\n" ,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                intent.putExtra("sets", finalset);
                intent.putExtra("nums", finalnum);
                startActivity(intent);
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CustomExerciseActivity.this,
                        "Failed to Add Exercise.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onHomeClick(View v) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
}