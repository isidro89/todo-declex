package com.dspot.declex.example.todo.ui.tasklist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class TaskListViewModelTest {

    private TaskListViewModel taskListViewModel;

    @Before
    public void setUp() {
        taskListViewModel = new TaskListViewModel();

    }


    @Test
    public void getBeginningOfDay_ReturnsCalendarWithRightTimeStamp() {
        Date date = new Date(1554477107000L);    //GMT: Friday, April 5, 2019 3:03:10 PM
        Calendar expectedCalendarInstance = Calendar.getInstance(TimeZone.getTimeZone("GMT")); //to getTimeZone GMT = UTC
        expectedCalendarInstance.setTimeInMillis(1554422400000L); //GMT: Friday, April 5, 2019 12:00:00 AM
        assertEquals(expectedCalendarInstance.getTimeInMillis(), taskListViewModel.getBeginningOf(date).getTimeInMillis());
    }

    @Test
    public void whenDateIsDec31_getEndOfDay_ReturnsRightTimeStamp() {
        Date date = new Date(1577813002000L);    //GMT: Tuesday, December 31, 2019 5:23:22 PM
        long expected = 1577836799999L;          //GMT: Tuesday, December 31, 2019 11:59:59.999
        assertEquals(expected, taskListViewModel.timeStampOfEndOf(date).longValue());
    }

    @Test
    public void getBeginningOfDay_ReturnsCorrectAnswer() {
        Date date = new Date(1554477107000L);    //GMT: Friday, April 5, 2019 3:03:10 PM
        long expectedTimeStamp = 1554422400000L; //GMT: Friday, April 5, 2019 12:00:00 AM
        assertEquals(expectedTimeStamp, taskListViewModel.timeStampOfBeginingOf(date).longValue());
    }

    @Test
    public void getEndOfDay_ReturnsCorrectAnswer() {
        Date date = new Date(1554477107000L);    //GMT: Friday, April 5, 2019 3:03:10 PM
        long expectedTimeStamp = 1554508799999L; //GMT: Friday, April 5, 2019 11:59:59.999 PM
        assertEquals(expectedTimeStamp, taskListViewModel.timeStampOfEndOf(date).longValue());
    }
}