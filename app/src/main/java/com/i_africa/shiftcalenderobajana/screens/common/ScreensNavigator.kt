package com.i_africa.shiftcalenderobajana.screens.common

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.screens.about.AboutActivity
import com.i_africa.shiftcalenderobajana.screens.calculate_ot.CalculateOvertimeActivity
import com.i_africa.shiftcalenderobajana.screens.customshift.CustomShiftActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftActivity

private const val TAG = "ScreensNavigator"
class ScreensNavigator(private val activity: AppCompatActivity) {

    var zeroTime = 0L

    fun navigateToShift() {
//        activity.startActivity(Intent(activity, ShiftActivity::class.java))
        activity.startActivity(Intent(activity, CustomShiftActivity::class.java))
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
//        activity.startActivity(Intent(activity, ShiftActivity::class.java))
        activity.startActivity(Intent(activity, CustomShiftActivity::class.java))
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

    fun updateApp(url: String, title: String) {
        var link = ""
        link = if (!url.startsWith("http://") && !url.startsWith("https://"))
            "http://$url"
        else
            url

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(link)
        val chooser = Intent.createChooser(i, title)
        activity.startActivity(chooser)
        Log.d(TAG, "updateApp: $link")
    }

}