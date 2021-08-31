package com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.screens.calculate_ot.CalculateOvertimeViewMvc
import com.i_africa.shiftcalenderobajana.screens.customshift.CustomShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.selectshiftall.SelectShiftAllViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun newCalculateOvertimeViewMvc(parent: ViewGroup?): CalculateOvertimeViewMvc {
        return CalculateOvertimeViewMvc(layoutInflater, parent)
    }

    fun newCustomShiftViewMvc(parent: ViewGroup?): CustomShiftViewMvc {
        return CustomShiftViewMvc(layoutInflater, parent)
    }

    fun newSelectShiftAllViewMvc(parent: ViewGroup?): SelectShiftAllViewMvc {
        return SelectShiftAllViewMvc(layoutInflater, parent)
    }
}