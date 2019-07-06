package com.example.academy.ui.bookmark

import androidx.test.rule.ActivityTestRule
import com.example.academy.testing.SingleFragmentActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.academy.R
import com.example.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class BookmarkFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val bookmarkFragment = BookmarkFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadBookmark() {
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}