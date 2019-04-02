package com.dspot.declex.example.todo.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @Bean
    Navigation navigation;

    @AfterViews
    public void setUpViews() {
        setSupportActionBar(toolbar);
        if (getSupportFragmentManager().getFragments().size() == 0) // is there a better way to
            // ensure this is done only when starting the activity for the first time and not
            // when there is a configuration change (e.g device is rotated)?
            navigation.goToTaskListFragment();
    }


}
