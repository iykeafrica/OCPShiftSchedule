package com.i_africa.shiftcalenderobajana.common

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    val screensNavigator get() = activityCompositionRoot.screensNavigator

    val mySharedPreferences get() = activityCompositionRoot.mySharedPreferences

    val popUpMenu get() = activityCompositionRoot.popUpMenu

    val viewMvcFactory get() = activityCompositionRoot.viewMvcFactory
}