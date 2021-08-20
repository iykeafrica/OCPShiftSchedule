package com.i_africa.shiftcalenderobajana.screens.shift_materialcalender


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.networking.SubmitFormUseCase
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.shift.utils.CheckNetworkAvailability
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import kotlinx.coroutines.*
import javax.inject.Inject

private val TAG = ShiftMaterialCalendarActivity::class.simpleName
class ShiftMaterialCalendarActivity : BaseActivity(), ShiftMaterialCalendarViewMvc.Listener, MyPopUpMenu.Listener {
    private lateinit var shiftMaterialCalendarViewMvc: ShiftMaterialCalendarViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myPopUpMenu: MyPopUpMenu
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var submitFormUseCase: SubmitFormUseCase

    private lateinit var shift: String
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        shiftMaterialCalendarViewMvc = viewMvcFactory.newShiftMaterialCalendarViewMvc(null)
        setContentView(shiftMaterialCalendarViewMvc.rootView)

        shift = mySharedPreferences.getStoredString(Constant.SHIFT_PREFERENCE_KEY)
        val token = mySharedPreferences.getStoredString(Constant.FCM_TOKEN)
        val newToken = mySharedPreferences.getStoredString(Constant.NEW_FCM_TOKEN)

        if (savedInstanceState == null) {
            loadShiftSchedule()
        } else {
            restoreState(savedInstanceState)
        }

        postUserFCM(token, newToken)
        getNotification()
    }

    private fun loadShiftSchedule() {
        shiftMaterialCalendarViewMvc.showDayOfMonth()
        shiftMaterialCalendarViewMvc.showWeekDay()
        shiftMaterialCalendarViewMvc.showShiftDuty(shift)
        shiftMaterialCalendarViewMvc.showShiftMonthlyWorkingDays(shift)
        shiftMaterialCalendarViewMvc.showShift(shift)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val dayOfMonth = savedInstanceState.getInt(Constant.DAY)
        val month = savedInstanceState.getInt(Constant.MONTH)
        val year = savedInstanceState.getInt(Constant.YEAR)

        shiftMaterialCalendarViewMvc.restoreState(dayOfMonth, month, year)
        loadShiftSchedule()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constant.DAY, shiftMaterialCalendarViewMvc.getDay())
        outState.putInt(Constant.MONTH, shiftMaterialCalendarViewMvc.getMonth())
        outState.putInt(Constant.YEAR, shiftMaterialCalendarViewMvc.getYear())

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

        if (CheckNetworkAvailability.isInternetAvailable(this)) {
            if (token != newToken) {
                coroutineScope.launch {
                    try {
                        when (val result = submitFormUseCase.submitFCM(token)) {
                            is SubmitFormUseCase.Result.Success -> {
                                Log.d(TAG, "postUserFCM: success ${result.responseCode}")
                                handler.post {
                                    mySharedPreferences.storeStringValue(
                                        Constant.NEW_FCM_TOKEN,
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
            if (intent.getStringExtra(Constant.FCM_BODY_KEY) != null) {
                if (intent.getStringExtra(Constant.FCM_LINK_KEY) != null) {
                    if(intent.getStringExtra(Constant.FCM_LINK_KEY) != "") {
                        Log.d(TAG, "updateApp: ${intent.getStringExtra(
                                Constant.FCM_LINK_KEY
                            )!!}")
//                        screensNavigator.updateApp(intent.getStringExtra(FCM_LINK_KEY)!!, "update using..")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        shiftMaterialCalendarViewMvc.registerListener(this)
        myPopUpMenu.registerListener(this)
    }

    override fun onStop() {
        shiftMaterialCalendarViewMvc.unregisterListener(this)
        myPopUpMenu.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
        super.onStop()
    }
}