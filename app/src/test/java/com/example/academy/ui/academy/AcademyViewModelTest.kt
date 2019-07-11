package com.example.academy.ui.academy

import com.example.academy.data.source.AcademyRepository
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import com.example.academy.utils.FakeDataDummies
import org.mockito.Mockito.*


class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    private var mAcademyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(mAcademyRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getCourse() {
        `when`(mAcademyRepository.getAllCourses()).thenReturn(FakeDataDummies().generateDummyCourses())
        val courseEntities = viewModel.getCourse()
        verify(mAcademyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}