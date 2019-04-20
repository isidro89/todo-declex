package com.dspot.declex.example.todo.ui.tasklist;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dspot.declex.example.todo.api.ItemViewModel;

import org.androidannotations.annotations.EBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class DayItemViewModel extends ItemViewModel<Date> {


    public static final int A_DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    static SimpleDateFormat simpleDateFormatForDayNumber = new SimpleDateFormat("d", Locale.US);

    static SimpleDateFormat simpleDateFormatForDayName = new SimpleDateFormat("E", Locale.US);

    protected Date selectedDay;


    public String getDayNumber() {
        return simpleDateFormatForDayNumber.format(model);
    }

    public String getDayName() {
        return simpleDateFormatForDayName.format(model);
    }

    public void getBackground(ImageView background, TextView day_number, TextView day_name) {
        long timeDiff = selectedDay.getTime() - model.getTime();
        if (selectedDay != null && 0 <= timeDiff && timeDiff < A_DAY_IN_MILLIS) {
            background.setVisibility(View.VISIBLE);
            day_name.setTextColor(Color.WHITE);
            day_number.setTextColor(Color.WHITE);
        } else {
            background.setVisibility(View.INVISIBLE);
            day_name.setTextColor(Color.BLACK);
            day_number.setTextColor(Color.BLACK);
        }
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDay = selectedDate;
    }

}


