package com.dspot.declex.example.todo;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dspot.declex.example.todo.model.TodoDatabase;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean(scope = EBean.Scope.Singleton)
public class DatabaseInstance {
    private final String DB_NAME = "todo_db";

    protected TodoDatabase INSTANCE;

    @RootContext
    Context context;

    public TodoDatabase get() {
        return INSTANCE;
    }

    @AfterInject
    protected void initializedDependencies(Context context) {
        INSTANCE = Room.databaseBuilder(context,
                TodoDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
    }
}
