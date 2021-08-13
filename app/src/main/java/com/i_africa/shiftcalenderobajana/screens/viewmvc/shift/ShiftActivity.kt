package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_EXTRA_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY

private val TAG = ShiftActivity::class.simpleName

class ShiftActivity : BaseActivity(), ShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var shiftViewMvc: ShiftViewMvc
    private lateinit var screensNavigator: ScreensNavigator
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var myPopUpMenu: MyPopUpMenu
    private lateinit var shift: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shiftViewMvc = compositionRoot.viewMvcFactory.newShiftViewMvc(null)
        screensNavigator = compositionRoot.screensNavigator
        mySharedPreferences = compositionRoot.mySharedPreferences
        myPopUpMenu = compositionRoot.popUpMenu

        setContentView(shiftViewMvc.rootView)

        val shift = intent.getStringExtra(SHIFT_EXTRA_KEY)
        mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)

        Log.d(TAG, "onCreate: $shift")
    }

    override fun onResume() {
        super.onResume()
        shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)
        shiftViewMvc.getOnResumeShiftDuty(shift)
        shiftViewMvc.getOnResumeDate()
    }

    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun calendarClick() {
        shiftViewMvc.getOnCalendarClickShiftDuty(shift)
        shiftViewMvc.getOnCalendarClickDate()
    }

    override fun popUpMenuClick(v: View) {
        myPopUpMenu.popup(v)
    }

    override fun refresh() {
        screensNavigator.refresh()
    }

    override fun changeShift() {
        mySharedPreferences.clearPreferences()
        screensNavigator.changeShift()
    }

    override fun calculateOvertime() {
        screensNavigator.calculateOvertime()
    }

    override fun rateApp() {
        screensNavigator.rateApp()
    }

    override fun about() {
        screensNavigator.about()
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

    companion object {
        fun showShift(context: Context, shift: String) {
            val intent = Intent(context, ShiftActivity::class.java)
            intent.putExtra(SHIFT_EXTRA_KEY, shift)
            context.startActivity(intent)
        }
    }

}