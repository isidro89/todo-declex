package com.dspot.declex.example.todo.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DayListTest {

    private DayList dayList;

    @Before
    public void setUp() {
        dayList = new DayList();

    }

    @Test
    public void callingSize_ReturnsRightAmountOfDays() {
        /*
        There are 24 leap years in a century. Each leap year adds 1 day.
        2000 was a leap year.
        1900 - 1999 there are 36524 days
        2000 - 2099 there are 36525 days.
        Jan 1 20100 - Dec 31 20100 there are 365 days

        the amount of days from Jan 1, 1900 to Dec 31, 2100 is 36524+36525+365 = 73414
        This amount (73414) was verified here https://www.timeanddate.com/date/durationresult.html?d1=01&m1=01&y1=1900&d2=31&m2=12&y2=2100&ti=on

        */
        int expectedAmountOfDaysFromJan1_1900ToDec31_2100 = 73414;
        assertEquals(expectedAmountOfDaysFromJan1_1900ToDec31_2100, dayList.size());
    }

    @Test
    public void getIndexOfJan1_1900_Returns_0() {
        int expectedIndex = 0;
        Date jan1_1900 = new Date(-2208988800000L); // GMT: Monday, January 1, 1900 12:00:00 AM
        assertEquals(expectedIndex, dayList.indexOf(jan1_1900));
    }

    @Test
    public void getIndexOfJan2_1900_Returns_1() {
        int expectedIndex = 1;
        Date jan2_1900 = new Date(-2208899671000L); // GMT: Tuesday, January 2, 1900 12:45:29 AM
        assertEquals(expectedIndex, dayList.indexOf(jan2_1900));
    }

    @Test
    public void getIndexOfApril8_2019_Returns_43561() {
        int expectedIndex = 43561;
        Date April_8th_2019 = new Date(1554749541000L); // GMT: Monday, April 8, 2019 6:52:21 PM
        assertEquals(expectedIndex, dayList.indexOf(April_8th_2019));
    }

    @Test
    public void passingANonDateInstance_Returns_minus1() {
        assertEquals(-1, dayList.indexOf(new Object()));
    }

    @Test
    public void get0_ReturnsJan1_1900() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(-2208988800000L);
        instance.setTimeZone(TimeZone.getTimeZone("GMT"));
        assertEquals(instance.getTimeInMillis(), dayList.get(0).getTime());
    }

    @Test
    public void get1_ReturnsJan2_1900() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(-2208902400000L); // GMT: Tuesday, January 2, 1900 12:45:29 AM
        instance.setTimeZone(TimeZone.getTimeZone("GMT"));
        assertEquals(instance.getTimeInMillis(), dayList.get(1).getTime());
    }

    @Test
    public void get43561_ReturnsApril8th_2019() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1554681600000L); //GMT: Monday, April 8, 2019 12:00:00 AM
        instance.setTimeZone(TimeZone.getTimeZone("GMT"));
        assertEquals(instance.getTimeInMillis(), dayList.get(43561).getTime());
    }
}