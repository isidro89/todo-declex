package com.dspot.declex.example.todo.ui.tasklist;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.api.ItemViewModel;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class TaskToDoItemViewModel extends ItemViewModel<TaskToDo> {

    @Bean
    Navigation navigation;

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.US);

    static SimpleDateFormat simpleDateFormatForTime = new SimpleDateFormat("HH:mm", Locale.US);

    public String getTitle() {
        return model.getTitle();
    }

    public String getDate() {
        return simpleDateFormat.format(model.getTimeStamp());
    }

    public String getTime() {
        return simpleDateFormatForTime.format(model.getTimeStamp());
    }

    public void showDetails() {
        navigation.goToTaskDetails(model);
    }
}
