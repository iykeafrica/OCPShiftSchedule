package com.i_africa.shiftcalenderobajana.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.MyApplication
import com.i_africa.shiftcalenderobajana.common.*

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    private val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appComponent))
            .build()
    }

    private val presentationComponent: PresentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()
    }

    val injector get() = Injector(presentationComponent)

}