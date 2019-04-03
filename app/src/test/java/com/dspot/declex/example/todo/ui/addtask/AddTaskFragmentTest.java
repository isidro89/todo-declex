package com.dspot.declex.example.todo.ui.addtask;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskDao;
import com.dspot.declex.example.todo.model.TaskToDo;
import com.dspot.declex.example.todo.model.TodoDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@Config(application = Application.class)
@RunWith(RobolectricTestRunner.class)
public class AddTaskFragmentTest {

    AddTaskFragment_ addTaskFragment;

    AddTaskFragmentDependencies addTaskFragmentDependencies;

    private AddTaskViewModelDependencies addTaskViewModelDependencies;

    @Before
    public void setUp() {
        addTaskFragmentDependencies = new AddTaskFragmentDependencies();
        addTaskViewModelDependencies = new AddTaskViewModelDependencies();
        addTaskFragment = new AddTaskFragment_();
    }

    @Test
    public void whenFragmentStarts_AllViewsAreShown() {
        startFragment(addTaskFragment);
        View taskTitle = addTaskFragment.getView().findViewById(R.id.task_title);
        View taskDescription = addTaskFragment.getView().findViewById(R.id.task_description);
        View date = addTaskFragment.getView().findViewById(R.id.dateView);
        View time = addTaskFragment.getView().findViewById(R.id.timeView);
        View buttonAddTask = addTaskFragment.getView().findViewById(R.id.button_add_task);

        assertNotNull("taskTitle can't be null", taskTitle);
        assertNotNull("taskDescription can't be null", taskDescription);
        assertNotNull("date can't be null", date);
        assertNotNull("time can't be null", time);
        assertNotNull("buttonAddTask can't be null", buttonAddTask);

    }

    @Test
    public void whenClickingButtonAddTask_TaskIsSavedIntoDb() {
        startFragment(addTaskFragment);
        addTaskFragment.getView().findViewById(R.id.button_add_task).performClick();
        verify(addTaskFragment.viewModel.databaseInstance.get().taskDao()).insert(any(TaskToDo.class));
    }

    @Test
    @Config(qualifiers = "en")
    public void whenLanguageIsEnglish_dateViewTextHasExpectedFormat(){
        testDateFormat();
    }

    @Test
    @Config(qualifiers = "es")
    public void whenLanguageIsSpanish_dateViewTextHasExpectedFormat(){
        testDateFormat();
    }

    private void testDateFormat() {
        startFragment(addTaskFragment);

        String[] months = new DateFormatSymbols(Locale.US).getMonths(); // TODO: 4/3/2019 Remove Locale.US if we decide to make the app locale-aware
        String monthsRegex = Arrays.toString(months).replaceAll(", ", "\\|").replaceAll("\\[|\\]", "");
        String regex = String.format("\\d{2} - (%s) - \\d{4}",monthsRegex);

        View date = addTaskFragment.getView().findViewById(R.id.dateView);
        String dateStr = ((TextView) date).getText().toString();

        assertTrue(dateStr.matches(regex));
    }

    private class AddTaskFragmentDependencies extends AddTaskFragment_.DependenciesProvider_ {

        @Mock
        Navigation navigation;

        public AddTaskFragmentDependencies() {
            MockitoAnnotations.initMocks(this);
            AddTaskFragment_.DependenciesProvider_.setInstance_(this);
        }

        @Override
        public Navigation getNavigation(Context context, Object rootFragment) {
            return navigation;
        }

    }

    private class AddTaskViewModelDependencies extends AddTaskViewModel_.DependenciesProvider_ {

        @Mock
        DatabaseInstance databaseInstance;

        @Mock
        TodoDatabase todoDatabase;

        @Mock
        TaskDao taskDao;

        public AddTaskViewModelDependencies() {
            MockitoAnnotations.initMocks(this);

            when(databaseInstance.get()).thenReturn(todoDatabase);
            when(todoDatabase.taskDao()).thenReturn(taskDao);

            AddTaskViewModel_.DependenciesProvider_.setInstance_(this);
        }

        @Override
        public DatabaseInstance getDatabaseInstance(Context context, Object rootFragment) {
            return databaseInstance;
        }
    }
}