package com.i_africa.shiftcalenderobajana.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.MyApplication
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityModule
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationComponent
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent: PresentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    val injector get() = presentationComponent

}