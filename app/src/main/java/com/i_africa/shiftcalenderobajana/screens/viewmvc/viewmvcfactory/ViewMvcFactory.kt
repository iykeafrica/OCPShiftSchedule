package com.i_africa.shiftcalenderobajana.screens.viewmvc.viewmvcfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.screens.calculate_ot.CalculateOvertimeViewMvc
import com.i_africa.shiftcalenderobajana.screens.customshift.CustomShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun newShiftViewMvc(parent: ViewGroup?) : ShiftViewMvc {
        return ShiftViewMvc(layoutInflater, parent)
    }

    fun newSelectShiftViewMvc(parent: ViewGroup?) : SelectShiftViewMvc {
        return SelectShiftViewMvc(layoutInflater, parent)
    }

    fun newCalculateOvertimeViewMvc(parent: ViewGroup?): CalculateOvertimeViewMvc {
        return CalculateOvertimeViewMvc(layoutInflater, parent)
    }

    fun newCustomShiftViewMvc(parent: ViewGroup?): CustomShiftViewMvc {
        return CustomShiftViewMvc(layoutInflater, parent)
    }
}