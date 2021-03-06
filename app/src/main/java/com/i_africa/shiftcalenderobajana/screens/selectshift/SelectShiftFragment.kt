package com.i_africa.shiftcalenderobajana.screens.selectshift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.screens.common.ScreensNavigator
import com.i_africa.shiftcalenderobajana.screens.common.fragment.BaseFragment
import com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory.ViewMvcFactory
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.CMTCE_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_BODY_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FCM_LINK_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.FIRST_TIME_LOADING
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = SelectShiftFragment::class.simpleName

@AndroidEntryPoint
class SelectShiftFragment : BaseFragment(), SelectShiftViewMvc.Listener {

    private lateinit var selectShiftViewMvc: SelectShiftViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: SelectShiftFragment $screensNavigator")
        Log.d(TAG, "onCreate: SelectShiftFragment $mySharedPreferences")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectShiftViewMvc = viewMvcFactory.newSelectShiftViewMvc(container)
        getNotification()
        return selectShiftViewMvc.rootView
    }

    override fun onResume() {
        super.onResume()
        val isFirstTime = mySharedPreferences.getStoredBoolean(FIRST_TIME_LOADING)

        if (!isFirstTime) {
            screensNavigator.navigateToShift()
        }
    }

    override fun clickPlantA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_A)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_B)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_C)
        screensNavigator.navigateToShift()
    }

    override fun clickCmtcA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_A)
        screensNavigator.navigateToShift()
    }

    override fun clickCmtcB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_B)
        screensNavigator.navigateToShift()
    }

    override fun clickCmtcC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, CMTCE_SHIFT_C)
        screensNavigator.navigateToShift()
    }

    override fun clickSecurityA() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_A)
        screensNavigator.navigateToShift()
    }

    override fun clickSecurityB() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_B)
        screensNavigator.navigateToShift()
    }

    override fun clickSecurityC() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, SECURITY_SHIFT_C)
        screensNavigator.navigateToShift()
    }

    private fun getNotification() {
        val intent = activity?.intent
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
        selectShiftViewMvc.registerListener(this)
    }

    override fun onStop() {
        selectShiftViewMvc.unregisterListener(this)
        super.onStop()
    }

}