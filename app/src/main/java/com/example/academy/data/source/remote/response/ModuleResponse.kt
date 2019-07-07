package com.example.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleResponse (
    val moduleId: String? = null,
    val courseId: String? = null,
    val title: String? = null,
    val position: Int? = null
): Parcelable