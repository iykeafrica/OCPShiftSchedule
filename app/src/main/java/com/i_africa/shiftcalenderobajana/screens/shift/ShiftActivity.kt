package com.i_africa.shiftcalenderobajana.screens.shift

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.screens.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant.SHIFT_EXTRA_KEY
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftViewMvc

private val TAG = ShiftActivity::class.simpleName

class ShiftActivity : AppCompatActivity(), ShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var shiftViewMvc: ShiftViewMvc
    private lateinit var screensNavigator: ScreensNavigator
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var myPopUpMenu: MyPopUpMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shiftViewMvc = ShiftViewMvc(layoutInflater, null)
        setContentView(shiftViewMvc.rootView)

        mySharedPreferences = MySharedPreferences(application)
        myPopUpMenu = MyPopUpMenu(this)
        screensNavigator = ScreensNavigator(this)

        val shift = intent.getStringExtra(SHIFT_EXTRA_KEY)
        Log.d(TAG, "onCreate: $shift")
    }

    override fun onResume() {
        super.onResume()
        shiftViewMvc.setOnResumeDate()
    }



















    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun calendarClick() {
        shiftViewMvc.setOnCreateDate()
    }

    override fun popUpMenu(v: View) {
        myPopUpMenu.popup(v)
    }

    override fun refresh() {
        screensNavigator.refresh()
    }

    override fun changeShift() {
        mySharedPreferences.clearPreferences()
        screensNavigator.changeShift()
    }

    override fun rateApp() {
        screensNavigator.rateApp()
    }

    override fun about() {
        screensNavigator.about()
    }


    companion object {
        fun showShift(context: Context, shift: String) {
            val intent = Intent(context, ShiftActivity::class.java)
            intent.putExtra(SHIFT_EXTRA_KEY, shift)
            context.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        shiftViewMvc.registerListener(this)
        myPopUpMenu.registerListener(this)
    }

    override fun onStop() {
        shiftViewMvc.unregisterListener(this)
        myPopUpMenu.unregisterListener(this)
        super.onStop()
    }

}