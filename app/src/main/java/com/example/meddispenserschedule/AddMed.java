package com.example.meddispenserschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMed extends AppCompatActivity {
    private EditText medname;
    private EditText medtime;
    private EditText numberofpills;
    private TimePickerDialog timepicker;
    private Button btnsubmit;
    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;
    private Button saturday;
    private Button sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        medname = findViewById(R.id.mednameinput);
        medtime = findViewById(R.id.timeinput);
        numberofpills = findViewById(R.id.numbpillinput);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);

       medname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if(!hasFocus) {
                   InputMethodManager imm =  (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                   //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
                   imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
               }
           }
       });

        medtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if (hasFocus) {
                    final Calendar cldr = Calendar.getInstance();
                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
                    int minutes = cldr.get(Calendar.MINUTE);
                    // time picker dialog
                    timepicker = new TimePickerDialog(AddMed.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int hour, int minute) {
                            medtime.setText(hour+ ":" + minute);

                        }
                    }, hour,minutes,false);
                    timepicker.show();

                }
            }
        });

        numberofpills.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        btnsubmit = findViewById(R.id.addbutton);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mednamestring = medname.getText().toString();
                String timestring = medtime.getText().toString();
                //format time
                SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
                try {
                    Date tempTime = formatter.parse(timestring);
                    timestring = formatter.format(tempTime);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                String numbpillstring = numberofpills.getText().toString();

                Intent toMedList = new Intent(getApplicationContext(),MainActivity.class);
                toMedList.putExtra("medItem", mednamestring);
                toMedList.putExtra("timeformed",timestring);
                toMedList.putExtra("numberofpills",numbpillstring);
                startActivity(toMedList);
                finish();
            }
        });


    }

}