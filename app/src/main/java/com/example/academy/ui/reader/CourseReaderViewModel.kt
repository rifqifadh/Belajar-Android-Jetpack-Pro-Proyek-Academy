package com.example.academy.ui.reader

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.utils.DataDummy


class CourseReaderViewModel(private val mAcademyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun getModules(): MutableList<ModuleEntity>? {
        return mAcademyRepository.getAllModulesByCourse(courseId)
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }


    fun getSelectedModule(): ModuleEntity? {
        return mAcademyRepository.getContent(courseId, moduleId)
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }
}