package com.i_africa.shiftcalenderobajana.screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftActivity

private const val TAG = "ScreensNavigator"

class ScreensNavigator(private val activity: AppCompatActivity) {

    var zeroTime = 0L

    fun navigateToShift(shift: String) {
        ShiftActivity.showShift(activity, shift)
        activity.finish()
    }

    fun backPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - zeroTime > 2500L) {
            zeroTime = currentTime
            Toast.makeText(activity, "Press back again to exit", Toast.LENGTH_LONG).show()
        } else {
            activity.finish()
        }

    }

    fun refresh() {
        activity.startActivity(Intent(activity, ShiftActivity::class.java))
        activity.finish()
    }

    fun changeShift() {
        activity.startActivity(Intent(activity, SelectShiftActivity::class.java))
        activity.finish()
    }

    fun rateApp() {
        Log.d(TAG, "rateApp: RateApp")
    }

    fun about() {
        Log.d(TAG, "about: About")
    }


}