package com.dspot.declex.example.todo.ui.tasklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.todo.DatabaseInstance;
import com.dspot.declex.example.todo.api.ItemViewModelList;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class TaskListViewModel extends ViewModel {

    @Observable
    LiveData<List<TaskToDoItemViewModel>> taskToDoItemViewModelList;

    MutableLiveData<Date> dateMutableLiveData;

    @Bean
    DatabaseInstance databaseInstance;

    @pl.com.dspot.archiannotations.annotation.ViewModel
    TaskToDoItemViewModel itemViewModel;

    @AfterInject
    void initializeDependencies() {
        dateMutableLiveData = new MutableLiveData<>();
        taskToDoItemViewModelList = Transformations.switchMap(dateMutableLiveData,
                date -> {
                    if (date == null) {
                        return Transformations.map(
                                databaseInstance.get().taskDao().getAllTasks(),
                                input -> new ItemViewModelList<>(itemViewModel, input));
                    } else {
                        return Transformations.map(
                                databaseInstance.get().taskDao().getTasksInRange(
                                        getAsTimeStampBeginningOf(date),
                                        getAsTimeStampEndOf(date)),
                                input -> new ItemViewModelList<>(itemViewModel, input));
                    }
                });
        dateMutableLiveData.setValue(null);
    }

    public LiveData<List<TaskToDoItemViewModel>> getTaskToDoItemViewModelList() {
        return taskToDoItemViewModelList;
    }

    public void setDate(Date date) {
        dateMutableLiveData.setValue(date);
    }

    protected Long getAsTimeStampBeginningOf(Date day) {
        Calendar calendarDay = getBeginningOf(day);
        return calendarDay.getTimeInMillis();
    }

    protected Long getAsTimeStampEndOf(Date day) {
        Calendar calendarDay = getBeginningOf(day);
        calendarDay.add(Calendar.DAY_OF_YEAR, 1);
        return calendarDay.getTimeInMillis() - 1;
    }

    protected Calendar getBeginningOf(Date day) {
        Calendar calendarDay = Calendar.getInstance();
        calendarDay.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendarDay.setTime(day);
        calendarDay.set(Calendar.HOUR_OF_DAY, 0);
        calendarDay.set(Calendar.MINUTE, 0);
        calendarDay.set(Calendar.SECOND, 0);
        calendarDay.set(Calendar.MILLISECOND, 0);
        return calendarDay;
    }

}
