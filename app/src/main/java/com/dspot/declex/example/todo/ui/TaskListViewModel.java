package com.dspot.declex.example.todo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.EBean;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class TaskListViewModel extends ViewModel {

    @Observable
    MutableLiveData<List<TaskToDo>> taskList;

    public LiveData<List<TaskToDo>> getTaskList() {
        return DatabaseInstance.get().taskDao().getAllTasks();
    }

}
