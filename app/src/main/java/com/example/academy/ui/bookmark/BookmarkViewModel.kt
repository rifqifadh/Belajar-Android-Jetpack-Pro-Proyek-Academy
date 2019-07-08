package com.example.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.DataDummy

class BookmarkViewModel(private val mAcademyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): MutableList<CourseEntity>? {
       return mAcademyRepository.getBookmarkedCourses()
    }

}