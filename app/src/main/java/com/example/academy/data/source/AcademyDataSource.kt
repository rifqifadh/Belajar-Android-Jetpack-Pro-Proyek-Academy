package com.example.academy.data.source

import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): MutableList<CourseEntity>?
    fun getCourseWithModules(courseId: String): CourseEntity?
    fun getAllModulesByCourse(courseId: String): MutableList<ModuleEntity>?
    fun getBookmarkedCourses(): MutableList<CourseEntity>?
    fun getContent(courseId: String, moduleId: String): ModuleEntity?
}