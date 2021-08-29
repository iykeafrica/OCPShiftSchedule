package com.i_africa.shiftcalenderobajana.screens.selectshiftall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseApp
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivitySelectShiftAllBinding
import com.i_africa.shiftcalenderobajana.firebase.MyFirebaseMessaging
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftFragment
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