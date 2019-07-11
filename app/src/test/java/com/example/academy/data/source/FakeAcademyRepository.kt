package com.example.academy.data.source

import android.util.Log
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.data.source.local.room.LocalRepository
import com.example.academy.data.source.remote.RemoteRepository

class FakeAcademyRepository() : AcademyDataSource {

    private lateinit var localRepository: LocalRepository
    private lateinit var remoteRepository: RemoteRepository

    constructor(localRepository: LocalRepository, remoteRepository: RemoteRepository) : this() {
        this.localRepository = localRepository
        this.remoteRepository = remoteRepository
    }

    fun getInstance(localRepository: LocalRepository, remoteData: RemoteRepository): FakeAcademyRepository? {
        var instance: FakeAcademyRepository? = null
        if (instance == null) {
            synchronized(FakeAcademyRepository::class.java) {
                if (instance == null) {
                    instance = FakeAcademyRepository(localRepository, remoteData)
                }
            }
        }
        return instance
    }

    override fun getAllCourses(): MutableList<CourseEntity>? {
        val courseList: MutableList<CourseEntity> = mutableListOf()
        val courseResponses = remoteRepository.getAllCourses()
        for (i in 0 until courseResponses.size) {
            val response = courseResponses[i]
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity? {
        var course: CourseEntity? = null
        val courses = remoteRepository.getAllCourses()
        for (i in 0 until courses.size) {
            val response = courses[i]
            if (response.id == courseId) {
                course = CourseEntity(
                    response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath
                )
            }
        }
        return course
    }

    override fun getBookmarkedCourses(): MutableList<CourseEntity>? {
        val courseList: MutableList<CourseEntity> = mutableListOf()
        val courses = remoteRepository.getAllCourses()
        for (i in 0 until courses.size) {
            val response = courses[i]
            val course = CourseEntity(response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getAllModulesByCourse(courseId: String): MutableList<ModuleEntity>? {
        val moduleList: MutableList<ModuleEntity> = mutableListOf()
        val moduleResponses = remoteRepository.getModules(courseId)
        for (i in 0 until moduleResponses.size) {
            val response = moduleResponses[i]
            val course = ModuleEntity(response.moduleId,
                response.courseId,
                response.title,
                response.position)
            moduleList.add(course)
        }
        return moduleList
    }


    override fun getContent(courseId: String, moduleId: String): ModuleEntity? {
        val moduleResponses = remoteRepository.getModules(courseId)
        var module: ModuleEntity? = null
        for (i in 0 until moduleResponses.size) {
            val moduleResponse = moduleResponses[i]
            val id: String? = moduleResponse.moduleId
            if (id.equals(moduleId)) {
                module = ModuleEntity(id,
                    moduleResponse.courseId,
                    moduleResponse.title,
                    moduleResponse.position, false)
                module.contentEntity = ContentEntity(remoteRepository.getContent(moduleId)?.content)
                break
            }
        }
        return module
    }
}