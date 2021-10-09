package com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.screens.calculate_ot.CalculateOvertimeViewMvc
import com.i_africa.shiftcalenderobajana.screens.customshift.CustomShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.selectshiftall.SelectShiftAllViewMvc
import com.i_africa.shiftcalenderobajana.screens.settings.SettingsViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val activity: Activity) {

    fun newCalculateOvertimeViewMvc(parent: ViewGroup?): CalculateOvertimeViewMvc {
        return CalculateOvertimeViewMvc(activity, parent)
    }

    fun newCustomShiftViewMvc(parent: ViewGroup?): CustomShiftViewMvc {
        return CustomShiftViewMvc(activity, parent)
    }

    fun newSelectShiftAllViewMvc(parent: ViewGroup?): SelectShiftAllViewMvc {
        return SelectShiftAllViewMvc(activity.layoutInflater, parent)
    }

    fun newSettingsViewMvc(parent: ViewGroup?): SettingsViewMvc {
        return SettingsViewMvc(activity, parent)
    }
}