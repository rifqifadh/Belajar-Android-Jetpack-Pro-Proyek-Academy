package com.example.academy.data

data class CourseEntity(
    private val courseId: String? = null,
    private val title: String? = null,
    private val description: String? = null,
    private val deadline: String? = null,
    private val bookmarked: Boolean? = true,
    private val imagePath: String? = null
)
