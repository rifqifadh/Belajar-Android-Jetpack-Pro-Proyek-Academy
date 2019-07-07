package com.example.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseResponse(
    private val id: String? = null,
    private val title: String? = null,
    private val description: String? = null,
    private val date: String? = null,
    private val imagePath: String? = null
): Parcelable