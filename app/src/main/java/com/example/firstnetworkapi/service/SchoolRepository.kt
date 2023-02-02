package com.example.firstnetworkapi.service

import com.example.firstnetworkapi.model.School
import com.example.firstnetworkapi.model.SchoolProvider
import javax.inject.Inject

class SchoolRepository @Inject constructor(private val api: SchoolService, private val schoolProvider: SchoolProvider) {

    suspend fun getAllSchool(): List<School> {
        val response: List<School> = api.getSchools()
        schoolProvider.schools = response
        return response
    }
}