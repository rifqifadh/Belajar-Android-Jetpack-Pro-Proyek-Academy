package com.example.academy.data.source.remote

import android.app.Application
import android.util.Log
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private var application: Application) {

    private fun parsingFileToString(fileName: String): String? {
        try {
            val `is` = application.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            return String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    fun loadCourses(): MutableList<CourseResponse> {

        val list = ArrayList<CourseResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponses.json"))
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id: String = course.getString("id")
                val title: String = course.getString("title")
                val description: String = course.getString("description")
                val date: String = course.getString("date")
                val imagePath: String = course.getString("imagePath")

                val courseResponse = CourseResponse(id, title, description, date, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadModule(courseId: String): MutableList<ModuleResponse> {
        val fileName = String.format("Module_%s.json", courseId)
        val list = ArrayList<ModuleResponse>()

        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("modules")
                for (i in 0 until listArray.length()) {
                    val course = listArray.getJSONObject(i)

                    val moduleId = course.getString("moduleId")
                    val title = course.getString("title")
                    val position = course.getString("position")

                    val courseResponse = ModuleResponse(moduleId, courseId, title, Integer.parseInt(position))
                    list.add(courseResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadContent(moduleId: String): ContentResponse? {
        val fileName = String.format("Content_%s.json", moduleId)
        var contentResponse: ContentResponse? = null

        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val content = responseObject.getString("content")
                contentResponse = ContentResponse(moduleId, content)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return contentResponse
    }
}