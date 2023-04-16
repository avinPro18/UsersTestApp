package com.example.userstestapplication.di.modules

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.example.userstestapplication.di.repository_helper.remote.RemoteHelper
import com.example.userstestapplication.di.repository_helper.remote.RemoteRepo
import com.example.userstestapplication.di.repository_helper.remote.RetrofitApi
import com.example.userstestapplication.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if(BuildConfig.DEBUG){
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: GsonConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(gson)
            baseUrl(BASE_URL)
            client(httpClient)
        }
        return retrofit.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): RetrofitApi{
        return retrofit.create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteHelper(
        context: Context,
        retrofitApi: RetrofitApi
    ): RemoteHelper{
        return RemoteRepo(context, retrofitApi)
    }

}