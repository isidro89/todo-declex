package com.dspot.declex.example.todo.ui;

import android.content.Context;

import com.dspot.declex.example.todo.App;
import com.dspot.declex.example.todo.Navigation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Config(application = App.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity_ mainActivity;
    private ActivityController<MainActivity_> activityController;

    @Before
    public void setUp() {
        activityController = Robolectric.buildActivity(MainActivity_.class);
        mainActivity = activityController.get();
        MainActivity_.DependenciesProvider_.setInstance_(new MainActivityDependencies());
        activityController.create();
        activityController.resume();
    }

    @Test
    public void whenActivityStarts_TaskListFragmentIsShown() {
        verify(mainActivity.navigation).goToTaskListFragment();
    }

    private class MainActivityDependencies extends MainActivity_.DependenciesProvider_ {

        private Navigation mockNavigation;

        @Override
        public Navigation getNavigation(Context context, Object rootFragment) {
            if (mockNavigation == null) {
                mockNavigation = mock(Navigation.class);
            }
            return mockNavigation;
        }
    }
}