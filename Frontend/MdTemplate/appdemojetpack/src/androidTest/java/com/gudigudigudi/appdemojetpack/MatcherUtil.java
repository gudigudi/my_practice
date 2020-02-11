package com.gudigudigudi.appdemojetpack;

import android.view.View;

import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * 视图匹配工具类
 */
public class MatcherUtil {
    /**
     * 根据视图id对多个视图进行统一匹配验证
     * @param viewIds 视图id数组
     * @param matcher 匹配器
     */
    public static void checkMultiViews(Matcher<View> matcher,int[] viewIds){
        for (int id : viewIds) {
            onView(withId(id)).check(matches(matcher));
        }
    }
}
