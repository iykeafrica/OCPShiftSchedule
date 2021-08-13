package com.i_africa.shiftcalenderobajana.screens.viewmvcfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift.SelectShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.ShiftViewMvc

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater
) {

    fun newShiftViewMvc(parent: ViewGroup?) : ShiftViewMvc {
        return ShiftViewMvc(layoutInflater, parent)
    }

    fun newSelectShiftViewMvc(parent: ViewGroup?) : SelectShiftViewMvc {
        return SelectShiftViewMvc(layoutInflater, parent)
    }
}