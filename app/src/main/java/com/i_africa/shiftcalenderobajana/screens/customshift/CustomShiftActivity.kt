package com.i_africa.shiftcalenderobajana.screens.customshift

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.networking.SubmitFormUseCase
import com.i_africa.shiftcalenderobajana.screens.common.MyPopUpMenu
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftActivity
import com.i_africa.shiftcalenderobajana.screens.shift.utils.CheckNetworkAvailability
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.OnSwipeTouchListener
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

private val TAG = CustomShiftActivity::class.simpleName

@AndroidEntryPoint
class CustomShiftActivity : BaseActivity(), CustomShiftViewMvc.Listener, MyPopUpMenu.Listener {

    private lateinit var customShiftViewMvc: CustomShiftViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myPopUpMenu: MyPopUpMenu
    @Inject lateinit var viewMvcFactory: ViewMvcFactory
    @Inject lateinit var submitFormUseCase: SubmitFormUseCase
//    @Inject lateinit var onSwipeTouchListener: com.i_africa.shiftcalenderobajana.utils.OnSwipeTouchListener

    private lateinit var shift: String
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customShiftViewMvc = viewMvcFactory.newCustomShiftViewMvc(null)
        shift = mySharedPreferences.getStoredString(Constant.SHIFT_PREFERENCE_KEY)
        setContentView(customShiftViewMvc.rootView)

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
        customShiftViewMvc.showDayOfMonth()
        customShiftViewMvc.showWeekDay()
        customShiftViewMvc.showShiftDuty(shift)
        customShiftViewMvc.showShiftMonthlyWorkingDays(shift)
        customShiftViewMvc.showShift(shift)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val dayOfMonth = savedInstanceState.getInt(Constant.DAY)
        val month = savedInstanceState.getInt(Constant.MONTH)
        val year = savedInstanceState.getInt(Constant.YEAR)

        customShiftViewMvc.restoreState(dayOfMonth, month, year)
        loadShiftSchedule()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constant.DAY, customShiftViewMvc.getDay())
        outState.putInt(Constant.MONTH, customShiftViewMvc.getMonth())
        outState.putInt(Constant.YEAR, customShiftViewMvc.getYear())

        super.onSaveInstanceState(outState)
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


    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun nextMonthClick() {
        customShiftViewMvc.setNextMonth()
        loadShiftSchedule()
    }

    override fun previousMonthClick() {
        customShiftViewMvc.setPreviousMonth()
        loadShiftSchedule()
    }

    override fun oneClick() {
        customShiftViewMvc.set1()
        loadShiftSchedule()
    }

    override fun twoClick() {
        customShiftViewMvc.set2()
        loadShiftSchedule()
    }

    override fun threeClick() {
        customShiftViewMvc.set3()
        loadShiftSchedule()
    }

    override fun fourClick() {
        customShiftViewMvc.set4()
        loadShiftSchedule()
    }

    override fun fiveClick() {
        customShiftViewMvc.set5()
        loadShiftSchedule()
    }

    override fun sixClick() {
        customShiftViewMvc.set6()
        loadShiftSchedule()
    }

    override fun sevenClick() {
        customShiftViewMvc.set7()
        loadShiftSchedule()
    }

    override fun eightClick() {
        customShiftViewMvc.set8()
        loadShiftSchedule()
    }

    override fun nineClick() {
        customShiftViewMvc.set9()
        loadShiftSchedule()
    }

    override fun tenClick() {
        customShiftViewMvc.set10()
        loadShiftSchedule()
    }

    override fun elevenClick() {
        customShiftViewMvc.set11()
        loadShiftSchedule()
    }

    override fun twelveClick() {
        customShiftViewMvc.set12()
        loadShiftSchedule()
    }

    override fun thirteenClick() {
        customShiftViewMvc.set13()
        loadShiftSchedule()
    }

    override fun fourteenClick() {
        customShiftViewMvc.set14()
        loadShiftSchedule()
    }

    override fun fifteenClick() {
        customShiftViewMvc.set15()
        loadShiftSchedule()
    }

    override fun sixteenClick() {
        customShiftViewMvc.set16()
        loadShiftSchedule()
    }

    override fun seventeenClick() {
        customShiftViewMvc.set17()
        loadShiftSchedule()
    }

    override fun eighteenClick() {
        customShiftViewMvc.set18()
        loadShiftSchedule()
    }

    override fun nineteenClick() {
        customShiftViewMvc.set19()
        loadShiftSchedule()
    }

    override fun twentyClick() {
        customShiftViewMvc.set20()
        loadShiftSchedule()
    }

    override fun twentyOneClick() {
        customShiftViewMvc.set21()
        loadShiftSchedule()
    }

    override fun twentyTwoClick() {
        customShiftViewMvc.set22()
        loadShiftSchedule()
    }

    override fun twentyThreeClick() {
        customShiftViewMvc.set23()
        loadShiftSchedule()
    }

    override fun twentyFourClick() {
        customShiftViewMvc.set24()
        loadShiftSchedule()
    }

    override fun twentyFiveClick() {
        customShiftViewMvc.set25()
        loadShiftSchedule()
    }

    override fun twentySixClick() {
        customShiftViewMvc.set26()
        loadShiftSchedule()
    }

    override fun twentySevenClick() {
        customShiftViewMvc.set27()
        loadShiftSchedule()
    }

    override fun twentyEightClick() {
        customShiftViewMvc.set28()
        loadShiftSchedule()
    }

    override fun twentyNineClick() {
        customShiftViewMvc.set29()
        loadShiftSchedule()
    }

    override fun thirtyClick() {
        customShiftViewMvc.set30()
        loadShiftSchedule()
    }

    override fun thirtyOneClick() {
        customShiftViewMvc.set31()
        loadShiftSchedule()
    }

    override fun thirtyTwoClick() {
        customShiftViewMvc.set32()
        loadShiftSchedule()
    }

    override fun thirtyThreeClick() {
        customShiftViewMvc.set33()
        loadShiftSchedule()
    }

    override fun thirtyFourClick() {
        customShiftViewMvc.set34()
        loadShiftSchedule()
    }

    override fun thirtyFiveClick() {
        customShiftViewMvc.set35()
        loadShiftSchedule()
    }

    override fun thirtySixClick() {
        customShiftViewMvc.set36()
        loadShiftSchedule()
    }

    override fun thirtySevenClick() {
        customShiftViewMvc.set37()
        loadShiftSchedule()
    }

    override fun onStart() {
        super.onStart()
        customShiftViewMvc.registerListener(this)
        myPopUpMenu.registerListener(this)
    }

    override fun onStop() {
        customShiftViewMvc.unregisterListener(this)
        myPopUpMenu.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
        super.onStop()
    }
}