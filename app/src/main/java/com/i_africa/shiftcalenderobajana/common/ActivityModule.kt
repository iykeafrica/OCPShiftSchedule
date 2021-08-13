package com.i_africa.shiftcalenderobajana.common

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {

    private val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    @Provides
    fun layoutInflater() = activity.layoutInflater

    @Provides
    fun screensNavigator()  = screensNavigator

    @Provides
    fun mySharedPreferences() = appComponent.mySharedPreferences()

    @Provides
    fun popUpMenu() = MyPopUpMenu(activity)

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)
}