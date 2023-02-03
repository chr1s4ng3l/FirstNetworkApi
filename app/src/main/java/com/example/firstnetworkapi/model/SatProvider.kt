package com.example.firstnetworkapi.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatProvider @Inject constructor(){

    var sat: List<SatItem> = emptyList()

}