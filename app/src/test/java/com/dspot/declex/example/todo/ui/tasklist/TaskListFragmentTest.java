package com.dspot.declex.example.todo.ui.tasklist;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskDao;
import com.dspot.declex.example.todo.model.TodoDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@Config(application = Application.class)
@RunWith(RobolectricTestRunner.class)
public class TaskListFragmentTest {

    private TaskListFragment_ taskListFragment;

    private TaskListFragmentDependencies taskListFragmentDependencies;

    @Before
    public void setUp() {
        new TaskListViewModelDependencies();
        taskListFragmentDependencies = new TaskListFragmentDependencies();
        taskListFragment = new TaskListFragment_();
    }

    @Test
    public void whenFragmentStarts_AllViewsAreShown() {
        startFragment(taskListFragment);
        View taskList = taskListFragment.getView().findViewById(R.id.taskList);
        View fab = taskListFragment.getView().findViewById(R.id.fab);

        assertNotNull("tasks list can't be null", taskList);
        assertNotNull("fab can't be null", fab);
        assertThat(taskList, instanceOf(RecyclerView.class));
        assertThat(fab, instanceOf(FloatingActionButton.class));
    }


    @Test
    public void clickingFab_TakesTheUserToAddTaskFragment() {
        startFragment(taskListFragment);
        taskListFragment.getView().findViewById(R.id.fab).performClick();
        verify(taskListFragmentDependencies.navigation).goToAddTaskFragment();
    }

    private class TaskListFragmentDependencies extends TaskListFragment_.DependenciesProvider_ {

        @Mock
        Navigation navigation;

        public TaskListFragmentDependencies() {
            MockitoAnnotations.initMocks(this);
            TaskListFragment_.DependenciesProvider_.setInstance_(this);
        }

        @Override
        public Navigation getNavigation(Context context, Object rootFragment) {
            return navigation;
        }

    }

    private class TaskListViewModelDependencies extends TaskListViewModel_.DependenciesProvider_ {

        @Mock
        DatabaseInstance databaseInstance;

        @Mock
        TodoDatabase todoDatabase;

        @Mock
        TaskDao taskDao;

        public TaskListViewModelDependencies() {
            MockitoAnnotations.initMocks(this);

            when(databaseInstance.get()).thenReturn(todoDatabase);
            when(todoDatabase.taskDao()).thenReturn(taskDao);
            when(taskDao.getAllTasks()).thenReturn(new MutableLiveData<>());

            TaskListViewModel_.DependenciesProvider_.setInstance_(this);
        }

        @Override
        public DatabaseInstance getDatabaseInstance(Context context, Object rootFragment) {
            return databaseInstance;
        }
    }

}