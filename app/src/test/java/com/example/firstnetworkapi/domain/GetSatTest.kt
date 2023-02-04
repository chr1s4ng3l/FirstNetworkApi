package com.example.firstnetworkapi.domain

import com.example.firstnetworkapi.service.SchoolRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetSatTest {

    @RelaxedMockK
    private lateinit var satRepository: SchoolRepository

    lateinit var getSat: GetSat

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getSat = GetSat(satRepository)
    }

    @Test
    fun `When invoke the use case get all sat`(): Unit = runBlocking {

        coEvery { satRepository.getAllSat() }
    }
}