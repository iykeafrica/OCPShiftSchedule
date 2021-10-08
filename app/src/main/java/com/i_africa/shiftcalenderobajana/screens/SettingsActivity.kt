package com.i_africa.shiftcalenderobajana.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.settings.SettingsViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_MORNING
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_NIGHT
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_OFF
import com.i_africa.shiftcalenderobajana.utils.Constant.MORNING_BACKGROUND_COLOR_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.NIGHT_BACKGROUND_COLOR_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.OFF_BACKGROUND_COLOR_KEY
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private lateinit var settingsViewMvc: SettingsViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var morningBackgroundColor: String
    private lateinit var nightBackgroundColor: String
    private lateinit var offBackgroundColor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsViewMvc = viewMvcFactory.newSettingsViewMvc(null)
        setContentView(settingsViewMvc.rootView)

        setDefaultBackgroundColor()
        getLatestBackgroundColor()

    }

    private fun setDefaultBackgroundColor() {
        mySharedPreferences.storeStringValue(MORNING_BACKGROUND_COLOR_KEY, BACKGROUND_COLOR_MORNING)
        mySharedPreferences.storeStringValue(NIGHT_BACKGROUND_COLOR_KEY, BACKGROUND_COLOR_NIGHT)
        mySharedPreferences.storeStringValue(OFF_BACKGROUND_COLOR_KEY, BACKGROUND_COLOR_OFF)
    }

    private fun getLatestBackgroundColor() {
        morningBackgroundColor = mySharedPreferences.getStoredString(MORNING_BACKGROUND_COLOR_KEY)
        nightBackgroundColor = mySharedPreferences.getStoredString(NIGHT_BACKGROUND_COLOR_KEY)
        offBackgroundColor = mySharedPreferences.getStoredString(OFF_BACKGROUND_COLOR_KEY)
    }
}