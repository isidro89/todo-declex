package com.dspot.declex.example.todo.ui.tasklist;

import android.support.constraint.Group;
import android.view.View;
import android.widget.CheckBox;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.api.ItemViewModel;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
public class TaskToDoItemViewModel extends ItemViewModel<TaskToDo> {

    public static boolean canEditStatus = false;

    @Bean
    Navigation navigation;

    @Bean
    DatabaseInstance databaseInstance;

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

    public void getDateGroup(Group dateGroup) {
        if (canEditStatus) {
            dateGroup.setVisibility(View.INVISIBLE);
        } else {
            dateGroup.setVisibility(View.VISIBLE);
        }
    }

    public void getStatusCheckBox(CheckBox checkBox) {
        checkBox.setChecked(model.getDone());
        if (canEditStatus) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.INVISIBLE);
        }
    }

    public void changeStatus(boolean isDone) {
        model.setDone(isDone);
        updateDb(this.model);
    }

    @Background(delay = 300, serial = "status_updates")
    /*delay here is to allow the check/uncheck animation run because updating the db triggers
    the live data and list content is replaced*/
    protected void updateDb(TaskToDo model) {
        databaseInstance.get().taskDao().update(model);
    }
}
