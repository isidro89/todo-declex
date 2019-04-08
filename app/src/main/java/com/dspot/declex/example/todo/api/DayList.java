package com.dspot.declex.example.todo.api;

import java.util.AbstractList;
import java.util.Calendar;

public final class DayList extends AbstractList<Calendar> {

    public static final long MILLIS_IN_A_DAY = 86400000L;
    /*I am using Jan 1st, 1900 because it is the beginning date in Android date picker.*/
    public static final long JAN_1st_1900_IN_MILLIS = -2208988800000L;
    /*I am using Jan 1st, 21001 because the last date in Android date picker is Dec 31, 2100.*/
    public static final long JAN_1st_2101_IN_MILLIS = 4133980800000L;


    @Override
    public Calendar get(int index) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(JAN_1st_1900_IN_MILLIS + index * MILLIS_IN_A_DAY);
        return instance;
    }

    @Override
    public int size() {
        return (int) ((JAN_1st_2101_IN_MILLIS - JAN_1st_1900_IN_MILLIS) / MILLIS_IN_A_DAY);
    }

    @Override
    public int indexOf(Object o) {
        if (o instanceof Calendar) {
            Calendar date = (Calendar) o;
            long amountOfDaysSinceJan1st1900 = (date.getTimeInMillis() - JAN_1st_1900_IN_MILLIS) / MILLIS_IN_A_DAY;
            return (int) amountOfDaysSinceJan1st1900;
        }
        return -1;
    }
}
