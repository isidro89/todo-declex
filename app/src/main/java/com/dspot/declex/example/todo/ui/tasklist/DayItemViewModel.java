package com.dspot.declex.example.todo.ui.tasklist;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;

import com.dspot.declex.example.todo.api.ItemViewModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class DayItemViewModel extends ItemViewModel<Date> {


    public static final int A_DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    static SimpleDateFormat simpleDateFormatForDayNumber = new SimpleDateFormat("d", Locale.US);

    static SimpleDateFormat simpleDateFormatForDayName = new SimpleDateFormat("E", Locale.US);

    protected Date selectedDay;

    @AfterInject
    public void initDateFormats() {
        simpleDateFormatForDayName.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormatForDayNumber.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public String getDayNumber() {
        return simpleDateFormatForDayNumber.format(model);
    }

    public String getDayName() {
        return simpleDateFormatForDayName.format(model);
    }

    public void getDayItemRootLayout(ConstraintLayout layout) {
        long timeDiff = selectedDay.getTime() - model.getTime();
        if (selectedDay != null && 0 <= timeDiff && timeDiff < A_DAY_IN_MILLIS)
            layout.setBackgroundColor(Color.YELLOW);
        else
            layout.setBackgroundColor(Color.WHITE);
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDay = selectedDate;
    }

}


