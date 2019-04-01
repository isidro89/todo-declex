package com.dspot.declex.example.todo.ui.addtask;


import android.support.v4.app.Fragment;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_add_task)
public class AddTaskFragment extends Fragment {

    @Bean
    Navigation navigation;

    @Click
    void buttonAddTask() {
        navigation.goToTaskListFragment();
    }
}
