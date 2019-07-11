package com.example.academy.ui.reader

import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.utils.FakeDataDummies
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private var dummyCourse: CourseEntity =  FakeDataDummies().generateDummyCourses()[0]
    private var courseId: String = dummyCourse.courseId
    private var dummyModules: ArrayList<ModuleEntity> = FakeDataDummies().generateDummyModules(courseId)
    private var academyRepository = mock(AcademyRepository::class.java)
    private var moduleId: String? = dummyModules[0].mModuleId


    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.setCourseId(courseId)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getModules() {
       `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val moduleEntities = viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = dummyModules[0]
        val content: String = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        moduleEntity.contentEntity = ContentEntity(content)
        viewModel.setSelectedModule(moduleId!!)

        `when`(academyRepository.getContent(courseId, moduleId!!)).thenReturn(moduleEntity)
        val entity = viewModel.getSelectedModule()
        verify(academyRepository).getContent(courseId, moduleId!!)
        assertNotNull(entity)

        val contentEntity = entity?.contentEntity
        assertNotNull(contentEntity)

        val resultContent: String = contentEntity?.mContent!!
        assertNotNull(resultContent)
        assertEquals(content, resultContent)
    }
}