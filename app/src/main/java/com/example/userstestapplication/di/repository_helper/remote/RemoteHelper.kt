package com.example.userstestapplication.di.repository_helper.remote

import com.example.userstestapplication.model.UsersResponse
import kotlinx.coroutines.flow.Flow
import com.example.userstestapplication.model.Result

interface RemoteHelper {

    suspend fun getUsers(): Flow<Result<UsersResponse>>

}