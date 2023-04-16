package com.example.userstestapplication.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefs
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefsHelper
import com.example.userstestapplication.utils.APP_PREFS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    @Singleton
    @Provides
    internal fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    internal fun provideSharedPrefs(preferences: SharedPreferences): SharedPrefs {
        return SharedPrefs(preferences)
    }

    @Singleton
    @Provides
    internal fun provideSharedPrefsHelper(sharedPrefs: SharedPrefs): SharedPrefsHelper {
        return sharedPrefs
    }

}