package com.example.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleResponse (
    private val moduleId: String? = null,
    private val courseId: String? = null,
    private val title: String? = null,
    private val position: Int? = null
): Parcelable