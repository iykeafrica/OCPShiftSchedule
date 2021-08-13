package com.i_africa.shiftcalenderobajana.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.MyApplication
import com.i_africa.shiftcalenderobajana.common.*
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityModule
import com.i_africa.shiftcalenderobajana.common.di.activity.DaggerActivityComponent
import com.i_africa.shiftcalenderobajana.common.di.presentation.DaggerPresentationComponent
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationComponent
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appComponent))
            .build()
    }

    private val presentationComponent: PresentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()
    }

    val injector get() = presentationComponent

}