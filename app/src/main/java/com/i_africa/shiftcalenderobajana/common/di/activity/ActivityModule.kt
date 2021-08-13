package com.i_africa.shiftcalenderobajana.common.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.common.di.app.AppComponent
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

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun activity() = activity

    @Provides
    fun layoutInflater() = activity.layoutInflater

    @ActivityScope
    @Provides
    fun screensNavigator(activity: AppCompatActivity)  = ScreensNavigator(activity)

    @Provides
    fun mySharedPreferences() = appComponent.mySharedPreferences()

    @Provides
    fun popUpMenu() = MyPopUpMenu(activity)

}