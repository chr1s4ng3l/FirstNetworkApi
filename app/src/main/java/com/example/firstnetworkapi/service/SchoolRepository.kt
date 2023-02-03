package com.example.firstnetworkapi.service

import com.example.firstnetworkapi.model.*
import javax.inject.Inject

class SchoolRepository @Inject constructor(
    private val api: SchoolService,
    private val schoolProvider: SchoolProvider,
    private val satProvider: SatProvider
) {

    suspend fun getAllSchool(): List<SchoolsItem> {
        val response: List<SchoolsItem> = api.getSchools()
        schoolProvider.schools = response
        return response
    }

    suspend fun getAllSat(): List<SatItem> {
        val response: List<SatItem> = api.getSats()
        satProvider.sat = response
        return response
    }
}