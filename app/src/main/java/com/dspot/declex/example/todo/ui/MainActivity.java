package com.dspot.declex.example.todo.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.Task;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$Populate;

@EBinder
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewModel
    TaskListViewModel viewModel;

    @ViewById
    Toolbar toolbar;

    @Populate()
    List<Task> taskList;

    @AfterViews
    public void setUpViews() {
        setSupportActionBar(toolbar);
    }

    @Observer
    void taskList(List<Task> tasks) {
        this.taskList = tasks;
        $Populate(taskList);
    }


    @Click(R.id.fab)
    void onFabClicked() {
        Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show();
    }

}
