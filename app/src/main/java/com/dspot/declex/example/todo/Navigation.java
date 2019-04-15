package com.dspot.declex.example.todo;

import android.support.v7.app.AppCompatActivity;

import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.ui.taskdetail.TaskDetailFragment;
import com.dspot.declex.example.todo.ui.taskdetail.TaskDetailFragment_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import static com.dspot.declex.Action.$AddTaskFragment;
import static com.dspot.declex.Action.$TaskListFragment;

@EBean
public class Navigation {

    @RootContext
    protected AppCompatActivity activity;

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
        $AddTaskFragment().transaction().addToBackStack("Add a task").setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);

    }

    public void goToTaskDetails(TaskToDo task) {
        TaskDetailFragment taskDetailFragment = TaskDetailFragment_.builder().taskId(task.getId()).build();
        taskDetailFragment.show(activity.getSupportFragmentManager(), null);
    }

}
