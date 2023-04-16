package com.example.userstestapplication.di.repository

import com.example.userstestapplication.di.repository_helper.remote.RemoteHelper
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefsHelper

interface DataRepository: RemoteHelper, SharedPrefsHelper