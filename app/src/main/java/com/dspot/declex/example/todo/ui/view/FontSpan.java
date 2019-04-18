package com.dspot.declex.example.todo.ui.view;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * Span that changes the typeface of the text used to the one provided. The style set before will
 * be kept.
 */
/*Taken and translated from https://github.com/googlesamples/android-text/blob/master/TextStyling-Kotlin/app/src/main/java/com/android/example/text/styling/renderer/spans/FontSpan.kt*/
public class FontSpan extends MetricAffectingSpan {


    Typeface font;

    public FontSpan(Typeface font) {
        this.font = font;
    }

    @Override
    public void updateMeasureState(TextPaint textPaint) {
        update(textPaint);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        update(tp);
    }

    private void update(TextPaint textPaint) {

        Typeface old = textPaint.getTypeface();
        int oldStyle = 0;
        if (old != null) {
            oldStyle = old.getStyle();
        }

        // keep the style set before
        Typeface.create(font, oldStyle);
        textPaint.setTypeface(font);

    }
}
