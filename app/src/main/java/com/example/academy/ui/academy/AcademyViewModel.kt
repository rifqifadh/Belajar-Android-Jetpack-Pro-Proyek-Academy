package com.example.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.DataDummy



class AcademyViewModel(private val mAcademyRepository: AcademyRepository) : ViewModel() {

    fun getCourse(): LiveData<MutableList<CourseEntity>?> {
        return mAcademyRepository.getAllCourses()
    }
}