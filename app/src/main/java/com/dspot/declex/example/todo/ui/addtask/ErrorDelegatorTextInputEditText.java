package com.dspot.declex.example.todo.ui.addtask;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

import com.dspot.declex.example.todo.api.ViewUtils;

public class ErrorDelegatorTextInputEditText extends TextInputEditText {

    public ErrorDelegatorTextInputEditText(Context context) {
        super(context);
    }

    public ErrorDelegatorTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorDelegatorTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setError(CharSequence error) {
        TextInputLayout textInputLayout = getParentTextInputLayout();

        boolean isInsideATextInputLayout = textInputLayout != null;
        if (isInsideATextInputLayout) {
            textInputLayout.setError(error);
        } else
            super.setError(error);
    }

    private TextInputLayout getParentTextInputLayout() {
        /*Since the "actual view hierarchy present under TextInputLayout is NOT guaranteed to match
         the view hierarchy as written in XML" (https://developer.android.com/reference/android/support/design/widget/TextInputLayout.html)
         we need to go up the view tree until we find the TextInputInputLayout
         */
        return ViewUtils.getFirstParent(this, TextInputLayout.class);
    }
}
