package com.dspot.declex.example.todo;

import android.app.Activity;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import static com.dspot.declex.Action.$AddTaskFragment;
import static com.dspot.declex.Action.$TaskListFragment;

@EBean
public class Navigation {

    @RootContext
    protected Activity activity;

    public void goBack() {
        activity.onBackPressed();
    }

    public void finish() {
        activity.finish();
    }

    public void goToTaskListFragment() {
        $TaskListFragment();
    }

    public void goToAddTaskFragment() {
        $AddTaskFragment();
    }
}
