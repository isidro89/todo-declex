package com.dspot.declex.example.todo.ui.taskdetail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.ui.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EFragment(R.layout.fragment_task_detail)
public class TaskDetailFragment extends DialogFragment {

    public static final String ARG_TASK_ID = "arg_task_id";

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.US);

    static SimpleDateFormat simpleDateFormatForTime = new SimpleDateFormat("HH:mm", Locale.US);

    @Bean
    Navigation navigation;

    @FragmentArg(ARG_TASK_ID)
    Long taskId;

    @ViewModel
    FragmentTaskDetailViewModel viewModel;

    @ViewById
    ImageView taskIcon;

    @ViewById
    TextView taskTitle;

    @ViewById
    TextView taskDate;

    @ViewById
    TextView taskTime;

    @ViewById
    TextView taskDescription;


    @AfterViews
    public void setUpViews() {
        taskDescription.setMovementMethod(new ScrollingMovementMethod());
    }

    @AfterViews
    public void loadTask() {
        viewModel.loadTask(taskId);
    }

    @Click
    public void buttonDone() {
        dismiss();
    }

    @Observer
    public void taskDetails(TaskToDo task) {
        updateUI(task);
    }

    private void updateUI(TaskToDo task) {
        taskTitle.setText(task.getTitle());
        taskDate.setText(simpleDateFormat.format(task.getTimeStamp()));
        taskTime.setText(simpleDateFormatForTime.format(task.getTimeStamp()));
        taskDescription.setText(task.getDescription());
        taskIcon.setImageResource(Map.getIcon(task.getCategory()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return null;
    }
}
