package com.dspot.declex.example.todo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dspot.declex.annotation.RunWith;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Bean
    Navigation navigation;

    @RunWith("onCreate")
    public void initializeFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            navigation.goToTaskListFragment();
        }
        getSupportFragmentManager().addOnBackStackChangedListener(
                () -> {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        // TODO: 4/10/2019 set title in title view that'll be added
//                        toolbar.setTitle(getSupportFragmentManager().getBackStackEntryAt(0).getName());
                    } else {
                        // TODO: 4/10/2019 set title in title view that'll be added
//                        toolbar.setTitle(R.string.app_name);
                    }
                    displayHomeUpIfNeeded();
                });

    }

    public void displayHomeUpIfNeeded() {
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        // TODO: 4/10/2019 show arrow if canGoBack
    }

    // TODO: 4/10/2019 onArrowClick do getSupportFragmentManager().popBackStack()

}
