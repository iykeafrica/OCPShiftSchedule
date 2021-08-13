package com.i_africa.shiftcalenderobajana.common.di.presentation

import com.i_africa.shiftcalenderobajana.common.di.activity.ActivityComponent
import com.i_africa.shiftcalenderobajana.screens.viewmvc.selectshift.SelectShiftFragment
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.ShiftActivity
import dagger.Component
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(shiftFragment: SelectShiftFragment)
    fun inject(shiftActivity: ShiftActivity)
}