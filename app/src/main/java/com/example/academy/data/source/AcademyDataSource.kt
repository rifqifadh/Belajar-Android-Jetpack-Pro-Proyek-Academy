package com.example.academy.data.source

import androidx.lifecycle.LiveData
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): LiveData<MutableList<CourseEntity>?>
    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>?
    fun getAllModulesByCourse(courseId: String): LiveData<MutableList<ModuleEntity>?>
    fun getBookmarkedCourses(): LiveData<MutableList<CourseEntity>?>
    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>?
}