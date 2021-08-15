package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift

import android.os.Bundle
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.networking.SubmitFCMUseCase
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.CheckNetworkAvailability.isInternetAvailable
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTH
import com.i_africa.shiftcalenderobajana.utils.Constant.IS_FCM_TOKEN_NOT_SUBMITTED
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.YEAR
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import kotlinx.coroutines.*
import javax.inject.Inject


private val TAG = ShiftActivity::class.simpleName
class ShiftActivity : BaseActivity(), ShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var shiftViewMvc: ShiftViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myPopUpMenu: MyPopUpMenu
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var submitFCMUseCase: SubmitFCMUseCase

    private lateinit var shift: String
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
        Log.d(TAG, "onCreate: $screensNavigator")
        shiftViewMvc = viewMvcFactory.newShiftViewMvc(null)
        setContentView(shiftViewMvc.rootView)

        shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)
        val token = mySharedPreferences.getStoredString(FCM_TOKEN)
        val isTokenNotSubmitted = mySharedPreferences.getStoredBoolean(IS_FCM_TOKEN_NOT_SUBMITTED)

        if (savedInstanceState == null) {
            loadShiftSchedule()
        } else {
            restoreState(savedInstanceState)
        }

        postUserFCM(token, isTokenNotSubmitted)
    }

    private fun postUserFCM(token: String, isTokenNotSubmitted: Boolean) {

        if (isInternetAvailable(this)){
            if (isTokenNotSubmitted){
                mySharedPreferences.storeBooleanValue(IS_FCM_TOKEN_NOT_SUBMITTED, false)

                coroutineScope.launch {
                    val result = submitFCMUseCase.submitFCM(token)
                    try {
                        when (result) {
                            is SubmitFCMUseCase.Result.Success -> {
                                Log.d(TAG, "postUserFCM: ${result.responseCode}")
                            }
                            is SubmitFCMUseCase.Result.Failure -> {
                                Log.d(TAG, "postUserFCM: ${result.responseCode}")
                            }
                        }
                    } finally {
                        Log.d(TAG, "postUserFCM: ....done")
                    }
                }
            }
        }
    }

    private fun loadShiftSchedule() {
        shiftViewMvc.showDayOfMonth()
        shiftViewMvc.showWeekDay()
        shiftViewMvc.showShiftDuty(shift)
        shiftViewMvc.showShiftMonthlyWorkingDays(shift)
        shiftViewMvc.showShift(shift)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val dayOfMonth = savedInstanceState.getInt(DAY)
        val month = savedInstanceState.getInt(MONTH)
        val year = savedInstanceState.getInt(YEAR)

        shiftViewMvc.restoreState(dayOfMonth, month, year)
        loadShiftSchedule()
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
        loadShiftSchedule()
    }

    override fun popUpMenuClick(v: View) {
        myPopUpMenu.popup(v)
    }

    override fun refresh() {
        screensNavigator.refresh()
    }

    override fun changeShift() {
        mySharedPreferences.clearSelectedShiftPreferences()
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
        coroutineScope.coroutineContext.cancelChildren()
        super.onStop()
    }
}