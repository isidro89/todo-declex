package com.dspot.declex.example.todo.ui.addtask;

import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class AddTaskViewModel extends ViewModel {

    @Bean
    DatabaseInstance databaseInstance;

    @Background
    public void saveTask(TaskToDo task) {
        databaseInstance.get().taskDao().insert(task);
    }
}
