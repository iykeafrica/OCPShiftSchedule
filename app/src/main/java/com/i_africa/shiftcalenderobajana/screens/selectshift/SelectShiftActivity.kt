package com.i_africa.shiftcalenderobajana.screens.selectshift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.i_africa.shiftcalenderobajana.screens.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.CMTCE_SHIFT_C
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.PLANT_SHIFT_C
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_A
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_B
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SECURITY_SHIFT_C

class SelectShiftActivity : AppCompatActivity(), SelectShiftViewMvc.Listener {

    private lateinit var selectShiftViewMvc: SelectShiftViewMvc
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectShiftViewMvc = SelectShiftViewMvc(layoutInflater, null)
        setContentView(selectShiftViewMvc.rootView)

        screensNavigator = ScreensNavigator(this)
    }

    override fun clickPlantA() {
        screensNavigator.navigateToShift(PLANT_SHIFT_A)
    }

    override fun clickPlantB() {
        screensNavigator.navigateToShift(PLANT_SHIFT_B)
    }

    override fun clickPlantC() {
        screensNavigator.navigateToShift(PLANT_SHIFT_C)
    }

    override fun clickCmtcA() {
        screensNavigator.navigateToShift(CMTCE_SHIFT_A)
    }

    override fun clickCmtcB() {
        screensNavigator.navigateToShift(CMTCE_SHIFT_B)
    }

    override fun clickCmtcC() {
        screensNavigator.navigateToShift(CMTCE_SHIFT_C)
    }

    override fun clickSecurityA() {
        screensNavigator.navigateToShift(SECURITY_SHIFT_A)
    }

    override fun clickSecurityB() {
        screensNavigator.navigateToShift(SECURITY_SHIFT_B)
    }

    override fun clickSecurityC() {
        screensNavigator.navigateToShift(SECURITY_SHIFT_C)
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