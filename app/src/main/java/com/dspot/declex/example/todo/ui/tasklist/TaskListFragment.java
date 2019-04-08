package com.dspot.declex.example.todo.ui.tasklist;


import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.api.DayList;
import com.dspot.declex.example.todo.api.ItemViewModelList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.Date;
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

    @Populate
    List<TaskToDoItemViewModel> taskList;

    @Populate
    List<DayItemViewModel> dayListView;

    @ViewById
    ConstraintLayout statusEditionLayout;

    @ViewById(R.id.fab_group)
    Group groupOfFabs;

    @ViewById(R.id.layout_list_of_days)
    ConstraintLayout constraintLayout;

    @ViewById(R.id.dayListView)
    RecyclerView dayListRecyclerView;

    @InstanceState
    boolean isEditing;

    @InstanceState
    boolean showAllTasks = true;

    @ViewById
    FloatingActionButton buttonViewModeToggle;

    @ViewModel
    DayItemViewModel dayItemViewModel;

    DayList dayList;

    @AfterViews
    public void initializeViews() {
        dayList = new DayList();
        setViewMode();
        setEditionMode(isEditing);
        dayListView = new ItemViewModelList<>(dayItemViewModel, dayList);
        $Populate(dayListView);
    }

    @Observer
    void taskToDoItemViewModelList(List<TaskToDoItemViewModel> taskList) {
        this.taskList = taskList;
        $Populate(taskList);
    }

    @Click(R.id.fab)
    void onFabClicked() {
        navigation.goToAddTaskFragment();
    }

    @Click
    void buttonCheckDoneTasks() {
        setEditionMode(true);
    }

    @Click
    public void buttonCloseEditionMode() {
        setEditionMode(false);
    }

    protected void setEditionMode(boolean isEditing) {
        this.isEditing = isEditing;
        TaskToDoItemViewModel.canEditStatus = isEditing;
        statusEditionLayout.setVisibility(isEditing ? View.VISIBLE : View.INVISIBLE);
        groupOfFabs.setVisibility(isEditing ? View.INVISIBLE : View.VISIBLE);
        $Populate(taskList);
    }

    @Click(R.id.card_view_root)
    public void showTaskDetails(TaskToDoItemViewModel model) {
        model.showDetails();
    }

    @Click(R.id.statusCheckBox)
    public void toggleStatus(CheckBox statusCheckBox, TaskToDoItemViewModel model) {
        model.changeStatus(statusCheckBox.isChecked());
    }

    @Click(R.id.button_view_mode_toggle)
    public void toggleViewMode() {
        showAllTasks = !showAllTasks;
        setViewMode();
    }

    protected void setViewMode() {
        if (showAllTasks) {
            constraintLayout.setVisibility(View.GONE);
            viewModel.setDate(null);
            buttonViewModeToggle.setImageResource(R.drawable.ic_date_range_black_24dp);
        } else {
            constraintLayout.setVisibility(View.VISIBLE);
            viewModel.setDate(new Date(System.currentTimeMillis()));
            dayListRecyclerView.scrollToPosition(dayList.indexOf(Calendar.getInstance()));
            buttonViewModeToggle.setImageResource(R.drawable.ic_menu_black_24dp);
        }
    }
}
