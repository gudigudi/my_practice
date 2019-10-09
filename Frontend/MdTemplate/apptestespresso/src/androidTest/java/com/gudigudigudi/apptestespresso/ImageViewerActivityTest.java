package com.gudigudigudi.apptestespresso;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.apptestespresso.matcher.ImageViewHasDrawableMatcher;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class ImageViewerActivityTest {

    @Rule
    public IntentsTestRule<ImageViewerActivity> mIntentsTestRule = new IntentsTestRule<>(ImageViewerActivity.class);

    @Before
    public void stubCameraIntent() {
        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();

        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }

    @Test
    public void takePhoto_drawableIsApplied() {
        onView(ViewMatchers.withId(R.id.imageView)).check(matches(Matchers.not(ImageViewHasDrawableMatcher.hasDrawable())));
    }

    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ImageViewerActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                mIntentsTestRule.getActivity().getResources(), R.drawable.ic_create
        ));

        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }
}