package com.i_africa.shiftcalenderobajana.screens

import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftActivity

class ScreensNavigator(private val activity: AppCompatActivity) {
    fun navigateToShift(shift: String) {
        ShiftActivity.showShift(activity, shift)
        activity.finish()
    }
}