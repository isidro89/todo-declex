package com.dspot.declex.example.todo;

import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.ui.MainActivity;
import com.dspot.declex.example.todo.ui.taskdetail.TaskDetailFragment;
import com.dspot.declex.example.todo.ui.taskdetail.TaskDetailFragment_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import static com.dspot.declex.Action.$TaskListFragment;

@EBean
public class Navigation {

    @RootContext
    protected MainActivity activity;

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
        activity.openDrawer();
    }

    public void goToTaskDetails(TaskToDo task) {
        TaskDetailFragment taskDetailFragment = TaskDetailFragment_.builder().taskId(task.getId()).build();
        taskDetailFragment.show(activity.getSupportFragmentManager(), null);
    }

}
