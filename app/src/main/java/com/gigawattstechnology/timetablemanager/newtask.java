package com.gigawattstechnology.timetablemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class newtask extends AppCompatActivity {
Button setdate,setstarttime,setendtime,submit;
TextView setdatetext,setstarttimetext,setendtimetext;
int hour,minute;
EditText TaskName,Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        TaskName=findViewById(R.id.givetaskname);
        Description=findViewById(R.id.givedescription);
        setdate=findViewById(R.id.setdate);
        setdatetext=findViewById(R.id.setdatetext);
        setstarttime=findViewById(R.id.setstarttime);
        setstarttimetext=findViewById(R.id.setstarttimetext);
        setendtime=findViewById(R.id.setendtime);
        setendtimetext=findViewById(R.id.setendtimetext);
        submit=findViewById(R.id.submit);
        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(newtask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String pdate=day+"/"+month+"/"+year;
                        setdatetext.setText(pdate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        setstarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        setstarttimetext.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                };
                TimePickerDialog timePickerDialog=new TimePickerDialog(newtask.this,onTimeSetListener,hour,minute,true);
                timePickerDialog.setTitle("Set Start Time");
                timePickerDialog.show();
            }
        });
        setendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        setendtimetext.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                };
                TimePickerDialog timePickerDialog=new TimePickerDialog(newtask.this,onTimeSetListener,hour,minute,true);
                timePickerDialog.setTitle("Set End Time");
                timePickerDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase=AppDatabase.getDbInstance(newtask.this);
                User user=new User();
                user.taskname=TaskName.getText().toString();
                user.description=Description.getText().toString();
                user.date=setdatetext.getText().toString();
                user.starttime=setstarttimetext.getText().toString();
                user.endtime=setendtimetext.getText().toString();
                appDatabase.userDao().insertUser(user);
                Intent intent=new Intent(newtask.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}