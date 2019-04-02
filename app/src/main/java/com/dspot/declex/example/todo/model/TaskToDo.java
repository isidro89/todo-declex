package com.dspot.declex.example.todo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

@Entity
public class TaskToDo {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
