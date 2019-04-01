package com.dspot.declex.example.todo.ui.tasklist;


import android.support.v4.app.Fragment;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.ui.TaskListViewModel;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$Populate;

@EBinder
@EFragment(R.layout.fragment_tasklist)
public class TaskListFragment extends Fragment {

    @ViewModel
    TaskListViewModel viewModel;

    @Bean
    Navigation navigation;

    @Populate()
    List<TaskToDo> taskList;

    @Observer
    void taskList(List<TaskToDo> taskToDos) {
        this.taskList = taskToDos;
        $Populate(taskList);
    }

    static long i = 0;

    @Click(R.id.fab)
    @Background
    void onFabClicked() {
        navigation.goToAddTaskFragment();
    }


}
