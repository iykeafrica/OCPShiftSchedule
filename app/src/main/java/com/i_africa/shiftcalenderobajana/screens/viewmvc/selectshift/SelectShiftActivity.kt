package com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift

import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_TOKEN
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import javax.inject.Inject

private val TAG = SelectShiftActivity::class.simpleName
class SelectShiftActivity : BaseActivity() {

    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var myFirebaseMessaging: MyFirebaseMessaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
        Log.d(TAG, "onCreate: SelectShiftActivity $screensNavigator")
        Log.d(TAG, "onCreate: SelectShiftActivity $mySharedPreferences")
        setContentView(R.layout.main)

        initFCM()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, SelectShiftFragment())
                .commit()
        }
    }

    private fun initFCM() {
        FirebaseApp.initializeApp(this)
        subscribeUserToShiftCalendar()
        getFirebaseUserToken()

        val token = mySharedPreferences.getStoredString(FCM_TOKEN)
        Log.d(TAG, "init: Token is: $token")
    }

    private fun subscribeUserToShiftCalendar() {
        myFirebaseMessaging.subscribe()
    }

    private fun getFirebaseUserToken() {
        val token = myFirebaseMessaging.generateNewToken()
        mySharedPreferences.storeStringValue(FCM_TOKEN, token)
    }

    override fun onBackPressed() {
       screensNavigator.backPressed()
    }
}