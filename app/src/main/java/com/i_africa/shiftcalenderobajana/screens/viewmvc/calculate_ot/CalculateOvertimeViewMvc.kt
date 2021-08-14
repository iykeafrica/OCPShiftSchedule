package com.i_africa.shiftcalenderobajana.screens.viewmvc.calculate_ot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.i_africa.shiftcalenderobajana.databinding.ActivityCalculateOvertimeBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.calculate_ot.ConversionUtil.setOTCalculation
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTHLY_HOURS
import com.i_africa.shiftcalenderobajana.utils.Constant.OT_MULTIPLIER
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_WORK_HOURS

class CalculateOvertimeViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<CalculateOvertimeViewMvc.Listener>() {

    private val binding = ActivityCalculateOvertimeBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var basic = ""
    private var workedDays = ""

    interface Listener {
        fun calculateOTClick()
        fun basicAndWorkDaysEmpty(message: String)
        fun basicEmpty(message: String)
        fun workDaysEmpty(message: String)

    }

    init {
        binding.submitButton.setOnClickListener {
            for (listener in listeners) {
                listener.calculateOTClick()
            }
        }
    }

    fun calculateOT() {
        basic = binding.BasicInput.text.trim().toString()
        workedDays = binding.WorkDaysInput.text.trim().toString()

        if (basic.isNotEmpty() && workedDays.isNotEmpty()) {
            computeCalculation(basic, workedDays)
        }

        if (basic.isEmpty() && workedDays.isEmpty()) {
            for (listener in listeners) {
                listener.basicAndWorkDaysEmpty("Please, enter your basic salary and worked days.")
                binding.overtime.text = ""
            }

        } else if (basic.isEmpty()) {
            for (listener in listeners) {
                listener.basicEmpty("Please, enter your basic salary.")
                binding.overtime.text = ""
            }

        } else if (workedDays.isEmpty()) {
            for (listener in listeners) {
                listener.workDaysEmpty("Please, enter number of worked days.")
                binding.overtime.text = ""
            }
        }
    }

    private fun computeCalculation(basic: String, workedDays: String) {
        this.basic = basic
        this.workedDays = workedDays

        val overtimeHours = (workedDays.toInt() * SHIFT_WORK_HOURS) - MONTHLY_HOURS

        val overtime = setOTCalculation(basic.toInt(), MONTHLY_HOURS, OT_MULTIPLIER, overtimeHours)
        displayOT(overtime)
    }

    private fun displayOT(overtime: String) {
        binding.overtime.text = "Overtime: ₦$overtime"
        binding.overtime.visibility = View.VISIBLE
    }

    fun basic(): String {
        return binding.BasicInput.text.trim().toString()
    }

    fun workedDays(): String {
        return binding.WorkDaysInput.text.trim().toString()
    }

    fun overtime(): String {
        var overtime = ""
        overtime = if (binding.overtime.visibility == View.VISIBLE)
            binding.overtime.text.toString()
        else
            ""

        return overtime
    }

    fun restorePreviousBasicAndWorkedDays(basic: String, workedDays: String, overtime: String) {
        binding.BasicInput.setText(basic)
        binding.WorkDaysInput.setText(workedDays)

        if (overtime == "")
            binding.overtime.visibility = View.GONE
        else {
            binding.overtime.visibility = View.VISIBLE
            binding.overtime.text = overtime
        }
    }
}