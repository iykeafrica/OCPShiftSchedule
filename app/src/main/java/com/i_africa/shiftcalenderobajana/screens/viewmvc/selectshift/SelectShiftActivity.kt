package com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift

import android.os.Bundle
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity

class SelectShiftActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, SelectShiftFragment())
                .commit()
        }
    }
}