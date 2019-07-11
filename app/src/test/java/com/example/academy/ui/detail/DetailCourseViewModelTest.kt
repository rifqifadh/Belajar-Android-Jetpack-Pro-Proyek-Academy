package com.example.academy.ui.detail

import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.FakeDataDummies
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private var dummyCourse: CourseEntity = FakeDataDummies().generateDummyCourses()[0]
    private var academyRepository = mock(AcademyRepository::class.java)
    private var courseId: String = dummyCourse.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        val courseId = courseEntity?.courseId
        assertNotNull(courseId)
        assertEquals(courseEntity?.courseId, courseId)
    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(FakeDataDummies().generateDummyModules(courseId))
        val moduleEntities = viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)
    }
}