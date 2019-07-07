package com.example.academy.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import com.example.academy.ui.home.HomeActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.academy.R
import com.example.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.RecursiveAction

class AcademyTest {

    @Rule
    val activityRule: ActivityTestRule<HomeActivity> = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun toDetailActivityTest() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Developer Expert")))
    }

    @Test
    fun toReaderActivityTest() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())
    }
}