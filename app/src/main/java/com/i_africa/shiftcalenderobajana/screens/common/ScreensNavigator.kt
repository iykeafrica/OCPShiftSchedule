package com.i_africa.shiftcalenderobajana.screens.common

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.about.AboutActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.calculate_ot.CalculateOvertimeActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.ShiftActivity

private const val TAG = "ScreensNavigator"

class ScreensNavigator(private val activity: AppCompatActivity) {

    var zeroTime = 0L

    fun navigateToShift() {
        activity.startActivity(Intent(activity, ShiftActivity::class.java))
        activity.finish()
    }

    fun backPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - zeroTime > 2500L) {
            zeroTime = currentTime
            Toast.makeText(activity, "Press back again to exit", Toast.LENGTH_SHORT).show()
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

    fun calculateOvertime() {
        activity.startActivity(Intent(activity, CalculateOvertimeActivity::class.java))
    }

    fun about() {
        activity.startActivity(Intent(activity, AboutActivity::class.java))
    }


}