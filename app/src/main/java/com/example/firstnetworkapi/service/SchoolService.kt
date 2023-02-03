package com.example.firstnetworkapi.service

import com.example.firstnetworkapi.model.SatItem
import com.example.firstnetworkapi.model.SchoolsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SchoolService @Inject constructor(private val api:ServiceApi) {

    suspend fun getSchools(): List<SchoolsItem> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllSchools()
            response.body() ?: emptyList()
        }
    }

    suspend fun getSats(): List<SatItem> {
        return withContext(Dispatchers.IO) {
            val response = api.getSat()
            response.body() ?: emptyList()
        }
    }
}