package com.example.dailyroutineplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox1, checkBox2, checkBox3;
    private RadioGroup radioGroup;
    private RatingBar ratingBar;
    private SeekBar seekBar;
    private TextView resultTextView, taskTimeTextView;
    private Switch notificationSwitch;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = findViewById(R.id.checkbox_task1);
        checkBox2 = findViewById(R.id.checkbox_task2);
        checkBox3 = findViewById(R.id.checkbox_task3);
        radioGroup = findViewById(R.id.radio_group_time_of_day);
        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBar_task_time);
        resultTextView = findViewById(R.id.resultTextView);
        taskTimeTextView = findViewById(R.id.taskTimeTextView);
        notificationSwitch = findViewById(R.id.switch_notification);
        submitButton = findViewById(R.id.submitButton);

        taskTimeTextView.setText("Task time: " + seekBar.getProgress() + " minutes");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                taskTimeTextView.setText("Task time: " + progress + " minutes");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Adjusting time...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Time set", Toast.LENGTH_SHORT).show();
            }
        });

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitButton.setOnClickListener(v -> {

            StringBuilder selectedTasks = new StringBuilder("Selected tasks: ");
            if (checkBox1.isChecked())
            {
                selectedTasks.append("\n- ").append(checkBox1.getText());
            }
            if (checkBox2.isChecked())
            {
                selectedTasks.append("\n- ").append(checkBox2.getText());
            }
            if (checkBox3.isChecked())
            {
                selectedTasks.append("\n- ").append(checkBox3.getText());
            }

            int selectedRadioId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioId);
            String timeOfDay = selectedRadioButton.getText().toString();

            float productivityRating = ratingBar.getRating();
            int taskTime = seekBar.getProgress();

            String resultText = selectedTasks.toString() +
                    "\nPreferred time of day: " + timeOfDay +
                    "\nProductivity rating: " + productivityRating +
                    "\nEstimated task time: " + taskTime + " minutes" +
                    "\nNotifications: " + (notificationSwitch.isChecked() ? "On" : "Off");

            resultTextView.setText(resultText);
        });
    }
}



