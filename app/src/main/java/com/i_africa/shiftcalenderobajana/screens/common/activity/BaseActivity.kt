package com.i_africa.shiftcalenderobajana.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.MyApplication
import com.i_africa.shiftcalenderobajana.common.ActivityCompositionRoot
import com.i_africa.shiftcalenderobajana.common.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    private val activityCompositionRoot get() = ActivityCompositionRoot(this, appCompositionRoot)

    val compositionRoot get() = PresentationCompositionRoot(activityCompositionRoot)

}