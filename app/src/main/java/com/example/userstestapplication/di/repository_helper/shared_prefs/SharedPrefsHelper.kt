package com.example.userstestapplication.di.repository_helper.shared_prefs

interface SharedPrefsHelper {

    fun saveAppOpenedCount()
    fun getAppOpenedCount(): Int?

}