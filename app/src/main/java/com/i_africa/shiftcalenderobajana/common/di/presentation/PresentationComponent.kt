package com.i_africa.shiftcalenderobajana.common.di.presentation

import com.i_africa.shiftcalenderobajana.screens.calculate_ot.CalculateOvertimeActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.selectshift.SelectShiftFragment
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent()
interface PresentationComponent {
    fun inject(selectShiftFragment: SelectShiftFragment)
    fun inject(selectShiftActivity: SelectShiftActivity)
    fun inject(shiftActivity: ShiftActivity)
    fun inject(calculateOvertimeActivity: CalculateOvertimeActivity)
}