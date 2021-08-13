package com.i_africa.shiftcalenderobajana

import android.app.Application
import com.i_africa.shiftcalenderobajana.common.AppCompositionRoot

class MyApplication: Application() {

    val appCompositionRoot: AppCompositionRoot by lazy {
        AppCompositionRoot(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}