package com.example.taskmanager

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import android.widget.EditText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import com.anondo.taskmanager.R
import com.anondo.taskmanager.views.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateTaskErrorE2ETest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createTask_emptyTitle_showsError() {
        onView(withId(R.id.add_task)).perform(click())
        onView(withId(R.id.btnSaveTask)).perform(click())
        onView(withId(R.id.etTitle)).check(matches(hasErrorText("Enter task title")))
    }
}

fun hasErrorText(expectedError: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(view: View): Boolean {
            return (view is EditText) && expectedError == view.error?.toString()
        }

        override fun describeTo(description: Description) {
            description.appendText("with error text: '$expectedError'")
        }
    }
}