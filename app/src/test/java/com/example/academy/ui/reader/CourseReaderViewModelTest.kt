package com.example.academy.ui.reader

import com.example.academy.data.ContentEntity
import com.example.academy.data.ModuleEntity
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var content: ContentEntity
    private var moduleId: String = ""

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel.setCourseId("a14")
        moduleId = "a14m1"
        val title = viewModel.getModules()[0].mTitle
        content = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" +
                title +
                "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getModules() {
        val moduleEntities: MutableList<ModuleEntity> = viewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        viewModel.setSelectedModule(moduleId)
        val moduleEntity = viewModel.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity?.contentEntity
        assertNotNull(contentEntity)
        val content = content.mContent
        assertNotNull(content)
        assertEquals(content, moduleEntity!!.contentEntity!!.mContent)
    }
}