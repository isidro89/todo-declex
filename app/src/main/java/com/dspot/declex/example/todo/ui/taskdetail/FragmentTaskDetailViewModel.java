package com.dspot.declex.example.todo.ui.taskdetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class FragmentTaskDetailViewModel extends ViewModel {
    @Bean
    DatabaseInstance databaseInstance;

    @Observable
    MutableLiveData<TaskToDo> taskDetails;


    @Background
    public void loadTask(Long taskId) {
        taskDetails.postValue(databaseInstance.get().taskDao().getTask(taskId));
    }

    public MutableLiveData<TaskToDo> getTaskDetails() {
        return taskDetails;
    }
}
