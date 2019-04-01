package com.dspot.declex.example.todo.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class TaskDao {

    @Query("select * from TaskToDo")
    public abstract LiveData<List<TaskToDo>> getAllTasks();

    @Insert
    public abstract void insert(TaskToDo taskToDo);

}
