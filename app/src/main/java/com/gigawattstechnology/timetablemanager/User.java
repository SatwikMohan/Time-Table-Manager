package com.gigawattstechnology.timetablemanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="Task_name")
    public String taskname;

    @ColumnInfo(name="Description")
    public String description;

    @ColumnInfo(name="Date")
    public String date;

    @ColumnInfo(name="Start_Time")
    public String starttime;

    @ColumnInfo(name="End_Time")
    public String endtime;
}
