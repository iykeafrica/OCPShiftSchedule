package com.i_africa.shiftcalenderobajana.screens.selectshiftall

import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.i_africa.shiftcalenderobajana.BuildConfig
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.firebase.MyFirebaseMessaging
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = SelectShiftAllActivity::class.simpleName

@AndroidEntryPoint
class SelectShiftAllActivity : BaseActivity() {
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myFirebaseMessaging: MyFirebaseMessaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: SelectShiftActivity $screensNavigator")
        Log.d(TAG, "onCreate: SelectShiftActivity $mySharedPreferences")
        setContentView(R.layout.main)

        initFCM()

        if(mySharedPreferences.getStoredString(Constant.MORNING_BACKGROUND_COLOR_STRING_KEY) == "")
            mySharedPreferences.storeStringValue(Constant.MORNING_BACKGROUND_COLOR_STRING_KEY,
                Constant.BACKGROUND_COLOR_1
            )

        if(mySharedPreferences.getStoredString(Constant.NIGHT_BACKGROUND_COLOR_STRING_KEY) == "")
            mySharedPreferences.storeStringValue(
                Constant.NIGHT_BACKGROUND_COLOR_STRING_KEY,
                Constant.BACKGROUND_COLOR_2
            )

        if(mySharedPreferences.getStoredString(Constant.OFF_BACKGROUND_COLOR_STRING_KEY) == "")
            mySharedPreferences.storeStringValue(
                Constant.OFF_BACKGROUND_COLOR_STRING_KEY,
                Constant.BACKGROUND_COLOR_3
            )

        if(mySharedPreferences.getStoredString(Constant.DATE_TEXT_COLOR_STRING_KEY) == "")
            mySharedPreferences.storeStringValue(
                Constant.DATE_TEXT_COLOR_STRING_KEY,
                Constant.BACKGROUND_COLOR_13
            )


        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, SelectShiftAllFragment())
                .commit()
        }
    }

    private fun initFCM() {
        FirebaseApp.initializeApp(this)
        subscribeUserToShiftCalendar()
        getFirebaseUserToken()

        val token = mySharedPreferences.getStoredString(Constant.FCM_TOKEN)
        Log.d(TAG, "init: Token is: $token")
    }

    private fun subscribeUserToShiftCalendar() {
        myFirebaseMessaging.subscribe()
    }

    private fun getFirebaseUserToken() {
        val token = myFirebaseMessaging.generateNewToken()
        mySharedPreferences.storeStringValue(Constant.FCM_TOKEN, token)
    }

    override fun onBackPressed() {
        screensNavigator.backPressed()
    }
}