package com.example.userstestapplication.model

sealed class Result<T> {
    class Success<T>(val data: T? = null): Result<T>()
    class Failure<T>(val errorMsg: String = "", val data: T? = null): Result<T>()
    class Loading<T>(val loading: Boolean = false): Result<T>()
}