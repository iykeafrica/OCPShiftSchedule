package com.i_africa.shiftcalenderobajana.common.di.app

import android.app.Application
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application() : Application
    fun mySharedPreferences(): MySharedPreferences
}