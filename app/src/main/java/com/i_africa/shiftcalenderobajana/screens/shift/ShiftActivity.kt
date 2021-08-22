package com.i_africa.shiftcalenderobajana.screens.shift

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.networking.SubmitFormUseCase
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.shift.utils.CheckNetworkAvailability.isInternetAvailable
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_BODY_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTH
import com.i_africa.shiftcalenderobajana.utils.Constant.NEW_FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.YEAR
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


private val TAG = ShiftActivity::class.simpleName

@AndroidEntryPoint
class ShiftActivity : BaseActivity(), ShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var shiftViewMvc: ShiftViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myPopUpMenu: MyPopUpMenu
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var submitFormUseCase: SubmitFormUseCase

    private lateinit var shift: String
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: ShiftActivity $screensNavigator")
        Log.d(TAG, "onCreate: ShiftActivity $mySharedPreferences")

        shiftViewMvc = viewMvcFactory.newShiftViewMvc(null)
        setContentView(shiftViewMvc.rootView)

        shift = mySharedPreferences.getStoredString(SHIFT_PREFERENCE_KEY)
        val token = mySharedPreferences.getStoredString(FCM_TOKEN)
        val newToken = mySharedPreferences.getStoredString(NEW_FCM_TOKEN)

        if (savedInstanceState == null) {
            loadShiftSchedule()
        } else {
            restoreState(savedInstanceState)
        }

        postUserFCM(token, newToken)
        getNotification()
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

    private fun postUserFCM(token: String, newToken: String) {
        val handler = Handler(Looper.getMainLooper())

        if (isInternetAvailable(this)) {
            if (token != newToken) {
                coroutineScope.launch {
                    try {
                        when (val result = submitFormUseCase.submitFCM(token)) {
                            is SubmitFormUseCase.Result.Success -> {
                                Log.d(TAG, "postUserFCM: success ${result.responseCode}")
                                handler.post {
                                    mySharedPreferences.storeStringValue(
                                        NEW_FCM_TOKEN,
                                        token
                                    )
                                }
                            }
                            is SubmitFormUseCase.Result.Failure -> {
                                Log.d(TAG, "postUserFCM: failure ${result.responseCode}")
                            }
                        }
                    } finally {
                        Log.d(TAG, "postUserFCM: ....done")
                    }
                }
            }
        }
    }

    private fun getNotification() {
        val intent = intent
        if (intent != null) {
            if (intent.getStringExtra(FCM_BODY_KEY) != null) {
                if (intent.getStringExtra(FCM_LINK_KEY) != null) {
                    if(intent.getStringExtra(FCM_LINK_KEY) != "") {
                        Log.d(TAG, "updateApp: ${intent.getStringExtra(FCM_LINK_KEY)!!}")
//                        screensNavigator.updateApp(intent.getStringExtra(FCM_LINK_KEY)!!, "update using..")
                    }
                }
            }
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
        coroutineScope.coroutineContext.cancelChildren()
        super.onStop()
    }
}