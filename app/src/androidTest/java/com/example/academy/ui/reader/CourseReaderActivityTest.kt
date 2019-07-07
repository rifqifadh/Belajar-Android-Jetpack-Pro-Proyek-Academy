package com.example.academy.ui.reader

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.academy.R
import com.example.academy.utils.FakeDataDummy
import com.example.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class CourseReaderActivityTest {

    val dummyCourse = FakeDataDummy().generateDummyCourses()[0]

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<CourseReaderActivity> =
        object : ActivityTestRule<CourseReaderActivity>(CourseReaderActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, CourseReaderActivity::class.java)
                result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.courseId)
                return result
            }
        }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadCourse() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(7))
    }

    @Test
    fun clickModule() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}