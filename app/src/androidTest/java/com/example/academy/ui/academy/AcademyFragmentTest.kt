package com.example.academy.ui.academy

import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.academy.R
import com.example.academy.testing.SingleFragmentActivity
import com.example.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule

class AcademyFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val academyFragment = AcademyFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadCourses() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).check(RecyclerViewItemCountAssertion(5))
    }
}