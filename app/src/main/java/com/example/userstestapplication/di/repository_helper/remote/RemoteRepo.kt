package com.example.userstestapplication.di.repository_helper.remote

import android.content.Context
import com.example.userstestapplication.model.UsersResponse
import com.example.userstestapplication.utils.toResultFlow
import kotlinx.coroutines.flow.Flow
import com.example.userstestapplication.model.Result
import javax.inject.Inject

class RemoteRepo @Inject constructor(
    private val context: Context,
    private val mRetrofitApi: RetrofitApi
): RemoteHelper {

    override suspend fun getUsers(): Flow<Result<UsersResponse>> {
        return context.toResultFlow {
            mRetrofitApi.getUsers()
        }
    }

}