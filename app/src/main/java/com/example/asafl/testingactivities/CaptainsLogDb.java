package com.example.asafl.testingactivities;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

@Database(entities = {CaptainsLogEntity.class}, version = 1)
public abstract class CaptainsLogDb extends RoomDatabase {
    private static final String TAG = CaptainsLogDb.class.getSimpleName();
    private static CaptainsLogDb INSTANCE;

    public static CaptainsLogDb getInstance(Context context) {
        synchronized (CaptainsLogDb.class) {
            if (INSTANCE == null) {
                // notice getApplicationContext
                // -- it prevents the memory leak that would happen if the activity was passed
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CaptainsLogDb.class, "clog.db")
//                        .addMigrations(MIGRATION_1_2) // placeholder for future db versions
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract CaptainsLogDao getCaptainsLogDao();

    public LiveData<List<CaptainsLogEntity>> readCaptainsLog() {
        LiveData<List<CaptainsLogEntity>> captainsLogEntities = getCaptainsLogDao().loadCaptainsLog();
        return captainsLogEntities;
    }

    public void writeToCaptainsLog(final CaptainsLogEntity logEntity) {
        // there are better ways than creating a new thread and a new Runnable every time.
        // we will cover these ways later (-au)
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCaptainsLogDao().insertToLog(logEntity);
            }
        }).start();
    }
}