package com.dspot.declex.example.todo.ui.tasklist;


import android.support.v4.app.Fragment;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;

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
    List<TaskToDoItemViewModel> taskList;

    @Observer
    void taskToDoItemViewModelList(List<TaskToDoItemViewModel> taskList) {
        this.taskList = taskList;
        $Populate(taskList);
    }

    @Click(R.id.fab)
    void onFabClicked() {
        navigation.goToAddTaskFragment();
    }

    @Click(R.id.card_view_root)
    public void showTaskDetails(TaskToDoItemViewModel model) {
        model.showDetails();
    }
}
