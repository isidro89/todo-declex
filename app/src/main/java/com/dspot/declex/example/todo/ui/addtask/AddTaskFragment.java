package com.dspot.declex.example.todo.ui.addtask;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FocusChange;

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

    @FocusChange
    public void titleFocusChanged(View view, boolean hasFocus){
        if (!hasFocus) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
