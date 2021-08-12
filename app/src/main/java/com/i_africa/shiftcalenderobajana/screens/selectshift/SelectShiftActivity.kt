package com.i_africa.shiftcalenderobajana.screens.selectshift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.i_africa.shiftcalenderobajana.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.screens.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_C
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.FIRST_TIME_LOADING
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_C
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_C
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SHIFT_PREFERENCE_KEY

class SelectShiftActivity : AppCompatActivity(), SelectShiftViewMvc.Listener {

    private lateinit var selectShiftViewMvc: SelectShiftViewMvc
    private lateinit var screensNavigator: ScreensNavigator
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectShiftViewMvc = SelectShiftViewMvc(layoutInflater, null)
        screensNavigator = ScreensNavigator(this)
        mySharedPreferences = MySharedPreferences(application)

        setContentView(selectShiftViewMvc.rootView)
    }

    override fun onResume() {
        super.onResume()
        val isFirstTime = mySharedPreferences.getStoredBoolean(FIRST_TIME_LOADING)

        if (!isFirstTime) {
            val shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)
            navigateToExistingShift(shift)
        }
    }

    override fun clickPlantA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_A)
        screensNavigator.navigateToShift(PLANT_SHIFT_A)
    }

    override fun clickPlantB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_B)
        screensNavigator.navigateToShift(PLANT_SHIFT_B)
    }

    override fun clickPlantC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_C)
        screensNavigator.navigateToShift(PLANT_SHIFT_C)
    }

    override fun clickCmtcA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_A)
        screensNavigator.navigateToShift(CMTCE_SHIFT_A)
    }

    override fun clickCmtcB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_B)
        screensNavigator.navigateToShift(CMTCE_SHIFT_B)
    }

    override fun clickCmtcC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_C)
        screensNavigator.navigateToShift(CMTCE_SHIFT_C)
    }

    override fun clickSecurityA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_A)
        screensNavigator.navigateToShift(SECURITY_SHIFT_A)
    }

    override fun clickSecurityB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_B)
        screensNavigator.navigateToShift(SECURITY_SHIFT_B)
    }

    override fun clickSecurityC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_C)
        screensNavigator.navigateToShift(SECURITY_SHIFT_C)
    }

    private fun navigateToExistingShift(shift: String) {
        if (shift == PLANT_SHIFT_A)
            screensNavigator.navigateToShift(PLANT_SHIFT_A)
        if (shift == PLANT_SHIFT_B)
            screensNavigator.navigateToShift(PLANT_SHIFT_B)
        if (shift == PLANT_SHIFT_C)
            screensNavigator.navigateToShift(PLANT_SHIFT_C)
        if (shift == CMTCE_SHIFT_A)
            screensNavigator.navigateToShift(CMTCE_SHIFT_A)
        if (shift == CMTCE_SHIFT_B)
            screensNavigator.navigateToShift(CMTCE_SHIFT_B)
        if (shift == CMTCE_SHIFT_C)
            screensNavigator.navigateToShift(CMTCE_SHIFT_C)
        if (shift == SECURITY_SHIFT_A)
            screensNavigator.navigateToShift(SECURITY_SHIFT_A)
        if (shift == SECURITY_SHIFT_B)
            screensNavigator.navigateToShift(SECURITY_SHIFT_B)
        if (shift == SECURITY_SHIFT_C)
            screensNavigator.navigateToShift(SECURITY_SHIFT_C)
    }

    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun onStart() {
        super.onStart()
        selectShiftViewMvc.registerListener(this)
    }

    override fun onStop() {
        selectShiftViewMvc.unregisterListener(this)
        super.onStop()
    }

}