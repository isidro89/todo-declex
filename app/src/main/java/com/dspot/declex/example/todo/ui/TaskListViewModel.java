package com.dspot.declex.example.todo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.model.Task;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.LinkedList;
import java.util.List;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class TaskListViewModel extends ViewModel {

    @Observable
    MutableLiveData<List<Task>> taskList;


    public MutableLiveData<List<Task>> getTaskList() {
        return taskList;
    }

    @AfterInject
    void initializeDependencies() {
        createMockData();
    }

    private void createMockData() {
        if (taskListMock == null) {
            taskListMock = new LinkedList<>();
            taskListMock.add(new Task(0, "Task #1", "description goes here"));
            taskListMock.add(new Task(1, "Task #2", "description goes here"));
            taskListMock.add(new Task(2, "Task #3", "description goes here"));
        }
        taskList.postValue(taskListMock);
    }

    List<Task> taskListMock;
}
