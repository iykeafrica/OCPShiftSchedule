package com.i_africa.shiftcalenderobajana.screens.common.fragment

import androidx.fragment.app.Fragment
import com.i_africa.shiftcalenderobajana.common.di.presentation.PresentationComponent
import com.i_africa.shiftcalenderobajana.screens.common.activity.BaseActivity

open class BaseFragment: Fragment() {

    private val activityComponent get() = (requireActivity() as BaseActivity).activityComponent

    private val presentationComponent: PresentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    val injector get() = presentationComponent
}