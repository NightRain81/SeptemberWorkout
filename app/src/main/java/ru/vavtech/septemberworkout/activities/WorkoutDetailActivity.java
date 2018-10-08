package ru.vavtech.septemberworkout.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.vavtech.septemberworkout.Model.Workout;
import ru.vavtech.septemberworkout.R;

public class WorkoutDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private TextView description;
    private TextView weight;
    private ImageView image;
    private SeekBar weightSeekBar;
    private EditText repsCountEditText;
    private Button saveRecordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        recordRepsCount = (TextView) findViewById(R.id.workout_detail_record_reps_count);
        repsCountEditText = (EditText) findViewById(R.id.workout_detail_reps_count_edit_text);
        weightSeekBar = (SeekBar) findViewById(R.id.workout_detail_weight_seek_bar);

        @SuppressLint("ResourceType") Workout workout = new Workout(getString(R.string.pull_ups), getString(R.string.info_pull_ups), 0, new Date(), 0);
        initGUI(workout);
        addListeners();
    }

    private void addListeners() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                weight.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rec = Integer.parseInt(recordRepsCount.getText().toString());
                int rep = Integer.parseInt(repsCountEditText.getText().toString());
                int weg = weightSeekBar.getProgress();

                if(rep>rec) {
                    recordDate.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.ROOT).format(new Date()));
                    recordRepsCount.setText(String.valueOf(rep));
                    recordWeight.setText(String.valueOf(weg));
                }

            }
        });

    }

    private void initGUI(Workout workout) {
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        description = findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());
        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);
    }


}
