package com.i_africa.shiftcalenderobajana.screens.calculate_ot

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.databinding.ActivityCalculateOvertimeBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.utils.Constant.MAX_SHIFT_WORK_DAYS
import com.i_africa.shiftcalenderobajana.utils.overtime.ConversionUtil.computeOTCalculation
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTHLY_HOURS
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_WORK_HOURS

class CalculateOvertimeViewMvc(
    val activity: Activity,
    parent: ViewGroup?
) : BaseViewMvc<CalculateOvertimeViewMvc.Listener>() {

    private val binding = ActivityCalculateOvertimeBinding.inflate(activity.layoutInflater, parent, false)
    val rootView = binding.root

    private var basic = ""
    private var workedDays = ""

    interface Listener {
        fun calculateOTClick()
        fun basicAndWorkDaysEmpty(message: String)
        fun basicEmpty(message: String)
        fun workDaysEmpty(message: String)
        fun basicZero(message: String)
        fun workedDaysZero(message: String)
        fun basicAndWorkedDaysZero(message: String)
        fun basicSalaryClick()
        fun workedDaysClick()
        fun backPressed()
    }

    init {
        binding.BasicInput.inputType = InputType.TYPE_NULL

        binding.submitButton.setOnClickListener {
            for (listener in listeners) {
                listener.calculateOTClick()
                hideKeyboard()
            }
        }

        binding.BasicInput.setOnClickListener{
            reFocusKeyBoard(binding.BasicInput)
            hideAsterisksNote()
            for (listener in listeners) {
                listener.basicSalaryClick()
            }
        }

        binding.WorkDaysInput.setOnClickListener {
            hideAsterisksNote()
            for (listener in listeners) {
                listener.workedDaysClick()
            }
        }

        binding.backButton.setOnClickListener {
            for (listener in listeners) {
                listener.backPressed()
            }
        }
    }

    private fun reFocusKeyBoard(editText: EditText) {
        editText.requestFocus()
        editText.isActivated = true
        editText.isPressed = true
        editText.isCursorVisible = true
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        val imm: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
    }

    fun hideAsterisksNote() {
        binding.asterisksNote.visibility = View.INVISIBLE
    }

    fun calculateOT() {
        basic = binding.BasicInput.text.trim().toString()
        workedDays = binding.WorkDaysInput.text.trim().toString()

        if (basic.isNotEmpty() && workedDays.isNotEmpty()) {

            if (basic.startsWith('0') && workedDays.startsWith('0')) {
                for (listener in listeners) {
                    listener.basicAndWorkedDaysZero("Basic salary and worked days cannot start with 0.")
                    binding.overtime.text = ""
                }
            }else if (basic.startsWith('0') || basic.toInt() !in 27000..1000000) {
                for (listener in listeners) {
                    listener.basicZero("Enter a basic salary between 27,000 and 1,000,000.")
                    binding.overtime.text = ""
                }
            } else if (workedDays.startsWith('0') || workedDays.toInt() !in 1..31) {
                for (listener in listeners) {
                    listener.workedDaysZero("Enter worked days between 1 and 31.")
                    binding.overtime.text = ""
                }
            } else {
                val leaveState: Boolean = binding.switchState.isChecked
                computeCalculation(basic, workedDays, leaveState)
            }

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

    private fun computeCalculation(basic: String, workedDays: String, leaveState: Boolean) {
        this.basic = basic
        this.workedDays = workedDays

        val overtimeHours = (workedDays.toInt() * SHIFT_WORK_HOURS) - MONTHLY_HOURS
        val maxOvertimeHours = (MAX_SHIFT_WORK_DAYS * SHIFT_WORK_HOURS) - MONTHLY_HOURS
        val workDaysDuringLeave = workedDays.toInt()

        val overtime =
            computeOTCalculation(basic.toInt(), overtimeHours, maxOvertimeHours, workDaysDuringLeave, leaveState)
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

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
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
        hideKeyboard()
        hideAsterisksNote()
    }
}