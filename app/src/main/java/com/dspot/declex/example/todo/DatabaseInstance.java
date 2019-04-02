package com.dspot.declex.example.todo;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dspot.declex.example.todo.model.TodoDatabase;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class DatabaseInstance {
    private static final String DB_NAME = "todo_db";

    protected static TodoDatabase INSTANCE;

    public TodoDatabase get() {
        return INSTANCE;
    }

    public DatabaseInstance(Context context) {
        init(context);
    }

    protected void init(Context context) {
        INSTANCE = Room.databaseBuilder(context,
                TodoDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
    }
}
