package com.example.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.academy.data.source.local.room.LocalRepository
import com.example.academy.data.source.remote.RemoteRepository
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.utils.FakeDataDummies
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class AcademyRepositoryTest{

    @Rule
    lateinit var instantTaskExecutorRule: InstantTaskExecutorRule
    private val local = mock(LocalRepository::class.java)
    private val remote = mock(RemoteRepository::class.java)
    private val academyRepository = FakeAcademyRepository(local, remote)

    private val courseResponses = FakeDataDummies().generateRemoteDummyCourses()
    private val courseId: String = courseResponses[0].id
    private val moduleResponses = FakeDataDummies().generateRemoteDummyModules(courseId)
    private val moduleId: String? = moduleResponses[0].moduleId
    private val content: ContentResponse = FakeDataDummies().generateRemoteDummyContent(moduleId!!)


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCourses() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities?.size)
    }

    @Test
    fun getAllModulesByCourse() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponses)
        val moduleEntity = academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        assertNotNull(moduleEntity)
        assertEquals(moduleResponses.size, moduleEntity?.size)
    }

    @Test
    fun getBookmarkedCourses() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities?.size)
    }

    @Test
    fun getContent() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponses)
        `when`(remote.getContent(moduleId!!)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule?.contentEntity?.mContent)
    }

    @Test
    fun getCourseWithModules() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponses[0].title, resultCourse?.title)
    }
}