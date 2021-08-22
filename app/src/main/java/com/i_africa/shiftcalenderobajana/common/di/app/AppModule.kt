package com.i_africa.shiftcalenderobajana.common.di.app

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApi
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule() {

    @Provides
    @AppScope
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun firebaseInstance(): FirebaseMessaging = FirebaseMessaging.getInstance()

    @Provides
    @AppScope
    fun googleFormApi(retrofit: Retrofit): GoogleFormApi = retrofit.create(GoogleFormApi::class.java)
}