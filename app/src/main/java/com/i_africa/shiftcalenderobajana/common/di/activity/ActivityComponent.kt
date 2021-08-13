package com.i_africa.shiftcalenderobajana.common.di.activity


import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationComponent
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule) : PresentationComponent
}