package com.dspot.declex.example.todo.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public abstract class TaskDao {

    @Query("select * from TaskToDo")
    public abstract LiveData<List<TaskToDo>> getAllTasks();

    @Query("select * from TaskToDo where id=:id")
    public abstract TaskToDo getTask(Long id);

    @Query("select * from TaskToDo where timestamp between :startTimeStamp and  :endTimeStamp")
    public abstract LiveData<List<TaskToDo>> getTasksInRange(Long startTimeStamp, Long endTimeStamp);

    @Insert
    public abstract void insert(TaskToDo taskToDo);

    @Update()
    public abstract void update(TaskToDo... tasksToDo);

}
