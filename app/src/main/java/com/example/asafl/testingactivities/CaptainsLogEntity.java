package com.example.asafl.testingactivities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CaptainsLogEntity {
    @PrimaryKey(autoGenerate = true) int id;
    String date;
    String name;

    public void setLog(String text) {
        name = text;
//        time = System.currentTimeMillis();
    }

    public String getLog() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String getTime() {
        return date;
    }

    public void setTime(String t) {
        date = t;
    }
}
