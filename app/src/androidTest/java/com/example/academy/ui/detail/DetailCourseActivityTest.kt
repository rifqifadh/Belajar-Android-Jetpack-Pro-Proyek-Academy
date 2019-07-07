package com.example.academy.ui.detail

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.academy.R
import com.example.academy.utils.FakeDataDummy
import com.example.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class DetailCourseActivityTest {

    val dummyCourse = FakeDataDummy().generateDummyCourses()[0]

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<DetailCourseActivity> =
        object : ActivityTestRule<DetailCourseActivity>(DetailCourseActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailCourseActivity::class.java)
                result.putExtra(DetailCourseActivity.EXTRA_COURSE, dummyCourse.courseId)
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
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s", dummyCourse.deadline))))
    }

    @Test
    fun loadModules() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module))
            .check(RecyclerViewItemCountAssertion(7))
    }
}