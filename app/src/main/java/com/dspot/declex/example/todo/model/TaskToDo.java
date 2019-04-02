package com.dspot.declex.example.todo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@TypeConverters(Converters.class)
public class TaskToDo {

    @Ignore
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.US);

    @Ignore
    static SimpleDateFormat simpleDateFormatForTime = new SimpleDateFormat("HH:mm", Locale.US);

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    private Date timeStamp;

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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Ignore
    public String getDate() {
        return simpleDateFormat.format(timeStamp);
    }

    @Ignore
    public String getTime() {
        return simpleDateFormatForTime.format(timeStamp);
    }
}
