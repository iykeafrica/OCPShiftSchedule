package com.i_africa.shiftcalenderobajana

import android.app.Application
import com.i_africa.shiftcalenderobajana.common.AppModule
import com.i_africa.shiftcalenderobajana.common.DaggerAppComponent

class MyApplication: Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    val appModule: AppModule by lazy {
        AppModule(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}