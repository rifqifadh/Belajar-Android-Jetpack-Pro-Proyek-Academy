package com.example.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {

    fun getBookmarks(): MutableList<CourseEntity> = DataDummy().generateDummyCourses()

}