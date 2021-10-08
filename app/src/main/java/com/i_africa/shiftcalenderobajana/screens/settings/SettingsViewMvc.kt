package com.i_africa.shiftcalenderobajana.screens.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivitySettingsBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc

class SettingsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<SettingsViewMvc.Listener>() {

    interface Listener {

    }

    private val binding = ActivitySettingsBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    init {

    }
}