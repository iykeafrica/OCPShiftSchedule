package com.i_africa.shiftcalenderobajana.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {

    @Provides
    fun activity() = activity

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater

    @ActivityScope
    @Provides
    fun screensNavigator(activity: AppCompatActivity)  = ScreensNavigator(activity)

    @Provides
    fun popUpMenu(activity: AppCompatActivity) = MyPopUpMenu(activity)

}