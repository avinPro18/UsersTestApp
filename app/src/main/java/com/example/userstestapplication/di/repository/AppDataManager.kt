package com.example.userstestapplication.di.repository

import com.example.userstestapplication.di.repository_helper.remote.RemoteHelper
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefsHelper
import com.example.userstestapplication.model.Result
import com.example.userstestapplication.model.UsersResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppDataManager @Inject constructor(
    private val mRemoteHelper: RemoteHelper,
    private val mSharedPrefsHelper: SharedPrefsHelper
): DataRepository {

    override suspend fun getUsers(): Flow<Result<UsersResponse>> {
        return mRemoteHelper.getUsers()
    }

    override fun saveAppOpenedCount() {
        mSharedPrefsHelper.saveAppOpenedCount()
    }

    override fun getAppOpenedCount(): Int? {
        return mSharedPrefsHelper.getAppOpenedCount()
    }


}