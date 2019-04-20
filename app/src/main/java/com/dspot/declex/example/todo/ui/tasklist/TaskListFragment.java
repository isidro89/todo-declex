package com.dspot.declex.example.todo.ui.tasklist;


import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.api.DayList;
import com.dspot.declex.example.todo.api.ItemViewModelList;
import com.dspot.declex.example.todo.ui.view.SimpleAnimationListener;

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

    public static final int ENTRANCE_ANIMATION_DURATION = 300;
    @ViewModel
    TaskListViewModel viewModel;

    @Bean
    Navigation navigation;

    @Populate
    List<TaskToDoItemViewModel> taskList;

    @Populate
    List<TaskToDoItemViewModel> taskListPerDay;

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

    @ViewById(R.id.rect)
    View circularRevealedView;

    @ViewById(R.id.taskList)
    RecyclerView taskListRecyclerView;

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
        dayItemViewModel.setSelectedDate(selectedDay);

        setViewMode();
        setEditionMode(isEditing);
        makeFabIconsMatchFabSize();

        dayListView = new ItemViewModelList<>(dayItemViewModel, dayList);
        $Populate(dayListView);
    }

    protected void makeFabIconsMatchFabSize() {
        /*as per https://stackoverflow.com/a/51913347/9316168
        this has to be done to increase icon size*/
        fab.setScaleType(ImageView.ScaleType.CENTER);
        buttonCheckDoneTasks.setScaleType(ImageView.ScaleType.CENTER);
        buttonViewModeToggle.setScaleType(ImageView.ScaleType.CENTER);
    }

    protected void initSelectedDay() {
        if (selectedDay == null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(System.currentTimeMillis()));
            instance.set(Calendar.HOUR_OF_DAY, 0);
            instance.set(Calendar.MINUTE, 0);
            instance.set(Calendar.SECOND, 0);
            instance.set(Calendar.MILLISECOND, 0);
            instance.add(Calendar.DAY_OF_YEAR, 1);
            selectedDay = new Date(instance.getTime().getTime() - 1);
        }
    }

    @Observer
    void taskListPerDay(List<TaskToDoItemViewModel> taskList) {
        this.taskListPerDay = taskList;
        $Populate(taskListPerDay);
    }

    @Observer
    void allTasks(List<TaskToDoItemViewModel> list) {
        this.taskList = list;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Animator circularReveal = getCircularRevealAnimator();
            circularReveal.start();

            ViewPropertyAnimator fadeAnimation = getFadeAnimation();
            fadeAnimation.start();
        }

        setViewMode();
    }

    protected ViewPropertyAnimator getFadeAnimation() {
        return circularRevealedView.animate()
                .alpha(0)
                .setStartDelay(200)
                .setListener(new SimpleAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        circularRevealedView.setVisibility(View.INVISIBLE);
                        circularRevealedView.setAlpha(1);
                    }

                });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected Animator getCircularRevealAnimator() {
        int cx = (int) (buttonViewModeToggle.getX() + buttonViewModeToggle.getWidth() / 2);
        int cy = (int) (buttonViewModeToggle.getY() + buttonViewModeToggle.getHeight() / 2);

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(circularRevealedView, cx, cy, buttonViewModeToggle.getWidth() / 2, finalRadius);

        circularRevealedView.setVisibility(View.VISIBLE);
        return anim;
    }

    @AfterViews
    public void setUpAnimationListener() {
        constraintLayout.animate().setListener(new SimpleAnimationListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (showAllTasks) {
                    constraintLayout.setVisibility(View.INVISIBLE);
                    constraintLayout.setY(150);
                }
            }
        });
    }

    protected void setViewMode() {
        if (showAllTasks) {
            hideCalendarModeViews();
        } else {
            showCalendarModeViews();
        }
    }

    private void showCalendarModeViews() {
        setSelectedDay();

        constraintLayout.setVisibility(View.VISIBLE);
        constraintLayout.animate().setStartDelay(100).alpha(1).setDuration(ENTRANCE_ANIMATION_DURATION).start();
        constraintLayout.animate().y(0).setDuration(ENTRANCE_ANIMATION_DURATION).start();

        buttonViewModeToggle.setImageResource(R.drawable.fab_view_mode_list_src);
    }

    private void hideCalendarModeViews() {
        constraintLayout.animate().y(-100).start();
        constraintLayout.animate().alpha(0).start();

        buttonViewModeToggle.setImageResource(R.drawable.fab_view_mode_toggle_src);
    }

    @Click(R.id.day_item_root_layout)
    public void onDaySelected(DayItemViewModel model) {
        int indexOfCurrentSelectedDay = dayList.indexOf(selectedDay);
        selectedDay = model.model;
        int indexOfNewSelectedDay = dayList.indexOf(selectedDay);

        setSelectedDay();
        notifyItemsChanged(indexOfCurrentSelectedDay, indexOfNewSelectedDay);
    }

    protected void notifyItemsChanged(int indexOfCurrentSelectedDay, int indexOfNewSelectedDay) {
        dayListRecyclerView.getAdapter().notifyItemChanged(indexOfCurrentSelectedDay);
        dayListRecyclerView.getAdapter().notifyItemChanged(indexOfNewSelectedDay);
    }

    protected void setSelectedDay() {
        viewModel.setDate(selectedDay);
        dayItemViewModel.setSelectedDate(selectedDay);
        dayListRecyclerView.scrollToPosition(dayList.indexOf(selectedDay));
    }
}
