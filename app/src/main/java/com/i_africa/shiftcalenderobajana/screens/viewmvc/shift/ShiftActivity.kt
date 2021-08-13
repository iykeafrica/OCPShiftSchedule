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
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_EXTRA_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import javax.inject.Inject

private val TAG = ShiftActivity::class.simpleName
class ShiftActivity : BaseActivity(), ShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var shiftViewMvc: ShiftViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myPopUpMenu: MyPopUpMenu
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var shift: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
        Log.d(TAG, "onCreate: $screensNavigator")
        shiftViewMvc = viewMvcFactory.newShiftViewMvc(null)
        setContentView(shiftViewMvc.rootView)

        val shift = intent.getStringExtra(SHIFT_EXTRA_KEY)
        Log.d(TAG, "onCreate: $shift")
    }

    override fun onResume() {
        super.onResume()
        shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)
        shiftViewMvc.getShiftDutyInfoOnResume(shift)
        shiftViewMvc.getDayOfMonthOnResume()
        shiftViewMvc.getWeekDay()
    }

    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun calendarClick() {
        shiftViewMvc.getShiftDutyInfoOnCalenderClick(shift)
        shiftViewMvc.getDayOfMonthOnCalendarClick()
        shiftViewMvc.getWeekDay()
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