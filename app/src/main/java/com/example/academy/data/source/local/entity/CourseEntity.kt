package com.example.academy.data.source.local.entity

data class CourseEntity(
    val courseId: String,
    val title: String,
    val description: String,
    val deadline: String,
    val bookmarked: Boolean? = false,
    val imagePath: String
)
