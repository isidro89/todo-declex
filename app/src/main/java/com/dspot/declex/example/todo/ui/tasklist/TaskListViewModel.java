package com.dspot.declex.example.todo.ui.tasklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.api.ItemViewModelList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class TaskListViewModel extends ViewModel {

    @Observable
    LiveData<List<TaskToDoItemViewModel>> taskToDoItemViewModelList;

    @Bean
    DatabaseInstance databaseInstance;

    @pl.com.dspot.archiannotations.annotation.ViewModel
    TaskToDoItemViewModel itemViewModel;

    @AfterInject
    void initializeDependencies() {
        taskToDoItemViewModelList = Transformations.map(databaseInstance.get().taskDao().getAllTasks(), input -> new ItemViewModelList<>(itemViewModel, input));
    }

    public LiveData<List<TaskToDoItemViewModel>> getTaskToDoItemViewModelList() {
        return taskToDoItemViewModelList;
    }
}
