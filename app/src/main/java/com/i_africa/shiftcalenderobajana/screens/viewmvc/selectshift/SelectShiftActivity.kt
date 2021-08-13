package com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.common.Service
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.FIRST_TIME_LOADING
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY

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