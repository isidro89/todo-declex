package com.dspot.declex.example.todo.ui.addtask;

import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class AddTaskViewModel extends ViewModel {


    @Background
    public void saveTask(TaskToDo task) {
        DatabaseInstance.get().taskDao().insert(task);
    }
}
