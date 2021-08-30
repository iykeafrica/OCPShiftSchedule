package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import android.graphics.Color
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalculator.setFormula
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftDuty.computeShiftDuty

object ShiftCalendarCellColor {
    fun updateShiftCalendarCellColor(
        binding: ActivityCustomShiftBinding,
        month: String,
        year: String,
        collection: List<String>,
        shiftDays: Int,
        loop: Int
    ) {

        if (binding.one.text.toString().trim().isNotEmpty()) {
            val day = binding.one.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.one.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.two.text.toString().trim().isNotEmpty()) {
            val day = binding.two.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.two.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.three.text.toString().trim().isNotEmpty()) {
            val day = binding.three.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.three.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.four.text.toString().trim().isNotEmpty()) {
            val day = binding.four.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.four.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.five.text.toString().trim().isNotEmpty()) {
            val day = binding.five.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.five.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.six.text.toString().trim().isNotEmpty()) {
            val day = binding.six.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.six.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.seven.text.toString().trim().isNotEmpty()) {
            val day = binding.seven.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.seven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eight.text.toString().trim().isNotEmpty()) {
            val day = binding.eight.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.eight.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.nine.text.toString().trim().isNotEmpty()) {
            val day = binding.nine.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.nine.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.ten.text.toString().trim().isNotEmpty()) {
            val day = binding.ten.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.ten.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eleven.text.toString().trim().isNotEmpty()) {
            val day = binding.eleven.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.eleven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twelve.text.toString().trim().isNotEmpty()) {
            val day = binding.twelve.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twelve.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirteen.text.toString().trim().isNotEmpty()) {
            val day = binding.thirteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.fourteen.text.toString().trim().isNotEmpty()) {
            val day = binding.fourteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.fourteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.fifteen.text.toString().trim().isNotEmpty()) {
            val day = binding.fifteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.fifteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.sixteen.text.toString().trim().isNotEmpty()) {
            val day = binding.sixteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.sixteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.seventeen.text.toString().trim().isNotEmpty()) {
            val day = binding.seventeen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.seventeen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.eighteen.text.toString().trim().isNotEmpty()) {
            val day = binding.eighteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.eighteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.nineteen.text.toString().trim().isNotEmpty()) {
            val day = binding.nineteen.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.nineteen.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twenty.text.toString().trim().isNotEmpty()) {
            val day = binding.twenty.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twenty.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyone.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyone.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentyone.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentytwo.text.toString().trim().isNotEmpty()) {
            val day = binding.twentytwo.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentytwo.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentythree.text.toString().trim().isNotEmpty()) {
            val day = binding.twentythree.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentythree.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyfour.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyfour.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentyfour.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyfive.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyfive.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentyfive.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentysix.text.toString().trim().isNotEmpty()) {
            val day = binding.twentysix.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentysix.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyseven.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyseven.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentyseven.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentyeight.text.toString().trim().isNotEmpty()) {
            val day = binding.twentyeight.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentyeight.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.twentynine.text.toString().trim().isNotEmpty()) {
            val day = binding.twentynine.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.twentynine.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirty.text.toString().trim().isNotEmpty()) {
            val day = binding.thirty.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirty.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyone.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtyone.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtytwo.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtytwo.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtythree.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtythree.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyfour.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtyfour.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyfive.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtyfive.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtysix.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtysix.setBackgroundColor(Color.parseColor(color))
        }

        if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
            val day = binding.thirtyseven.text.toString()
            val color = setBackgroundColor(day, month, year, collection, shiftDays, loop)
            binding.thirtyseven.setBackgroundColor(Color.parseColor(color))
        }
    }

    private fun setBackgroundColor(
        day: String,
        month: String,
        year: String,
        collection: List<String>,
        shiftDays: Int,
        loop: Int
    ): String {
        val dateDifference = setFormula(day, month, year, shiftDays)
        val shiftDuty = computeShiftDuty(dateDifference, collection, loop)

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