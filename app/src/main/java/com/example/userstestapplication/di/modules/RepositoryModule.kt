package com.example.userstestapplication.di.modules

import com.example.userstestapplication.di.repository.AppDataManager
import com.example.userstestapplication.di.repository.DataRepository
import com.example.userstestapplication.di.repository_helper.remote.RemoteHelper
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(
        remoteHelper: RemoteHelper,
        sharedPrefsHelper: SharedPrefsHelper
    ): DataRepository{
        return AppDataManager(remoteHelper, sharedPrefsHelper)
    }

}