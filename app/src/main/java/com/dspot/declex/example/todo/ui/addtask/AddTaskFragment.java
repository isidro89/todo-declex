package com.dspot.declex.example.todo.ui.addtask;


import android.support.v4.app.Fragment;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.ViewModel;

@EFragment(R.layout.fragment_add_task)
public class AddTaskFragment extends Fragment {

    @Bean
    Navigation navigation;
    @ViewModel
    AddTaskViewModel viewModel;

    @Click
    void buttonAddTask(String titleText) {
        viewModel.saveTask(new TaskToDo(titleText));
        navigation.goToTaskListFragment();
    }
}
