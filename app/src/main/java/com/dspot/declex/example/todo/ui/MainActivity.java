package com.dspot.declex.example.todo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dspot.declex.annotation.RunWith;
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

    @RunWith("onCreate")
    public void initializeFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            navigation.goToTaskListFragment();
        }
    }

    @AfterViews
    public void setUpViews() {
        setSupportActionBar(toolbar);
    }


}
