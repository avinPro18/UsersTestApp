package com.example.userstestapplication.ui.home_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userstestapplication.di.repository.DataRepository
import com.example.userstestapplication.model.Result
import com.example.userstestapplication.model.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    var mUsersFlow: Flow<Result<UsersResponse>?>? = null

    init{
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch{
            mUsersFlow = repository.getUsers()
        }
    }

    fun saveAppOpenedCount(){
        repository.apply {
            saveAppOpenedCount()
        }
    }

    fun getAppOpenedCount(): Int{
        return repository.getAppOpenedCount() ?: 0
    }

}