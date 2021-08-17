package com.i_africa.shiftcalenderobajana.common.di.app

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApi
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun application() = application

    @Provides
    fun context(): Context = application.applicationContext

    @Provides
    @AppScope
    fun firebaseInstance(): FirebaseMessaging = FirebaseMessaging.getInstance()

    @AppScope
    @Provides
    fun mySharedPreferences(application: Application) = MySharedPreferences(application)

    @Provides
    @AppScope
    fun googleFormApi(retrofit: Retrofit): GoogleFormApi = retrofit.create(GoogleFormApi::class.java)
}