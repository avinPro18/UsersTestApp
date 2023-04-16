package com.example.userstestapplication.di.repository_helper.remote

import com.example.userstestapplication.model.UsersResponse
import com.example.userstestapplication.utils.USERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): Response<UsersResponse>

}