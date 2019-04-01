package com.dspot.declex.example.todo;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dspot.declex.example.todo.model.TodoDatabase;

public class DatabaseInstance {
    private static final String DB_NAME = "todo_db";

    private static TodoDatabase INSTANCE;

    public static TodoDatabase get() {
        return INSTANCE;
    }


    public static void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    TodoDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
    }
}
