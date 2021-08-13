package com.i_africa.shiftcalenderobajana.common.di.app

import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun mySharedPreferences(): MySharedPreferences
}