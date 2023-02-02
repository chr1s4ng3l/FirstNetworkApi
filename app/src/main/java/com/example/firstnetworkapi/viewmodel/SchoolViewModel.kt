package com.example.firstnetworkapi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.di.NetworkModule
import com.example.firstnetworkapi.model.School
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.service.SchoolService
import com.example.firstnetworkapi.service.ServiceApi
import com.example.firstnetworkapi.view.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.GetSchool
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val getSchool: GetSchool
) : ViewModel() {

    val schoolModel = MutableLiveData<School?>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAllSchools() {

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSchool()

            if (!result.isNullOrEmpty()) {
                schoolModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }

    }


}