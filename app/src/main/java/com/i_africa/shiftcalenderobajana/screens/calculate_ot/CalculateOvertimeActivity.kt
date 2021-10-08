package com.i_africa.shiftcalenderobajana.screens.calculate_ot

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant.BASIC
import com.i_africa.shiftcalenderobajana.utils.Constant.OVERTIME
import com.i_africa.shiftcalenderobajana.utils.Constant.WORKED_DAYS
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = CalculateOvertimeActivity::class.simpleName

@AndroidEntryPoint
class CalculateOvertimeActivity : BaseActivity(), CalculateOvertimeViewMvc.Listener {

    private lateinit var calculateOvertimeViewMvc: CalculateOvertimeViewMvc
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: CalculateOvertimeActivity $mySharedPreferences")

        calculateOvertimeViewMvc = viewMvcFactory.newCalculateOvertimeViewMvc(null)
        setContentView(calculateOvertimeViewMvc.rootView)

        if (savedInstanceState != null){
            restoreFromSavedState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(BASIC, calculateOvertimeViewMvc.basic())
        outState.putString(WORKED_DAYS, calculateOvertimeViewMvc.workedDays())
        outState.putString(OVERTIME, calculateOvertimeViewMvc.overtime())
        super.onSaveInstanceState(outState)
    }

    private fun restoreFromPreference() {
        val basic = mySharedPreferences.getStoredString(BASIC)
        val workedDays = mySharedPreferences.getStoredString(WORKED_DAYS)
        val overtime = mySharedPreferences.getStoredString(OVERTIME)
        calculateOvertimeViewMvc.restorePreviousBasicAndWorkedDays(basic, workedDays, overtime)
    }

    private fun restoreFromSavedState(savedInstanceState: Bundle) {
        val basic = savedInstanceState.getString(BASIC)!!
        val workedDays = savedInstanceState.getString(WORKED_DAYS)!!
        val overtime = savedInstanceState.getString(OVERTIME)!!
        calculateOvertimeViewMvc.restorePreviousBasicAndWorkedDays(basic, workedDays, overtime)
    }

    override fun calculateOTClick() {
        calculateOvertimeViewMvc.calculateOT()
        storeBasicAndWorkedDays()
    }

    private fun storeBasicAndWorkedDays() {
        mySharedPreferences.storeStringValue(BASIC, calculateOvertimeViewMvc.basic())
        mySharedPreferences.storeStringValue(WORKED_DAYS, calculateOvertimeViewMvc.workedDays())
        mySharedPreferences.storeStringValue(OVERTIME, calculateOvertimeViewMvc.overtime())
    }

    override fun basicAndWorkDaysEmpty(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun basicEmpty(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun workDaysEmpty(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun basicZero(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun workedDaysZero(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun basicAndWorkedDaysZero(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun basicSalaryClick() {
        calculateOvertimeViewMvc.hideAsterisksNote()
    }

    override fun workedDaysClick() {
        calculateOvertimeViewMvc.hideAsterisksNote()
    }

    override fun onBackPressed() {
        screensNavigator.backPressedFromMenuToCustomShiftActivity()
    }

    override fun onStart() {
        super.onStart()
        calculateOvertimeViewMvc.registerListener(this)
    }

    override fun onStop() {
        calculateOvertimeViewMvc.unregisterListener(this)
        storeBasicAndWorkedDays()
        super.onStop()
    }
}