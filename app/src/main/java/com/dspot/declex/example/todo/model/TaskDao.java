package com.dspot.declex.example.todo.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public abstract class TaskDao {

    @Query("select * from TaskToDo")
    public abstract LiveData<List<TaskToDo>> getAllTasks();

    @Query("select * from TaskToDo where id=:id")
    public abstract TaskToDo getTask(Long id);

    @Insert
    public abstract void insert(TaskToDo taskToDo);

    @Update
    public abstract void update(TaskToDo... tasksToDo);

}
