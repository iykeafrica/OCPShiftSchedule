package com.i_africa.shiftcalenderobajana.common.di.presentation

import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun screensNavigator(): ScreensNavigator

    fun mySharedPreferences(): MySharedPreferences

    fun popUpMenu(): MyPopUpMenu

    fun viewMvcFactory(): ViewMvcFactory
}