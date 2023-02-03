package com.example.firstnetworkapi.view

import com.example.firstnetworkapi.model.SatItem
import com.example.firstnetworkapi.model.SchoolsItem

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val response: List<SchoolsItem>) : UIState()
    data class ERROR(val error: java.lang.Exception) : UIState()
}

sealed class UIStateSat {
    object LOADING : UIStateSat()
    data class SUCCESS(val response: List<SatItem>) : UIStateSat()
    data class ERROR(val error: java.lang.Exception) : UIStateSat()
}