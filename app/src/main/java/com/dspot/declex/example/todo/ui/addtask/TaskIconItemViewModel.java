package com.dspot.declex.example.todo.ui.addtask;

import android.support.constraint.Group;
import android.widget.ImageView;

import com.dspot.declex.example.todo.api.ItemViewModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.LinkedList;
import java.util.List;

import pl.com.dspot.archiannotations.annotation.EViewModel;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

@EBean
@EViewModel
public class TaskIconItemViewModel extends ItemViewModel<Integer> {

    protected Integer selectedIcon;
    private List<Group> iconBackgrounds;

    @AfterInject
    void initList() {
        iconBackgrounds = new LinkedList<>();
    }

    void selectIcon(Integer iconResId) {
        if (iconBackgrounds.size() > 0) {
            if (this.selectedIcon != null)
                hideBackground(this.selectedIcon);
            showBackground(iconResId);
        }
        this.selectedIcon = iconResId;
    }

    private void showBackground(Integer iconResId) {
        changeVisibility(iconResId, VISIBLE);
    }

    private void hideBackground(Integer selectedIcon) {
        changeVisibility(selectedIcon, INVISIBLE);
    }

    private void changeVisibility(Integer selectedIcon, int visibility) {
        iconBackgrounds.get(getModels().indexOf(selectedIcon)).setVisibility(visibility);
        iconBackgrounds.get(getModels().indexOf(selectedIcon)).requestLayout();
    }

    Integer getSelectedIcon() {
        return selectedIcon;
    }

    void getSelectionIndicators(Group selectionIndicators) {
        this.iconBackgrounds.add(selectionIndicators);
        if (selectedIcon != null && selectedIcon.intValue() == model.intValue())
            selectionIndicators.setVisibility(VISIBLE);
        else
            selectionIndicators.setVisibility(INVISIBLE);
    }

    void getIcon(ImageView icon) {
        icon.setImageResource(model.intValue());
    }

    int getIconResId() {
        return model.intValue();
    }
}
