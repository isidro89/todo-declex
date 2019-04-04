package com.dspot.declex.example.todo.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = TaskToDo.class, version = TodoDatabase.VERSION
)
public abstract class TodoDatabase extends RoomDatabase {
    static final int VERSION = 4;

    public abstract TaskDao taskDao();
}
