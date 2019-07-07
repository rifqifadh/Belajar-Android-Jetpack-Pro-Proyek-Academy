package com.example.academy.data.source.remote

import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse

class RemoteRepository() {

    private lateinit var jsonHelper: JsonHelper

    private constructor(jsonHelper: JsonHelper): this() {
        this.jsonHelper = jsonHelper
    }

    fun getInstance(helper: JsonHelper): RemoteRepository {
        return RemoteRepository(helper)
    }

    fun getAllCourses(): MutableList<CourseResponse> {
        return jsonHelper.loadCourses()
    }

    fun getModules(courseId: String): MutableList<ModuleResponse> {
        return jsonHelper.loadModule(courseId)
    }

    fun getContent(moduleId: String): ContentResponse? {
        return jsonHelper.loadContent(moduleId)
    }

}