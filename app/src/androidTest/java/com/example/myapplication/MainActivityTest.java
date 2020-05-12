package com.example.myapplication;

import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {



    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.Coffee)).check(matches(withText(R.string.coffee_dates)));
        onView(withId(R.id.Image)).check(matches(isDisplayed()));
        onView(withId(R.id.Name)).check(matches(isDisplayed()));
        onView(withId(R.id.Bio)).check(matches(isDisplayed()));
        onView(withId(R.id.Work)).check(matches(isDisplayed()));
        onView(withId(R.id.Submit)).check(matches(isDisplayed()));


    }

    @Test
    public void hasSecondActivity() {
        onView(withId(R.id.Submit)).perform(ViewActions.scrollTo()).perform(click());
        onView(withId(R.id.Name)).check(matches(hasErrorText("Empty Field")));

        onView(withId(R.id.Name)).perform(click())
                .perform(typeText("Barry"));
        onView(withId(R.id.Age)).perform(click())
                .perform(typeText("42"));
        onView(withId(R.id.Bio)).perform(click())
                .perform(typeText("Barry Bio"));
        onView(withId(R.id.Work)).perform(click())
                .perform(typeText("Barry Work"));

        onView(withId(R.id.Submit)).perform(ViewActions.scrollTo()).perform(click());

        onView(withId(R.id.Bioplate)).check(matches(withText(R.string.about_me)));
        onView(withId(R.id.Jobplate)).check(matches(withText(R.string.my_field)));
        onView(withId(R.id.Submit2)).check(matches(isDisplayed()));
        onView(withId(R.id.java)).check(matches(isDisplayed()));


        onView(withText("Settings")).perform(click());
        onView(withId(R.id.settings)).check(matches(withText(R.string.datingapp_settings)));
        onView(withText("Matches")).perform(click());
        onView(withId(R.id.matches)).check(matches(withText(R.string.datingapp_matches)));
        onView(withText("Profile")).perform(click());

       


    }
}
