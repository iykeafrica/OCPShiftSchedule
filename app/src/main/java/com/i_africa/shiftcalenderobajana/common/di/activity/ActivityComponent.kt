package com.i_africa.shiftcalenderobajana.common.di.activity

import android.view.LayoutInflater
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun layoutInflater(): LayoutInflater

    fun screensNavigator(): ScreensNavigator

    fun mySharedPreferences(): MySharedPreferences

    fun popUpMenu(): MyPopUpMenu
}