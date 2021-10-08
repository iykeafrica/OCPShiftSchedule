package com.i_africa.shiftcalenderobajana.model
import com.google.gson.annotations.SerializedName

data class AppBuildConfig(
    @SerializedName("majorDimension")
    val majorDimension: String,

    @SerializedName("range")
    val range: String,

    @SerializedName("values")
    val values: List<List<String>>
)
