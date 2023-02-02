package com.example.firstnetworkapi.model

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SchoolProvider @Inject constructor() {

    var schools: List<SchoolsItem> = emptyList()
}