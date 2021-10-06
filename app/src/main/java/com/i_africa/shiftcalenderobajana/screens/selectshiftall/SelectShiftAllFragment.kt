package com.i_africa.shiftcalenderobajana.screens.selectshiftall

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_A_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_A_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_B_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_B_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_C_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_C_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_D_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_D_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_E_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_E_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_F_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_F_TWO
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_G_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_H_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_I_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_J_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_K_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.PLANT_SHIFT_L_FOUR
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_A
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_B
import com.i_africa.shiftcalenderobajana.utils.Constant.SECURITY_SHIFT_C
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_PREFERENCE_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = SelectShiftAllFragment::class.simpleName

@AndroidEntryPoint
class SelectShiftAllFragment : BaseFragment(), SelectShiftAllViewMvc.Listener {

    private lateinit var selectShiftAllViewMvc: SelectShiftAllViewMvc
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var mySharedPreferences: MySharedPreferences
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private val handler = Handler(Looper.getMainLooper())

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
        selectShiftAllViewMvc = viewMvcFactory.newSelectShiftAllViewMvc(container)
        getNotification()
        return selectShiftAllViewMvc.rootView
    }

    override fun onResume() {
        super.onResume()
        val isFirstTime = mySharedPreferences.getStoredBoolean(FIRST_TIME_LOADING)

        if (!isFirstTime) {
            screensNavigator.navigateToShift()
        }
    }

    override fun twoDaysShift() {
        selectShiftAllViewMvc.enableTwoDaysChipGroup()
    }

    override fun threeDaysShift() {
        selectShiftAllViewMvc.enableThreeDaysChipGroup()
    }

    override fun fourDaysShift() {
        selectShiftAllViewMvc.enableFourDaysChipGroup()
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

    override fun clickPlantATwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_A_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantBTwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_B_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantCTwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_C_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantDTwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_D_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantETwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_E_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantFTwo() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_F_TWO)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantAFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_A_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantBFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_B_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantCFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_C_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantDFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_D_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantEFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_E_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantFFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_F_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantGFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_G_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantHFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_H_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantIFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_I_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantJFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_J_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantKFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_K_FOUR)
        screensNavigator.navigateToShift()
    }

    override fun clickPlantLFour() {
        mySharedPreferences.storeBooleanValue(FIRST_TIME_LOADING, false)
        mySharedPreferences.storeStringValue(SHIFT_PREFERENCE_KEY, PLANT_SHIFT_L_FOUR)
        screensNavigator.navigateToShift()
    }

    private fun getNotification() {
        val intent = activity?.intent
        if (intent != null) {
            if (intent.getStringExtra(FCM_BODY_KEY) != null) {
                if (intent.getStringExtra(FCM_LINK_KEY) != null) {
                    if(intent.getStringExtra(FCM_LINK_KEY) != "") {
                        Log.d(TAG, "updateApp: ${intent.getStringExtra(FCM_LINK_KEY)!!}")
                        handler.post {
                            screensNavigator.updateApp(intent.getStringExtra(FCM_LINK_KEY)!!, "update using..")
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        selectShiftAllViewMvc.registerListener(this)
    }

    override fun onStop() {
        selectShiftAllViewMvc.unregisterListener(this)
        super.onStop()
    }

}