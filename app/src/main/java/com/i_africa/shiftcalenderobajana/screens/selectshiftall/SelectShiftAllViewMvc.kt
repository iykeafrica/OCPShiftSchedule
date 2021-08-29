package com.i_africa.shiftcalenderobajana.screens.selectshiftall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivitySelectShiftAllBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc

class SelectShiftAllViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<SelectShiftAllViewMvc.Listener>() {

    private val binding = ActivitySelectShiftAllBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root
    
    interface Listener {

        fun twoDaysShift()
        fun threeDaysShift()
        fun fourDaysShift()

        //Three Days Shift
        fun clickPlantA()
        fun clickPlantB()
        fun clickPlantC()
        fun clickCmtcA()
        fun clickCmtcB()
        fun clickCmtcC()
        fun clickSecurityA()
        fun clickSecurityB()
        fun clickSecurityC()

        //Two Days Shift
        fun clickPlantATwo()
        fun clickPlantBTwo()
        fun clickPlantCTwo()
        fun clickPlantDTwo()
        fun clickPlantETwo()
        fun clickPlantFTwo()

        //Four Days shift
        fun clickPlantAFour()
        fun clickPlantBFour()
        fun clickPlantCFour()
        fun clickPlantDFour()
        fun clickPlantEFour()
        fun clickPlantFFour()
        fun clickPlantGFour()
        fun clickPlantHFour()
        fun clickPlantIFour()
        fun clickPlantJFour()
        fun clickPlantKFour()
        fun clickPlantLFour()
    }

    init {
        binding.twoDaysShift.setOnClickListener {
            for(listener in listeners) {
                listener.twoDaysShift()
            }
        }

        binding.threeDaysShift.setOnClickListener {
            for(listener in listeners) {
                listener.threeDaysShift()
            }
        }

        binding.fourDaysShift.setOnClickListener {
            for(listener in listeners) {
                listener.fourDaysShift()
            }
        }

        twoDaysChips()
        threeDaysChips()
        fourDaysChips()
    }

    fun enableTwoDaysChipGroup(){
        binding.threeDaysChipGroup.visibility = View.GONE
        binding.fourDaysChipGroup.visibility = View.GONE
        binding.twoDaysChipGroup.visibility = View.VISIBLE
    }

    fun enableThreeDaysChipGroup(){
        binding.fourDaysChipGroup.visibility = View.GONE
        binding.twoDaysChipGroup.visibility = View.GONE
        binding.threeDaysChipGroup.visibility = View.VISIBLE
    }

    fun enableFourDaysChipGroup(){
        binding.twoDaysChipGroup.visibility = View.GONE
        binding.threeDaysChipGroup.visibility = View.GONE
        binding.fourDaysChipGroup.visibility = View.VISIBLE
    }

    private fun twoDaysChips() {
        binding.plantATwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantATwo()
            }
        }

        binding.plantBTwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantBTwo()
            }
        }

        binding.plantCTwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantCTwo()
            }
        }

        binding.plantDTwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantDTwo()
            }
        }

        binding.plantETwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantETwo()
            }
        }

        binding.plantFTwo.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantFTwo()
            }
        }
    }

    private fun threeDaysChips() {
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

    private fun fourDaysChips() {
        binding.plantAFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantAFour()
            }
        }

        binding.plantBFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantBFour()
            }
        }

        binding.plantCFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantCFour()
            }
        }

        binding.plantDFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantDFour()
            }
        }

        binding.plantEFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantEFour()
            }
        }

        binding.plantFFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantFFour()
            }
        }

        binding.plantGFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantGFour()
            }
        }

        binding.plantHFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantHFour()
            }
        }

        binding.plantIFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantIFour()
            }
        }

        binding.plantJFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantJFour()
            }
        }

        binding.plantKFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantKFour()
            }
        }

        binding.plantLFour.setOnClickListener {
            for(listener in listeners) {
                listener.clickPlantLFour()
            }
        }
    }

}