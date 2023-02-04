package com.example.firstnetworkapi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.firstnetworkapi.model.SatItem
import com.example.firstnetworkapi.service.ServiceApi
import com.example.firstnetworkapi.view.UIState
import com.example.firstnetworkapi.view.UIStateSat
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.firstnetworkapi.domain.GetSat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val getSat: GetSat
) : ViewModel() {

    var dbn = ""
    var email = ""
    var web = ""
    var description = ""

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _satModel = MutableLiveData<SatItem?>()
    val satModel: LiveData<SatItem?> get() = _satModel

    val isLoading = MutableLiveData<Boolean>()

    fun getAllSchools() {
        viewModelScope.launch(ioDispatcher) {
            _schools.postValue(UIState.LOADING)

            try {
                val response = serviceApi.getAllSchools()
                if (response.isSuccessful) {
                    response.body()?.let {
                        // this post value works in main thread and worker thread
                        _schools.postValue(UIState.SUCCESS(it))
                        withContext(Dispatchers.Main) {
                            // this set value only works in the main thread
                            // _schools.value = UIState.SUCCESS(it)
                            Log.d(TAG, "onCreate: $it")
                        }
                    } ?: throw Exception("Error null schools response")
                } else {
                    throw Exception(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                _schools.postValue(UIState.ERROR(e))
                Log.e(TAG, "onCreate: ${e.localizedMessage}", e)
            }
        }
    }



    //Get de sat information by dbn
    fun getAllSat() {
        viewModelScope.launch(ioDispatcher) {
            isLoading.postValue(true)
            val result = getSat()
            val dbnA = dbn
            if (!result.isNullOrEmpty()) {
                for (i in 0 until result.size){
                    if (dbnA.equals(result[i].dbn.toString())){
                        _satModel.postValue(result[i])
                        isLoading.postValue(false)
                    }
                }

            }

        }
    }

}