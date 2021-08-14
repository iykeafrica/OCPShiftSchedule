package com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift

import android.os.Bundle
import android.util.Log
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import javax.inject.Inject

private val TAG = SelectShiftActivity::class.simpleName

class SelectShiftActivity : BaseActivity() {

    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
        Log.d(TAG, "onCreate: $screensNavigator")
        setContentView(R.layout.main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, SelectShiftFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
       screensNavigator.backPressed()
    }
}