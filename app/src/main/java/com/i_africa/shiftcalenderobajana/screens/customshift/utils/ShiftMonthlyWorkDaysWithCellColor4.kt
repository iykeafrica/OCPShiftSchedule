package com.i_africa.shiftcalenderobajana.screens.customshift.utils

import android.graphics.Color
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftDuty.computeShiftDuty4
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftUtil.setFormula
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftUtil.setFormula4
import com.i_africa.shiftcalenderobajana.utils.Constant

private val TAG = ShiftMonthlyWorkDaysWithCellColor4::class.simpleName

object ShiftMonthlyWorkDaysWithCellColor4 {

    fun updateCellBackground4(
        binding: ActivityCustomShiftBinding,
        month: String,
        year: String,
        collection: List<String>,
    ) {

        if (binding.one.text.toString().trim().isNotEmpty()) {
            val day = binding.one.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.one.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.two.text.toString().trim().isNotEmpty()) {
            val day = binding.two.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.two.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.three.text.toString().trim().isNotEmpty()) {
            val day = binding.three.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.three.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.four.text.toString().trim().isNotEmpty()) {
            val day = binding.four.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.four.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.five.text.toString().trim().isNotEmpty()) {
            val day = binding.five.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.five.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.six.text.toString().trim().isNotEmpty()) {
            val day = binding.six.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.six.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.seven.text.toString().trim().isNotEmpty()) {
            val day = binding.seven.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.seven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eight.text.toString().trim().isNotEmpty()) {
            val day = binding.eight.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.eight.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.nine.text.toString().trim().isNotEmpty()) {
            val day = binding.nine.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.nine.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.ten.text.toString().trim().isNotEmpty()) {
            val day = binding.ten.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.ten.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eleven.text.toString().trim().isNotEmpty()) {
            val day = binding.eleven.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.eleven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twelve.text.toString().trim().isNotEmpty()) {
            val day = binding.twelve.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twelve.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirteen.text.toString().trim().isNotEmpty()) {
            val day = binding.thirteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.fourteen.text.toString().trim().isNotEmpty()) {
            val day = binding.fourteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.fourteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.fifteen.text.toString().trim().isNotEmpty()) {
            val day = binding.fifteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.fifteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.sixteen.text.toString().trim().isNotEmpty()) {
            val day = binding.sixteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.sixteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.seventeen.text.toString().trim().isNotEmpty()) {
            val day = binding.seventeen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.seventeen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eighteen.text.toString().trim().isNotEmpty()) {
            val day = binding.eighteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.eighteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.nineteen.text.toString().trim().isNotEmpty()) {
            val day = binding.nineteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.nineteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twenty.text.toString().trim().isNotEmpty()) {
            val day = binding.twenty.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twenty.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyone.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyone.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentyone.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentytwo.text.toString().trim().isNotEmpty()) {
            val day = binding.twentytwo.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentytwo.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentythree.text.toString().trim().isNotEmpty()) {
            val day = binding.twentythree.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentythree.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyfour.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyfour.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentyfour.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyfive.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyfive.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentyfive.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentysix.text.toString().trim().isNotEmpty()) {
            val day = binding.twentysix.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentysix.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyseven.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyseven.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentyseven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyeight.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyeight.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentyeight.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentynine.text.toString().trim().isNotEmpty()) {
            val day = binding.twentynine.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.twentynine.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirty.text.toString().trim().isNotEmpty()) {
            val day = binding.thirty.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirty.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyone.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtyone.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtytwo.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtytwo.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtythree.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtythree.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyfour.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtyfour.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyfive.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtyfive.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtysix.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtysix.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyseven.text.toString()
            val color = setBackgroundColor(day, month, year, collection)
            binding.thirtyseven.setBackgroundColor(Color.parseColor(color))
        }
    }

    private fun setBackgroundColor(
        day: String,
        month: String,
        year: String,
        collection: List<String>
    ): String {
        val dateDifference = setFormula4(day, month, year)
        val shiftDuty = computeShiftDuty4(dateDifference, collection)

        var color = ""

        if (shiftDuty == Constant.FIRST_MORNING) {
            color = Constant.BACKGROUND_COLOR_MORNING
        }
        if (shiftDuty == Constant.SECOND_MORNING) {
            color = Constant.BACKGROUND_COLOR_MORNING
        }
        if (shiftDuty == Constant.THIRD_MORNING) {
            color = Constant.BACKGROUND_COLOR_MORNING
        }
        if (shiftDuty == Constant.FOURTH_MORNING) {
            color = Constant.BACKGROUND_COLOR_MORNING
        }
        if (shiftDuty == Constant.FIRST_NIGHT) {
            color = Constant.BACKGROUND_COLOR_NIGHT
        }
        if (shiftDuty == Constant.SECOND_NIGHT) {
            color = Constant.BACKGROUND_COLOR_NIGHT
        }
        if (shiftDuty == Constant.THIRD_NIGHT) {
            color = Constant.BACKGROUND_COLOR_NIGHT
        }
        if (shiftDuty == Constant.FOURTH_NIGHT) {
            color = Constant.BACKGROUND_COLOR_NIGHT
        }
        if (shiftDuty == Constant.FIRST_OFF) {
            color = Constant.BACKGROUND_COLOR_OFF
        }
        if (shiftDuty == Constant.SECOND_OFF) {
            color = Constant.BACKGROUND_COLOR_OFF
        }
        if (shiftDuty == Constant.THIRD_OFF) {
            color = Constant.BACKGROUND_COLOR_OFF
        }
        if (shiftDuty == Constant.FOURTH_OFF) {
            color = Constant.BACKGROUND_COLOR_OFF
        }

        return color
    }
}

