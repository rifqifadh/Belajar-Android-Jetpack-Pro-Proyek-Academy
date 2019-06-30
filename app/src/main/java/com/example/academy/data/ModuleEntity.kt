package com.example.academy.data

data class ModuleEntity(
    private var contentEntity: ContentEntity,
    private val mModuleId: String? = null,
    private val mCourseId: String? = null,
    private val mTitle: String? = null,
    private val mPosition: Int? = null,
    private val mRead: Boolean = false

)