package com.i_africa.shiftcalenderobajana.screens.selectshift

import android.view.LayoutInflater
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivitySelectShiftBinding
import com.i_africa.shiftcalenderobajana.screens.common.BaseViewMvc

class SelectShiftViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<SelectShiftViewMvc.Listener>() {

    private val binding = ActivitySelectShiftBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root
    
    interface Listener {
        fun clickPlantA()
        fun clickPlantB()
        fun clickPlantC()
        fun clickCmtcA()
        fun clickCmtcB()
        fun clickCmtcC()
        fun clickSecurityA()
        fun clickSecurityB()
        fun clickSecurityC()
    }

    init {
        binding.button.setOnClickListener{
            for(listener in listeners) {
                listener.clickPlantA()
            }
        }
        binding.button2.setOnClickListener{
            for(listener in listeners) {
                listener.clickPlantB()
            }
        }
        binding.button3.setOnClickListener{
            for(listener in listeners) {
                listener.clickPlantC()
            }
        }
        binding.button4.setOnClickListener{
            for(listener in listeners) {
                listener.clickCmtcA()
            }
        }
        binding.button5.setOnClickListener{
            for(listener in listeners) {
                listener.clickCmtcB()
            }
        }
        binding.button6.setOnClickListener{
            for(listener in listeners) {
                listener.clickCmtcC()
            }
        }
        binding.button7.setOnClickListener{
            for(listener in listeners) {
                listener.clickSecurityA()
            }
        }
        binding.button8.setOnClickListener{
            for(listener in listeners) {
                listener.clickSecurityB()
            }
        }
        binding.button9.setOnClickListener{
            for(listener in listeners) {
                listener.clickSecurityC()
            }
        }
    }

}