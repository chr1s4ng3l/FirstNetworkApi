package com.example.firstnetworkapi.model


import com.google.gson.annotations.SerializedName

data class SchoolsItem(

    @SerializedName("city")
    val city: String? = null,
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("state_code")
    val stateCode: String? = null,
    @SerializedName("subway")
    val subway: String? = null,
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @SerializedName("transfer")
    val transfer: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("zip")
    val zip: String? = null,

// SAT ITEMS
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String? = null,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String? = null,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String? = null,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String? = null

)