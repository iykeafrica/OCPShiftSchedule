package com.i_africa.shiftcalenderobajana.common

import android.app.Application
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences

class AppCompositionRoot(application: Application) {

    val mySharedPreferences: MySharedPreferences by lazy {
        MySharedPreferences(application)
    }
}