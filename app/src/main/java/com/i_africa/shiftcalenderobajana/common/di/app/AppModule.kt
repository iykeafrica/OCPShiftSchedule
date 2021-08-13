package com.i_africa.shiftcalenderobajana.common.di.app

import android.app.Application
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun mySharedPreferences(application: Application) = MySharedPreferences(application)
}