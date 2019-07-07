package com.example.academy.ui.reader

import androidx.lifecycle.ViewModel
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.utils.DataDummy


class CourseReaderViewModel : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun getModules(): MutableList<ModuleEntity> {
        return DataDummy().generateDummyModules(courseId)
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }


    fun getSelectedModule(): ModuleEntity? {
        var module: ModuleEntity? = null

        for (i in 0 until getModules().size) {
            if (getModules()[i].mModuleId.equals(moduleId)) {
                module = getModules()[i]
                module.contentEntity = ContentEntity(
                    "<h3 class=\\\"fr-text-bordered\\\">"
                            + module.mTitle + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
                )
                break
            }
        }
        return module
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }
}