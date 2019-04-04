package com.dspot.declex.example.todo.ui.tasklist;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskDao;
import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.model.TodoDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import static java.util.Collections.nCopies;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@Config(application = Application.class)
@RunWith(RobolectricTestRunner.class)
public class TaskListFragmentTest {

    @Rule
    public TestRule executeLiveDataInstantly = new InstantTaskExecutorRule();

    private TaskListFragment_ taskListFragment;

    private TaskListFragmentDependencies taskListFragmentDependencies;
    private TaskListViewModelDependencies taskListViewModelDependencies;
    private TaskToDoItemViewModelDependencies taskToDoItemViewModelDependencies;

    @Before
    public void setUp() {
        taskListViewModelDependencies = new TaskListViewModelDependencies();
        taskListFragmentDependencies = new TaskListFragmentDependencies();
        taskToDoItemViewModelDependencies = new TaskToDoItemViewModelDependencies();
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

    @Test
    public void whenGettingListOfTasks_TheyAreDisplayed() {
        startFragment(taskListFragment);
        RecyclerView recyclerView = taskListFragment.getView().findViewById(R.id.taskList);
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 1000);

        assertEquals(taskListViewModelDependencies.list.size(), recyclerView.getChildCount());
    }

    @Test
    public void whenATaskIsClicked_ItsDetailsAreShown() {
        startFragment(taskListFragment);
        RecyclerView recyclerView = taskListFragment.getView().findViewById(R.id.taskList);
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 1000);

        recyclerView.getChildAt(0).performClick();

        verify(taskToDoItemViewModelDependencies.navigation).goToTaskDetails(any(TaskToDo.class));
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

        private List<TaskToDo> list;

        @Mock
        DatabaseInstance databaseInstance;

        @Mock
        TodoDatabase todoDatabase;

        @Mock
        TaskDao taskDao;

        @Mock
        MutableLiveData<List<TaskToDo>> taskListLiveData;

        @Mock
        TaskToDo task;

        public TaskListViewModelDependencies() {
            initMocksAndStubs();
            TaskListViewModel_.DependenciesProvider_.setInstance_(this);
        }

        private List<TaskToDo> initMocksAndStubs() {
            MockitoAnnotations.initMocks(this);

            when(databaseInstance.get()).thenReturn(todoDatabase);
            when(todoDatabase.taskDao()).thenReturn(taskDao);
            when(taskDao.getAllTasks()).thenReturn(taskListLiveData);
            when(task.getTimeStamp()).thenReturn(new Date(System.currentTimeMillis()));

            list = nCopies(5, task);

            doAnswer(invocation -> {

                Observer<List<TaskToDo>> observer = (Observer<List<TaskToDo>>) invocation.getArguments()[0];
                observer.onChanged(list);

                return null;

            }).when(taskListLiveData).observeForever(any());
            return list;
        }

        @Override
        public DatabaseInstance getDatabaseInstance(Context context, Object rootFragment) {
            return databaseInstance;
        }
    }

    private class TaskToDoItemViewModelDependencies extends TaskToDoItemViewModel_.DependenciesProvider_ {

        @Mock
        Navigation navigation;

        public TaskToDoItemViewModelDependencies() {
            MockitoAnnotations.initMocks(this);
            TaskToDoItemViewModel_.DependenciesProvider_.setInstance_(this);
        }

        @Override
        public Navigation getNavigation(Context context, Object rootFragment) {
            return navigation;
        }
    }

}