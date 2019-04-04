package com.dspot.declex.example.todo.ui.addtask;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class AddTaskViewModel extends ViewModel {

    @Bean
    DatabaseInstance databaseInstance;

    @Observable
    MutableLiveData<Boolean> successfullySaved;

    public LiveData<Boolean> getSuccessfullySaved() {
        return successfullySaved;
    }

    @Background()
    public void saveTask(TaskToDo task) {
        databaseInstance.get().taskDao().insert(task);
        successfullySaved.postValue(true);
    }
}
