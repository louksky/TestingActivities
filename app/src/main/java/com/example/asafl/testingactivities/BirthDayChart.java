package com.example.asafl.testingactivities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BirthDayChart extends AppCompatActivity {
    EditText logInputText;
    TextView logOutputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibirthday);
        logOutputText = findViewById(R.id.textView);

        // Create the observer which updates the UI.
        final Observer<List<CaptainsLogEntity>> logObserver = new Observer<List<CaptainsLogEntity>>() {
            List<CaptainsLogEntity> logObserver2;

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            StringBuilder sb = new StringBuilder();

            @Override
            public void onChanged(@Nullable final List<CaptainsLogEntity> newLog) {
                // Update the UI, in this case, a TextView.

                sb.setLength(0);
                for (CaptainsLogEntity cle : newLog) {
                    sb.append(cle.date);
                    sb.append(": ");
                    sb.append(cle.name);
                    sb.append('\n');
                }
                Collections.sort(newLog, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        CaptainsLogEntity p1 = (CaptainsLogEntity) o1;
                        CaptainsLogEntity p2 = (CaptainsLogEntity) o2;
                        return p1.getLog().compareToIgnoreCase(p2.getLog());
                    }
                });
                logOutputText.setText(sb);
            }
        };

        LiveData<List<CaptainsLogEntity>> captainsLogEntityLiveData = CaptainsLogDb.getInstance(this).readCaptainsLog();

        captainsLogEntityLiveData.observe(this, logObserver);
    }

    public void onAddClick(View v) {
        Intent intent = new Intent(getBaseContext(), Ibirthday.class);
        startActivity(intent);
    }

}