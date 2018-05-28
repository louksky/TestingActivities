package com.example.asafl.testingactivities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CaptainsLogEntity {
    @PrimaryKey(autoGenerate = true) int id;
    long time;
    String log;

    public void setLog(String text) {
        log = text;
//        time = System.currentTimeMillis();
    }

    public String getLog() {
        return log;
    }

    public int getID() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long t) {
        time = t;
    }
}
