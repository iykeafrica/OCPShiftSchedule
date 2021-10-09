package com.i_africa.shiftcalenderobajana.screens.settings

import android.os.Bundle
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.settings.SettingsViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant.APACHE_LICENSE
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_1
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_10
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_11
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_12
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_13
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_14
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_15
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_16
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_2
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_3
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_4
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_5
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_6
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_7
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_8
import com.i_africa.shiftcalenderobajana.utils.Constant.BACKGROUND_COLOR_9
import com.i_africa.shiftcalenderobajana.utils.Constant.DATE_TEXT_COLOR_RESOURCE
import com.i_africa.shiftcalenderobajana.utils.Constant.DATE_TEXT_COLOR_RESOURCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.DATE_TEXT_COLOR_STRING_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY_COLOR_RESOURCE
import com.i_africa.shiftcalenderobajana.utils.Constant.EMAIL_US
import com.i_africa.shiftcalenderobajana.utils.Constant.MORNING_BACKGROUND_COLOR_RESOURCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.MORNING_BACKGROUND_COLOR_STRING_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.NIGHT_BACKGROUND_COLOR_RESOURCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.NIGHT_BACKGROUND_COLOR_STRING_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.NIGHT_COLOR_RESOURCE
import com.i_africa.shiftcalenderobajana.utils.Constant.OFF_BACKGROUND_COLOR_RESOURCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.OFF_BACKGROUND_COLOR_STRING_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.OFF_COLOR_RESOURCE
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = SettingsActivity::class.simpleName

@AndroidEntryPoint
class SettingsActivity : BaseActivity(), SettingsViewMvc.Listener {

    private lateinit var settingsViewMvc: SettingsViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private var morningBackgroundColorResource = 0
    private var nightBackgroundColorResource = 0
    private var offBackgroundColorResource = 0
    private var dateTextColorResource = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding.license.movementMethod = LinkMovementMethod.getInstance()

        settingsViewMvc = viewMvcFactory.newSettingsViewMvc(null)
        setContentView(settingsViewMvc.rootView)

