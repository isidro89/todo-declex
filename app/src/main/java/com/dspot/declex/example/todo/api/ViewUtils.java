package com.dspot.declex.example.todo.api;

import android.view.View;

public class ViewUtils {

    /*Taken from this Stack Overflow answer: https://stackoverflow.com/a/41984474/9402979
    * */
    public static <ParentClass> ParentClass getFirstParent(View view, Class<ParentClass> parentClass) {
        if (view.getParent() instanceof View) {

            if (parentClass.isInstance(view.getParent())) {
                return (ParentClass) view.getParent();
            } else {
                return getFirstParent((View) view.getParent(), parentClass);
            }

        } else {
            return null;
        }

    }
}
