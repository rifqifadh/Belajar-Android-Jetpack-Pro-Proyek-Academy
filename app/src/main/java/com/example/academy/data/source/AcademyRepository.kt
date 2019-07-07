package com.example.academy.data.source

import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.data.source.local.room.LocalRepository
import com.example.academy.data.source.remote.RemoteRepository
import com.example.academy.data.source.remote.response.CourseResponse

class AcademyRepository() : AcademyDataSource {

    private lateinit var localRepository: LocalRepository
    private lateinit var remoteRepository: RemoteRepository

    private constructor(localRepository: LocalRepository, remoteRepository: RemoteRepository) : this() {
        this.localRepository = localRepository
        this.remoteRepository = remoteRepository
    }

    fun getInstance(localRepository: LocalRepository, remoteData: RemoteRepository): AcademyRepository? {
        var instance: AcademyRepository? = null
        if (instance == null) {
            synchronized(AcademyRepository::class.java) {
                if (instance == null) {
                    instance = AcademyRepository(localRepository, remoteData)
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
            if (response.id.equals(courseId)) {
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
        return null
    }
}