        setDefaultBackgroundColorResource()
        getLatestBackgroundColorResource()
        settingsViewMvc.onResumeRestoreCellBackgroundColor(morningBackgroundColorResource, nightBackgroundColorResource, offBackgroundColorResource)
        settingsViewMvc.onResumeRestoreDateTextColor(dateTextColorResource)
    }

    private fun setDefaultBackgroundColorResource() {
        if (mySharedPreferences.getStoredInt(MORNING_BACKGROUND_COLOR_RESOURCE_KEY) == 0)
            mySharedPreferences.storeIntValue(MORNING_BACKGROUND_COLOR_RESOURCE_KEY, 1)

        if(mySharedPreferences.getStoredInt(NIGHT_BACKGROUND_COLOR_RESOURCE_KEY) == 0)
            mySharedPreferences.storeIntValue(NIGHT_BACKGROUND_COLOR_RESOURCE_KEY, 2)

        if(mySharedPreferences.getStoredInt(OFF_BACKGROUND_COLOR_RESOURCE_KEY) == 0)
            mySharedPreferences.storeIntValue(OFF_BACKGROUND_COLOR_RESOURCE_KEY, 3)

        if(mySharedPreferences.getStoredInt(DATE_TEXT_COLOR_RESOURCE_KEY) == 0)
            mySharedPreferences.storeIntValue(DATE_TEXT_COLOR_RESOURCE_KEY, 13)
    }

    private fun getLatestBackgroundColorResource() {
        morningBackgroundColorResource = mySharedPreferences.getStoredInt(MORNING_BACKGROUND_COLOR_RESOURCE_KEY)
        nightBackgroundColorResource = mySharedPreferences.getStoredInt(NIGHT_BACKGROUND_COLOR_RESOURCE_KEY)
        offBackgroundColorResource = mySharedPreferences.getStoredInt(OFF_BACKGROUND_COLOR_RESOURCE_KEY)
        dateTextColorResource = mySharedPreferences.getStoredInt(DATE_TEXT_COLOR_RESOURCE_KEY)
    }

    override fun backPressed() {
        screensNavigator.backPressedFromMenuToCustomShiftActivity()
    }

    override fun colorPickerDropDown() {
        settingsViewMvc.colorPickerDropDown()
    }

    override fun changeDayColor() {
        settingsViewMvc.changeDayColor()
    }

    override fun changeNightColor() {
        settingsViewMvc.changeNightColor()
    }

    override fun changeOffColor() {
        settingsViewMvc.changeOffColor()
    }

    override fun changeDefaultColor() {
        settingsViewMvc.restoreDefaultCellBackgroundColor(DAY_COLOR_RESOURCE, NIGHT_COLOR_RESOURCE, OFF_COLOR_RESOURCE)
        saveColor()
    }

    override fun saveColor() {
        settingsViewMvc.saveColor()
        saveInPreference()
    }

    private fun saveInPreference() {
        if (settingsViewMvc.dayCellColor() != 0) {
            mySharedPreferences.storeIntValue(MORNING_BACKGROUND_COLOR_RESOURCE_KEY, settingsViewMvc.dayCellColor())
            mySharedPreferences.storeStringValue(MORNING_BACKGROUND_COLOR_STRING_KEY, sortColorString(settingsViewMvc.dayCellColor()))
        }

        if (settingsViewMvc.nightCellColor() != 0) {
            mySharedPreferences.storeIntValue(NIGHT_BACKGROUND_COLOR_RESOURCE_KEY, settingsViewMvc.nightCellColor())
            mySharedPreferences.storeStringValue(NIGHT_BACKGROUND_COLOR_STRING_KEY, sortColorString(settingsViewMvc.nightCellColor()))
        }

        if (settingsViewMvc.dayCellColor() != 0) {
            mySharedPreferences.storeIntValue(OFF_BACKGROUND_COLOR_RESOURCE_KEY, settingsViewMvc.offCellColor())
            mySharedPreferences.storeStringValue(OFF_BACKGROUND_COLOR_STRING_KEY, sortColorString(settingsViewMvc.offCellColor()))
        }
    }

    private fun saveDateTextInPreference() {
        if (settingsViewMvc.dateTextColor() != 0) {
            mySharedPreferences.storeStringValue(DATE_TEXT_COLOR_STRING_KEY, sortColorString(settingsViewMvc.dateTextColor()))
            mySharedPreferences.storeIntValue(DATE_TEXT_COLOR_RESOURCE_KEY, settingsViewMvc.dateTextColor())
        }
    }

    override fun licenseAgreementClick() {
        screensNavigator.visitUrl(APACHE_LICENSE)
    }

    override fun termsOfUseDropDown() {
        settingsViewMvc.termsOfUseDropDown()
    }

    override fun contactUsClick() {
        screensNavigator.visitUrl(EMAIL_US)
    }

    override fun changeDateTextColor() {
        settingsViewMvc.changeDateText()
    }

    override fun changeDateTextDefaultColor() {
        settingsViewMvc.restoreDefaultDateTextColor(DATE_TEXT_COLOR_RESOURCE)
        saveDateTextColor()
    }

    override fun saveDateTextColor() {
        settingsViewMvc.saveDateTextColor()
        saveDateTextInPreference()
    }

    private fun sortColorString(colorNumber: Int): String {
        var stringColor = ""

        if (colorNumber == 1)
            stringColor = BACKGROUND_COLOR_1
        if (colorNumber == 2)
            stringColor = BACKGROUND_COLOR_2
        if (colorNumber == 3)
            stringColor = BACKGROUND_COLOR_3
        if (colorNumber == 4)
            stringColor = BACKGROUND_COLOR_4
        if (colorNumber == 5)
            stringColor = BACKGROUND_COLOR_5
        if (colorNumber == 6)
            stringColor = BACKGROUND_COLOR_6
        if (colorNumber == 7)
            stringColor = BACKGROUND_COLOR_7
        if (colorNumber == 8)
            stringColor = BACKGROUND_COLOR_8
        if (colorNumber == 9)
            stringColor = BACKGROUND_COLOR_9
        if (colorNumber == 10)
            stringColor = BACKGROUND_COLOR_10
        if (colorNumber == 11)
            stringColor = BACKGROUND_COLOR_11
        if (colorNumber == 12)
            stringColor = BACKGROUND_COLOR_12
        if (colorNumber == 13)
            stringColor = BACKGROUND_COLOR_13
        if (colorNumber == 14)
            stringColor = BACKGROUND_COLOR_14
        if (colorNumber == 15)
            stringColor = BACKGROUND_COLOR_15
        if (colorNumber == 16)
            stringColor = BACKGROUND_COLOR_16

        return stringColor
    }


    override fun onStart() {
        super.onStart()
        settingsViewMvc.registerListener(this)
    }

    override fun onStop() {
        settingsViewMvc.unregisterListener(this)
        super.onStop()
    }
}