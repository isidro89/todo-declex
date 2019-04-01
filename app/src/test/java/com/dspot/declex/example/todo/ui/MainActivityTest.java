package com.dspot.declex.example.todo.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dspot.declex.example.todo.App;
import com.dspot.declex.example.todo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Config(application = App.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity_ mainActivity;
    private ActivityController<MainActivity_> activityController;

    @Before
    public void setUp() {
        activityController = Robolectric.buildActivity(MainActivity_.class);
        mainActivity = activityController.get();
    }

    @Test
    public void whenActivityStarts_AllViewsAreShown() {
        activityController.create();
        activityController.resume();

        View taskList = mainActivity.findViewById(R.id.taskList);
        View fab = mainActivity.findViewById(R.id.fab);

        assertNotNull("tasks list is null", taskList);
        assertNotNull("fab is null", fab);
        assertThat(taskList, instanceOf(RecyclerView.class));
        assertThat(fab, instanceOf(FloatingActionButton.class));

    }
}