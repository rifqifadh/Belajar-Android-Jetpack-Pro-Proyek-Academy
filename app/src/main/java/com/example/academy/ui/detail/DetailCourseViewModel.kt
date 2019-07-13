package com.example.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.utils.DataDummy

class DetailCourseViewModel(private val mAcademyRepository: AcademyRepository) : ViewModel() {

    private lateinit var mCourse: CourseEntity
    private var courseId = ""

    fun getCourse(): LiveData<CourseEntity> {
        return mAcademyRepository.getCourseWithModules(courseId)
    }

    fun getModules(): LiveData<MutableList<ModuleEntity>?> {
        return mAcademyRepository.getAllModulesByCourse(courseId)
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }

    private fun getCourseId(): String {
        return courseId
    }
}