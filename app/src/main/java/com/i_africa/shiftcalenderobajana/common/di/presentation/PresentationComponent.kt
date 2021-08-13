package com.i_africa.shiftcalenderobajana.common.di.presentation

import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift.SelectShiftActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift.SelectShiftFragment
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.ShiftActivity
import dagger.Component
import dagger.Subcomponent

@PresentationScope
@Subcomponent()
interface PresentationComponent {
    fun inject(selectShiftFragment: SelectShiftFragment)
    fun inject(selectShiftActivity: SelectShiftActivity)
    fun inject(shiftActivity: ShiftActivity)
}