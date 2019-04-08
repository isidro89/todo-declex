package com.dspot.declex.example.todo.ui.tasklist;

import com.dspot.declex.example.todo.api.ItemViewModel;

import org.androidannotations.annotations.EBean;

import java.util.Calendar;
import java.util.Locale;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class DayItemViewModel extends ItemViewModel<Calendar> {



    public String getDayNumber() {
        return String.valueOf(model.get(Calendar.DAY_OF_MONTH));
    }

    public String getDayName() {
        return String.valueOf(model.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
    }

}


