package com.i_africa.shiftcalenderobajana.common.di.app

import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApi
import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApiGet
import com.i_africa.shiftcalenderobajana.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
    @RetrofitGet
    fun retrofitGet(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.MINUTES)
            .readTimeout(15, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_GET)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @AppScope
    fun firebaseInstance(): FirebaseMessaging = FirebaseMessaging.getInstance()

    @Provides
    @AppScope
    fun googleFormApi(retrofit: Retrofit): GoogleFormApi = retrofit.create(GoogleFormApi::class.java)

    @Provides
    @AppScope
    fun googleFormApiGet(@RetrofitGet retrofit: Retrofit): GoogleFormApiGet = retrofit.create(GoogleFormApiGet::class.java)
}