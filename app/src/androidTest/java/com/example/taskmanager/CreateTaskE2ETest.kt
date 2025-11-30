package com.example.taskmanager

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.anondo.taskmanager.R
import com.anondo.taskmanager.views.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateTaskE2ETest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createNewTask_success() {
        onView(withId(R.id.add_task)).perform(click())

        onView(withId(R.id.etTitle))
            .perform(typeText("Test task"), closeSoftKeyboard())

        onView(withId(R.id.etDescription))
            .perform(typeText("Some description"), closeSoftKeyboard())

        onView(withId(R.id.btnSaveTask)).perform(click())

        onView(withText("Test task")).check(matches(isDisplayed()))
    }
}
