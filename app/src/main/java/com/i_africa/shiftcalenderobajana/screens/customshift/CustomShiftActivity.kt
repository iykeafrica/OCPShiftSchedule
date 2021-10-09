package com.i_africa.shiftcalenderobajana.screens.customshift

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.i_africa.shiftcalenderobajana.BuildConfig
import com.i_africa.shiftcalenderobajana.networking.FetchVersionNameCodeUseCase
import com.i_africa.shiftcalenderobajana.networking.SubmitFormUseCase
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.CheckNetworkAvailability
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_BODY_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.UPDATE_APP_URL_LINK
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
    @Inject lateinit var fetchVersionNameCodeUseCase: FetchVersionNameCodeUseCase

    private lateinit var shift: String
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val handler = Handler(Looper.getMainLooper())
    private var state = false

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

    override fun onResume() {
        super.onResume()
        customShiftViewMvc.makeNotificationIconInvisible()
        val versionName = BuildConfig.VERSION_NAME
        val newVersionName = mySharedPreferences.getStoredString(Constant.NEW_PREFERENCE_VERSION_NAME_KEY)

        if (versionName == newVersionName)
            state = false
        getVersionName()
        loadShiftSchedule()
    }

    private fun loadShiftSchedule() {
        customShiftViewMvc.updateCell(mySharedPreferences)
        customShiftViewMvc.showDayOfMonth(mySharedPreferences)
        customShiftViewMvc.showWeekDay()
        customShiftViewMvc.showShiftDuty(shift, mySharedPreferences)
        customShiftViewMvc.showShiftMonthlyWorkingDays(shift)
        customShiftViewMvc.showShift(shift)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val dayOfMonth = savedInstanceState.getInt(Constant.DAY)
        val month = savedInstanceState.getInt(Constant.MONTH)
        val year = savedInstanceState.getInt(Constant.YEAR)

        customShiftViewMvc.restoreState(dayOfMonth, month, year, mySharedPreferences)
        loadShiftSchedule()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constant.DAY, customShiftViewMvc.getDay())
        outState.putInt(Constant.MONTH, customShiftViewMvc.getMonth())
        outState.putInt(Constant.YEAR, customShiftViewMvc.getYear())

        super.onSaveInstanceState(outState)
    }

    override fun popUpMenuClick(v: View) {
        myPopUpMenu.popup(v, state, mySharedPreferences)
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

    override fun update() {
        screensNavigator.visitUrl(UPDATE_APP_URL_LINK)
    }

    override fun settings() {
        screensNavigator.navigateToSettings()
    }

    private fun getVersionName() {
        if (CheckNetworkAvailability.isInternetAvailable(this)) {
            coroutineScope.launch {
                try {
                    when (val result = fetchVersionNameCodeUseCase.fetchVersionCodeName()) {
                        is FetchVersionNameCodeUseCase.Result.Success -> {
                            val updateVersionName = result.versionNameCode[1][0]

                            if (updateVersionName != BuildConfig.VERSION_NAME){
                                handler.post {
                                    Log.d(TAG, "getVersionName: success $updateVersionName")
                                    mySharedPreferences.storeStringValue(Constant.NEW_PREFERENCE_VERSION_NAME_KEY, updateVersionName)
                                    customShiftViewMvc.makeNotificationIconVisible(mySharedPreferences)
                                    state = true
                                }
                            } else {
                               handler.post {
                                   customShiftViewMvc.makeNotificationIconInvisible()
                                   state = false
                               }
                            }
                        }
                        is FetchVersionNameCodeUseCase.Result.Failure -> {
                            Log.d(TAG, "getVersionName: failure ${result.responseCode}")
                        }
                    }

                } finally {
                    Log.d(TAG, "getVersionName: done")
                }
            }
        }
    }

    private fun postUserFCM(token: String, newToken: String) {

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
            if (intent.getStringExtra(FCM_BODY_KEY) != null) {
                if (intent.getStringExtra(FCM_LINK_KEY) != null) {
                    if(intent.getStringExtra(FCM_LINK_KEY) != "") {
                        Log.d(TAG, "updateApp: ${intent.getStringExtra(
                                FCM_LINK_KEY
                            )!!}")

                        handler.post {
                            screensNavigator.visitUrl(intent.getStringExtra(FCM_LINK_KEY)!!)
                        }
                    }
                }
            }
        }
    }


    override fun onBackPressed() {
        screensNavigator.backPressed()
    }

    override fun nextMonthClick() {
        customShiftViewMvc.setNextMonth(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun previousMonthClick() {
        customShiftViewMvc.setPreviousMonth(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun oneClick() {
        customShiftViewMvc.set1(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twoClick() {
        customShiftViewMvc.set2(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun threeClick() {
        customShiftViewMvc.set3(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun fourClick() {
        customShiftViewMvc.set4(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun fiveClick() {
        customShiftViewMvc.set5(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun sixClick() {
        customShiftViewMvc.set6(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun sevenClick() {
        customShiftViewMvc.set7(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun eightClick() {
        customShiftViewMvc.set8(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun nineClick() {
        customShiftViewMvc.set9(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun tenClick() {
        customShiftViewMvc.set10(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun elevenClick() {
        customShiftViewMvc.set11(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twelveClick() {
        customShiftViewMvc.set12(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirteenClick() {
        customShiftViewMvc.set13(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun fourteenClick() {
        customShiftViewMvc.set14(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun fifteenClick() {
        customShiftViewMvc.set15(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun sixteenClick() {
        customShiftViewMvc.set16(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun seventeenClick() {
        customShiftViewMvc.set17(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun eighteenClick() {
        customShiftViewMvc.set18(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun nineteenClick() {
        customShiftViewMvc.set19(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyClick() {
        customShiftViewMvc.set20(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyOneClick() {
        customShiftViewMvc.set21(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyTwoClick() {
        customShiftViewMvc.set22(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyThreeClick() {
        customShiftViewMvc.set23(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyFourClick() {
        customShiftViewMvc.set24(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyFiveClick() {
        customShiftViewMvc.set25(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentySixClick() {
        customShiftViewMvc.set26(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentySevenClick() {
        customShiftViewMvc.set27(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyEightClick() {
        customShiftViewMvc.set28(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun twentyNineClick() {
        customShiftViewMvc.set29(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyClick() {
        customShiftViewMvc.set30(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyOneClick() {
        customShiftViewMvc.set31(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyTwoClick() {
        customShiftViewMvc.set32(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyThreeClick() {
        customShiftViewMvc.set33(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyFourClick() {
        customShiftViewMvc.set34(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtyFiveClick() {
        customShiftViewMvc.set35(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtySixClick() {
        customShiftViewMvc.set36(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun thirtySevenClick() {
        customShiftViewMvc.set37(mySharedPreferences)
        loadShiftSchedule()
    }

    override fun onPause() {
        super.onPause()
        if (myPopUpMenu != null)
            myPopUpMenu.dismiss()
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