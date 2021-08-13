package com.i_africa.shiftcalenderobajana.common.di.presentation

import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityComponent: ActivityComponent) {

    @Provides
    fun layoutInflater() = activityComponent.layoutInflater()

    @Provides
    fun screensNavigator() = activityComponent.screensNavigator()

    @Provides
    fun mySharedPreferences() = activityComponent.mySharedPreferences()

    @Provides
    fun popUpMenu() = activityComponent.popUpMenu()

    @Provides
    fun viewMvcFactory() = activityComponent.viewMvcFactory()
}