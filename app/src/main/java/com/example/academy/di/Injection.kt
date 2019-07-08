package com.example.academy.di

import android.app.Application
import com.example.academy.data.source.AcademyRepository
import com.example.academy.data.source.local.room.LocalRepository
import com.example.academy.data.source.remote.JsonHelper
import com.example.academy.data.source.remote.RemoteRepository

class Injection() {

    fun provideRepository(application: Application): AcademyRepository? {

        val localRepository = LocalRepository()
        val remoteRepository = RemoteRepository().getInstance(JsonHelper(application))

        return AcademyRepository().getInstance(localRepository, remoteRepository)
    }
}