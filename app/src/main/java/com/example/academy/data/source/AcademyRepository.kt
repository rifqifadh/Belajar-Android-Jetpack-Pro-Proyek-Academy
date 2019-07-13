package com.example.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.data.source.local.room.LocalRepository
import com.example.academy.data.source.remote.RemoteRepository
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse

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

    override fun getAllCourses(): LiveData<MutableList<CourseEntity>?> {
        val courseResults: MutableLiveData<MutableList<CourseEntity>> = MutableLiveData()
        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList: MutableList<CourseEntity> = mutableListOf()
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
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {
            }

        })
        return courseResults
    }

    override fun getBookmarkedCourses(): LiveData<MutableList<CourseEntity>?> {
        val courseResults: MutableLiveData<MutableList<CourseEntity>> = MutableLiveData()
        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList: MutableList<CourseEntity> = mutableListOf()
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
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {}

        })

        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult: MutableLiveData<CourseEntity> = MutableLiveData()
        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                for (i in 0 until courseResponses.size) {
                    val response = courseResponses[i]
                    if (response.id == courseId) {
                        val course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                        courseResult.postValue(course)
                    }
                }
            }

            override fun onDataNotAvailable() {
            }

        })
        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<MutableList<ModuleEntity>?> {
        val moduleResults: MutableLiveData<MutableList<ModuleEntity>> = MutableLiveData()
        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList: MutableList<ModuleEntity> = mutableListOf()
                for (i in 0 until moduleResponses.size) {
                    val response = moduleResponses[i]
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position)
                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }
            override fun onDataNotAvailable() {
            }

        })

        return moduleResults
    }


    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>? {
        val moduleResult: MutableLiveData<ModuleEntity> = MutableLiveData()
        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                for (i in 0 until moduleResponses.size) {
                    val moduleResponse = moduleResponses[i]
                    val id = moduleResponse.moduleId
                    if (id == moduleId) {
                        val module = ModuleEntity(
                            id,
                            moduleResponse.courseId,
                            moduleResponse.title,
                            moduleResponse.position
                        )

                        remoteRepository.getContent(moduleId, object : RemoteRepository.GetContentCallback {
                            override fun onContentReceived(contentResponse: ContentResponse) {
                                module.contentEntity = ContentEntity(contentResponse.content)
                                moduleResult.postValue(module)
                            }

                            override fun onDataNotAvailable() {
                            }

                        })
                        break
                    }
                }
            }

            override fun onDataNotAvailable() {}
        })
        return moduleResult
    }
}