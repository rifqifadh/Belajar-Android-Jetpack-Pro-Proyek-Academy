package com.example.academy.ui.bookmark

import com.example.academy.data.CourseEntity
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getBookmarks() {
        val courseEntity: MutableList<CourseEntity> = viewModel.getBookmarks()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }
}