package com.dspot.declex.example.todo.ui.tasklist;


import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

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

    @InstanceState
    Date selectedDay;

    @ViewById
    FloatingActionButton buttonViewModeToggle;

    @ViewById
    FloatingActionButton fab;

    @ViewById
    FloatingActionButton buttonCheckDoneTasks;

    @ViewModel
    DayItemViewModel dayItemViewModel;

    DayList dayList;

    @AfterViews
    public void initializeViews() {
        dayList = new DayList();
        initSelectedDay();
        setViewMode();
        setEditionMode(isEditing);
        dayListView = new ItemViewModelList<>(dayItemViewModel, dayList);
        $Populate(dayListView);
        makeFabIconsMatchFabSize();
    }

    protected void makeFabIconsMatchFabSize() {
        //as per https://stackoverflow.com/a/51913347/9316168
        //this has to be done to increase icon size
        fab.setScaleType(ImageView.ScaleType.CENTER);
        buttonCheckDoneTasks.setScaleType(ImageView.ScaleType.CENTER);
        buttonViewModeToggle.setScaleType(ImageView.ScaleType.CENTER);
    }

    protected void initSelectedDay() {
        if (selectedDay == null)
            selectedDay = new Date(System.currentTimeMillis());
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
            buttonViewModeToggle.setImageResource(R.drawable.fab_view_mode_toggle_src);
        } else {
            onlyShowTasksForSelectedDay();
            buttonViewModeToggle.setImageResource(R.drawable.fab_view_mode_list_src);
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.day_item_root_layout)
    public void onDaySelected(DayItemViewModel model) {
        int indexOfCurrentSelectedDay = dayList.indexOf(selectedDay);
        selectedDay = model.model;
        int indexOfNewSelectedDay = dayList.indexOf(selectedDay);
        onlyShowTasksForSelectedDay();
        dayListRecyclerView.getAdapter().notifyItemChanged(indexOfCurrentSelectedDay);
        dayListRecyclerView.getAdapter().notifyItemChanged(indexOfNewSelectedDay);
    }

    protected void onlyShowTasksForSelectedDay() {
        viewModel.setDate(selectedDay);
        dayItemViewModel.setSelectedDate(selectedDay);
        dayListRecyclerView.scrollToPosition(dayList.indexOf(selectedDay));
    }
}
