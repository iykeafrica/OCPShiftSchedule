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
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTH
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_EXTRA_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.YEAR
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

        shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)

        if (savedInstanceState == null) {
            loadShiftView()
        } else {
            restoreState(savedInstanceState)
        }
    }

    private fun loadShiftView() {
        shiftViewMvc.getDayOfMonth()
        shiftViewMvc.getWeekDay()
        shiftViewMvc.getShiftDuty(shift)
        shiftViewMvc.getShiftMonthlyWorkingDays(shift)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val dayOfMonth = savedInstanceState.getInt(DAY)
        val month = savedInstanceState.getInt(MONTH)
        val year = savedInstanceState.getInt(YEAR)

        shiftViewMvc.restoreState(dayOfMonth, month, year)
        loadShiftView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(DAY, shiftViewMvc.getDay())
        outState.putInt(MONTH, shiftViewMvc.getMonth())
        outState.putInt(YEAR, shiftViewMvc.getYear())

        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun calendarClick() {
        shiftViewMvc.getDayOfMonth()
        shiftViewMvc.getWeekDay()
        shiftViewMvc.getShiftDuty(shift)
        shiftViewMvc.getShiftMonthlyWorkingDays(shift)
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