package com.udacity.gradle.builditbigger;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class JokeTest {
    public static final String EMPTY_STRING = "";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jokeTest() {

        // "Add code to test that your Async task successfully retrieves a non-empty string."

        // If you tap the TELL JOKE button in MainActivity ...
        onView(withId(R.id.button)).perform(click());

        // ... does it bring up DisplayJokeActivity, and display the retrieved joke?
        onView(withId(R.id.joke_textview)).check(matches(not(withText(EMPTY_STRING))));
    }

}
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withText("Tell Joke"),
//                        childAtPosition(
//                                allOf(withId(R.id.free_fragment),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }

