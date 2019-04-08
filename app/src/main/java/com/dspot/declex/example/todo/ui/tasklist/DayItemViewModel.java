package com.dspot.declex.example.todo.ui.tasklist;

import com.dspot.declex.example.todo.api.ItemViewModel;

import org.androidannotations.annotations.EBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class DayItemViewModel extends ItemViewModel<Date> {


    static SimpleDateFormat simpleDateFormatForDayNumber = new SimpleDateFormat("d", Locale.US);

    static SimpleDateFormat simpleDateFormatForDayName = new SimpleDateFormat("E", Locale.US);


    public String getDayNumber() {
        return simpleDateFormatForDayNumber.format(model);
    }

    public String getDayName() {
        return simpleDateFormatForDayName.format(model);
    }

}


