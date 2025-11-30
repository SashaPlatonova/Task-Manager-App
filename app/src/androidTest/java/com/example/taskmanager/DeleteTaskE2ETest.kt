package com.example.taskmanager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import com.anondo.taskmanager.R
import com.anondo.taskmanager.views.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.isA
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeleteTaskE2ETest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun deleteTask_success() {
        onView(withId(R.id.add_task)).perform(click())
        onView(withId(R.id.etTitle)).perform(typeText("Test task"), closeSoftKeyboard())
        onView(withId(R.id.etDescription)).perform(typeText("Some description"), closeSoftKeyboard())
        onView(withId(R.id.btnSaveTask)).perform(click())

        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Test task")),
                    clickChild(R.id.ivDeleteTask)
                )
            )

        onView(withText("Test task")).check(doesNotExist())
    }

    private fun clickChild(id: Int): ViewAction = object : ViewAction {
        override fun perform(uiController: UiController, view: View) {
            view.findViewById<View>(id)?.performClick()
        }

        override fun getConstraints(): Matcher<View> = isA(View::class.java)
        override fun getDescription(): String? {
            return ""
        }
    }
}