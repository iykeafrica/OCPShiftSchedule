package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import android.graphics.Color
import android.view.View
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences

object HighlightCurrentDay {
    fun checkCurrentDayAndHighlight(mySharedPreferences: MySharedPreferences, binding: ActivityCustomShiftBinding, currentDay: Int, daysInMonth: Int) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(Constant.DATE_TEXT_COLOR_STRING_KEY)
        if (binding.one.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.one.text.toString()) == currentDay)
                binding.one.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (binding.two.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.two.text.toString()) == currentDay)
                binding.two.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (binding.three.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.three.text.toString()) == currentDay)
                binding.three.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (binding.four.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.four.text.toString()) == currentDay)
                binding.four.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (binding.five.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.five.text.toString()) == currentDay)
                binding.five.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (binding.six.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.six.text.toString()) == currentDay)
                binding.six.setTextColor(Color.parseColor(dayOfMonthColor))
        }

        if (Integer.parseInt(binding.seven.text.toString()) == currentDay)
            binding.seven.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.eight.text.toString()) == currentDay)
            binding.eight.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.nine.text.toString()) == currentDay)
            binding.nine.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.ten.text.toString()) == currentDay)
            binding.ten.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.eleven.text.toString()) == currentDay)
            binding.eleven.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twelve.text.toString()) == currentDay)
            binding.twelve.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.thirteen.text.toString()) == currentDay)
            binding.thirteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.fourteen.text.toString()) == currentDay)
            binding.fourteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.fifteen.text.toString()) == currentDay)
            binding.fifteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.sixteen.text.toString()) == currentDay)
            binding.sixteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.seventeen.text.toString()) == currentDay)
            binding.seventeen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.eighteen.text.toString()) == currentDay)
            binding.eighteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.nineteen.text.toString()) == currentDay)
            binding.nineteen.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt( binding.twenty.text.toString()) == currentDay)
            binding.twenty.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentyone.text.toString()) == currentDay)
            binding.twentyone.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentytwo.text.toString()) == currentDay)
            binding.twentytwo.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentythree.text.toString()) == currentDay)
            binding.twentythree.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentyfour.text.toString()) == currentDay)
            binding.twentyfour.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentyfive.text.toString()) == currentDay)
            binding.twentyfive.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentysix.text.toString()) == currentDay)
            binding.twentysix.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentyseven.text.toString()) == currentDay)
            binding.twentyseven.setTextColor(Color.parseColor(dayOfMonthColor))

        if (Integer.parseInt(binding.twentyeight.text.toString()) == currentDay)
            binding.twentyeight.setTextColor(Color.parseColor(dayOfMonthColor))

        if (binding.twentynine.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.twentynine.text.toString().trim()) > daysInMonth) {
                binding.twentynine.text = ""
            } else {
                binding.fourthRow.visibility = View.VISIBLE
                if (Integer.parseInt(binding.twentynine.text.toString()) == currentDay)
                    binding.twentynine.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirty.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirty.text.toString().trim()) > daysInMonth) {
                binding.thirty.text = ""
            } else {
                if (Integer.parseInt(binding.thirty.text.toString()) == currentDay)
                    binding.thirty.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtyone.text.toString().trim()) > daysInMonth) {
                binding.thirtyone.text = ""
            } else {
                if (Integer.parseInt(binding.thirtyone.text.toString()) == currentDay)
                    binding.thirtyone.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtytwo.text.toString().trim()) > daysInMonth) {
                binding.thirtytwo.text = ""
            } else {
                if (Integer.parseInt(binding.thirtytwo.text.toString()) == currentDay)
                    binding.thirtytwo.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtythree.text.toString().trim()) > daysInMonth) {
                binding.thirtythree.text = ""
            } else {
                if (Integer.parseInt(binding.thirtythree.text.toString()) == currentDay)
                    binding.thirtythree.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtyfour.text.toString().trim()) > daysInMonth) {
                binding.thirtyfour.text = ""
            } else {
                if (Integer.parseInt(binding.thirtyfour.text.toString()) == currentDay)
                    binding.thirtyfour.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtyfive.text.toString().trim()) > daysInMonth) {
                binding.thirtyfive.text = ""
            } else {
                if (Integer.parseInt(binding.thirtyfive.text.toString()) == currentDay)
                    binding.thirtyfive.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtysix.text.toString().trim()) > daysInMonth) {
                binding.thirtysix.text = ""
            } else {
                binding.fifthRow.visibility = View.VISIBLE
                if (Integer.parseInt(binding.thirtysix.text.toString()) == currentDay)
                    binding.thirtysix.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }

        if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
            if (Integer.parseInt(binding.thirtyseven.text.toString().trim()) > daysInMonth) {
                binding.thirtyseven.text = ""
            } else {
                if (Integer.parseInt(binding.thirtyseven.text.toString()) == currentDay)
                    binding.thirtyseven.setTextColor(Color.parseColor(dayOfMonthColor))
            }
        }
    }
}