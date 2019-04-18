package com.dspot.declex.example.todo.ui;

import com.dspot.declex.example.todo.R;
import com.dspot.declex.example.todo.model.TaskCategory;

import java.util.HashMap;

public class Map {

    static HashMap<Integer, TaskCategory> iconToTaskCategoryMap;
    static HashMap<TaskCategory, Integer> taskCategoryToIconMap;
    static Integer[] icons =
            {
                    R.drawable.shop,
                    R.drawable.sport,
                    R.drawable.place,
                    R.drawable.event,
                    R.drawable.gym,
                    R.drawable.other
            };

    static TaskCategory[] categories =
            {
                    TaskCategory.SHOP,
                    TaskCategory.SPORT,
                    TaskCategory.PLACE,
                    TaskCategory.EVENT,
                    TaskCategory.GYM,
                    TaskCategory.OTHER
            };

    static {
        iconToTaskCategoryMap = new HashMap<>();
        iconToTaskCategoryMap.put(icons[0], categories[0]);
        iconToTaskCategoryMap.put(icons[1], categories[1]);
        iconToTaskCategoryMap.put(icons[2], categories[2]);
        iconToTaskCategoryMap.put(icons[3], categories[3]);
        iconToTaskCategoryMap.put(icons[4], categories[4]);
        iconToTaskCategoryMap.put(icons[5], categories[5]);

        taskCategoryToIconMap = new HashMap<>();
        taskCategoryToIconMap.put(categories[0], icons[0]);
        taskCategoryToIconMap.put(categories[1], icons[1]);
        taskCategoryToIconMap.put(categories[2], icons[2]);
        taskCategoryToIconMap.put(categories[3], icons[3]);
        taskCategoryToIconMap.put(categories[4], icons[4]);
        taskCategoryToIconMap.put(categories[5], icons[5]);
    }

    public static int getIcon(TaskCategory category) {
        return taskCategoryToIconMap.get(category);
    }

    public static TaskCategory getCategory(Integer icon) {
        return iconToTaskCategoryMap.get(icon);
    }

}
