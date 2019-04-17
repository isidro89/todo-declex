package com.dspot.declex.example.todo.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.dspot.declex.annotation.RunWith;
import com.dspot.declex.example.todo.Navigation;
import com.dspot.declex.example.todo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static android.view.Gravity.RIGHT;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Bean
    Navigation navigation;

    @ViewById
    DrawerLayout drawerLayout;

    @AfterViews
    public void initializeViews() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

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

    public void openDrawer() {
        drawerLayout.openDrawer(RIGHT);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(RIGHT)) {
            drawerLayout.closeDrawers();
        } else
            super.onBackPressed();
    }
}
