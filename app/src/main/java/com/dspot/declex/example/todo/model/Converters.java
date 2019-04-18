package com.dspot.declex.example.todo.model;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Integer fromTaskCategory(TaskCategory category) {
        if (category == null) {
            return 0;//OTHER
        }
        return category.ordinal();
    }

    @TypeConverter
    public TaskCategory toTaskCategory(Integer ordinal) {
        if (0 < ordinal && ordinal < TaskCategory.values().length)
            return TaskCategory.values()[ordinal];
        else return TaskCategory.OTHER;
    }
}