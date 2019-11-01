package com.gudigudigudi.appdemojetpack.motionlayout;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MyViewMatchers {

    public static Matcher<View> hasDrawable() {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(View item) {
                ImageView imageView = (ImageView) item;
                return imageView.getDrawable() != null;
            }
        };
    }

    public static Matcher<View> withDrawableId(@DrawableRes final int id) {
        return new DrawableMatcher(id);
    }
}
