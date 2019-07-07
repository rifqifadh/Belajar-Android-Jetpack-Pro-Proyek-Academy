package com.example.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.DataDummy



class AcademyViewModel: ViewModel() {

    fun getCourse(): MutableList<CourseEntity> {
        return DataDummy().generateDummyCourses()
    }
}