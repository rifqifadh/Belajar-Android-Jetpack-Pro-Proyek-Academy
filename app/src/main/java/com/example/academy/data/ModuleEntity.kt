package com.example.academy.data

data class ModuleEntity(
    val mModuleId: String? = null,
    val mCourseId: String? = null,
    val mTitle: String? = null,
    val mPosition: Int? = null,
    val mRead: Boolean? = false
) {
     var contentEntity: ContentEntity? = null
}