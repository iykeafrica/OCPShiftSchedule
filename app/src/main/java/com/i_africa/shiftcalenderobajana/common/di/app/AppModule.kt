package com.i_africa.shiftcalenderobajana.common.di.app

import android.app.Application
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Module
import dagger.Provides

@Module
class AppModule(application: Application) {

    private val mySharedPreferences: MySharedPreferences by lazy {
        MySharedPreferences(application)
    }

    @Provides
    fun mySharedPreferences() = mySharedPreferences
}