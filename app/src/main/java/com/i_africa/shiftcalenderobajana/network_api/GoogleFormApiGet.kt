package com.i_africa.shiftcalenderobajana.network_api

import com.i_africa.shiftcalenderobajana.model.AppBuildConfig
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

import retrofit2.http.POST

interface GoogleFormApiGet {
    //[FORMAT]https://sheets.googleapis.com/v4/spreadsheets/{Sheet ID}/values/{Range}?key={API Key}
    //[FORM] https://docs.google.com/spreadsheets/d/1YsnE4jw-FNBfsTDAxvJ97trPEjJAAOb-YB7WvV6ZVcw/edit?usp=sharing
    //[BASE_URL_GET] https://sheets.googleapis.com/v4/spreadsheets/
    //[Sheet ID] 1YsnE4jw-FNBfsTDAxvJ97trPEjJAAOb-YB7WvV6ZVcw
    //[Range] Sheet1!A1:B2
    //[API-KEY] AIzaSyDF5FMqQP0pCQAe24ACg_fyrnl_BWoJKGA
    ////https://sheets.googleapis.com/v4/spreadsheets/1YsnE4jw-FNBfsTDAxvJ97trPEjJAAOb-YB7WvV6ZVcw/values/Sheet1!A1:B2?key=AIzaSyDF5FMqQP0pCQAe24ACg_fyrnl_BWoJKGA

    @GET("1YsnE4jw-FNBfsTDAxvJ97trPEjJAAOb-YB7WvV6ZVcw/values/Sheet1!A1:B2?key=AIzaSyDF5FMqQP0pCQAe24ACg_fyrnl_BWoJKGA")
    suspend fun getVersionNameCode() : Response<AppBuildConfig>
}