package com.example.academy.ui.bookmark

import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.FakeDataDummies
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getBookmarks() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummies().generateDummyCourses())
        val courseEntity = viewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity?.size)
    }
}