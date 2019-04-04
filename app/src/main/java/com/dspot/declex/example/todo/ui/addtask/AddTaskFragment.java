package com.dspot.declex.example.todo.ui.addtask;


import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.widget.TextView;

import com.dspot.declex.annotation.Recollect;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskToDo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$DateDialog;
import static com.dspot.declex.actions.Action.$Recollect;
import static com.dspot.declex.actions.Action.$TimeDialog;
import static com.dspot.declex.api.action.ActionsTools.$inject;

@EBinder
@EFragment(R.layout.fragment_add_task)
public class AddTaskFragment extends Fragment {

    public Calendar calendar = Calendar.getInstance();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MMMM - yyyy", Locale.US); // TODO: 4/3/2019 Remove Locale.US if we decide to make the app locale-aware

    SimpleDateFormat simpleDateFormatForTime = new SimpleDateFormat("hh : mm a", Locale.US); // TODO: 4/3/2019 Remove Locale.US if we decide to make the app locale-aware

    @Bean
    Navigation navigation;

    @ViewModel
    AddTaskViewModel viewModel;

    @Recollect(validate = true)
    TaskToDo task = new TaskToDo();

    @ViewById
    TextView dateView;

    @ViewById
    TextView timeView;

    @AfterViews
    public void init() {
        setDateToDateView();
        setTimeToTimeView();
    }

    protected void setDateToDateView() {
        dateView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    protected void setTimeToTimeView() {
        timeView.setText(simpleDateFormatForTime.format(calendar.getTime()));
    }

    @Click
    void buttonAddTask() {
        $Recollect(task);
        task.setTimeStamp(calendar.getTime());
        task.setDone(false);

        viewModel.saveTask(task);
    }

    @Observer
    public void successfullySaved(Boolean wasSuccessfullySaved) {
        if (wasSuccessfullySaved) {
            navigation.goBack();
        }
    }

    @Touch(R.id.dateView)
    // I used this annotation here instead of @Click because due to the style
    // (@style/Base.Widget.MaterialComponents.TextInputEditText) the 1st time the user clicks this
    // view the dialog doesn't appear
    public void pickDate(MotionEvent motionEvent) {
        if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
            return;

        $DateDialog();

        int year = $inject();
        int month = $inject();
        int day = $inject();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        setDateToDateView();
    }

    @Touch(R.id.timeView) // Why? Same as pickDate()
    public void pickTime(MotionEvent motionEvent) {
        if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
            return;

        $TimeDialog();

        int hour = $inject();
        int minute = $inject();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        setTimeToTimeView();
    }
}
