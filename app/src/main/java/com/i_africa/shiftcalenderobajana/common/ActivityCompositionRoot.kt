package com.i_africa.shiftcalenderobajana.common

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    private val layoutInflater get() = activity.layoutInflater

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val mySharedPreferences get() = appCompositionRoot.mySharedPreferences

    val popUpMenu get() = MyPopUpMenu(activity)

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
}