package com.i_africa.shiftcalenderobajana.network_api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST

interface GoogleFormApi {

    @POST("1FAIpQLScr3o1k4rRD_TuuhUrzmV9jXW_IRA45WL96wxAs557P0x1oNQ/formResponse")
    @FormUrlEncoded
    suspend fun sendFCMToken(@Field("entry.64080122") fcmToken: String?): Response<Void>
}