package com.example.firstnetworkapi.service

import com.example.firstnetworkapi.model.School
import com.example.firstnetworkapi.model.SchoolProvider
import com.example.firstnetworkapi.model.SchoolsItem
import javax.inject.Inject

class SchoolRepository @Inject constructor(private val api: SchoolService, private val schoolProvider: SchoolProvider) {

    suspend fun getAllSchool(): List<SchoolsItem> {
        val response: List<SchoolsItem> = api.getSchools()
        schoolProvider.schools = response
        return response
    }
}