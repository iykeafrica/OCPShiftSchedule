package com.i_africa.shiftcalenderobajana.common.di.activity


import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent() : PresentationComponent
}