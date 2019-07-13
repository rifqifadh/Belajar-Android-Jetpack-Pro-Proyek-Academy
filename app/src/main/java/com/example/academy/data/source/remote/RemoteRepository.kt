package com.example.academy.data.source.remote

import android.os.Handler
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse
import androidx.core.os.HandlerCompat.postDelayed




class RemoteRepository() {

    private lateinit var jsonHelper: JsonHelper
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    private constructor(jsonHelper: JsonHelper): this() {
        this.jsonHelper = jsonHelper
    }

    fun getInstance(helper: JsonHelper): RemoteRepository {
        return RemoteRepository(helper)
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllModulesReceived(jsonHelper.loadModule(courseId))}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
       val handler = Handler()
        handler.postDelayed({ jsonHelper.loadContent(moduleId)?.let { callback.onContentReceived(it) } }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)

        fun onDataNotAvailable()
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)

        fun onDataNotAvailable()
    }

    interface GetContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)

        fun onDataNotAvailable()
    }
}
