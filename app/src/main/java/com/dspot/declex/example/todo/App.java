package com.dspot.declex.example.todo;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseInstance.init(getApplicationContext());
    }
}
