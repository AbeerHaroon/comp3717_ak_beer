package com.example.projectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ExerciseActivity extends AppCompatActivity {
    EditText setedit;
    EditText numedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Integer set = (Integer) getIntent().getExtras().get("sets");
        Integer num = (Integer) getIntent().getExtras().get("nums");
        setedit = findViewById(R.id.editTextNumber);
        numedit = findViewById(R.id.editTextNumber2);
        setedit.setText(String.valueOf(set));
        numedit.setText(String.valueOf(num));
        Spinner spinner = findViewById(R.id.exercise_spinner);
        final ImageView model = findViewById(R.id.modelImage);
        final ImageView muscles = findViewById(R.id.muscleImage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.exercise_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        model.setBackgroundResource(R.drawable.situp);
                        muscles.setBackgroundResource(R.drawable.rectus);
                        break;
                    case 1:
                        model.setBackgroundResource(R.drawable.pushup);
                        muscles.setBackgroundResource(R.drawable.pects);
                        break;
                    case 7:
                        onCustomClick(view);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onCustomClick(View v) {
        Intent intent = new Intent(getApplicationContext(), CustomExerciseActivity.class);
        startActivity(intent);
    }
}