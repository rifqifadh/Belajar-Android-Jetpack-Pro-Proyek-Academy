package com.example.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    private lateinit var mCourse: CourseEntity
    private var courseId = ""

    fun getCourse(): CourseEntity {

        for (i in 0 until DataDummy().generateDummyCourses().size) {
            val courseEntity = DataDummy().generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                mCourse = courseEntity
            }
        }
        return mCourse
    }

    fun getModules(): MutableList<ModuleEntity> {
        return DataDummy().generateDummyModules(getCourseId())
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }

    private fun getCourseId(): String {
        return courseId
    }
}