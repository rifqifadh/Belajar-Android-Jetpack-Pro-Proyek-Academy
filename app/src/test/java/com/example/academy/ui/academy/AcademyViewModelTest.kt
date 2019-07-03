package com.example.academy.ui.academy

import com.example.academy.data.CourseEntity
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getCourse() {
        val courseEntity: MutableList<CourseEntity> = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }
}