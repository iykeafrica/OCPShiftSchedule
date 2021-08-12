package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.DateFormatter
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftDuty
import com.i_africa.shiftcalenderobajana.utils.Constant.FIRST_OFF
import com.i_africa.shiftcalenderobajana.utils.Constant.SECOND_OFF
import com.i_africa.shiftcalenderobajana.utils.Constant.THIRD_OFF
import java.util.*

private const val TAG = "ShiftViewMvc"

class ShiftViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ShiftViewMvc.Listener>() {

    private val binding = ActivityShiftBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var dateOne: Date = Date()
    private var dateTwo = Date()
    private val calOne = Calendar.getInstance()
    private val calTwo = Calendar.getInstance()

    private lateinit var day: String
    private lateinit var month: String
    private lateinit var year: String
    private var daysInMonth: Int = 0
    private var count = 0

    interface Listener {
        fun calendarClick()
        fun popUpMenu(v: View)
    }

    init {
        calOne.time = dateOne

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateOne = Date(year, month, (dayOfMonth - 1))
            dateTwo = Date(year, month, dayOfMonth)
            calTwo.set(year, month, dayOfMonth)
//            calTwo.time = dateTwo

            for (listener in listeners) {
                listener.calendarClick()
            }
        }

        binding.menu.setOnClickListener { v ->
            for (listener in listeners) {
                listener.popUpMenu(v)
            }
        }
    }

    fun getOnResumeDate() {
        binding.dayOfMonthView.text = calOne.get(Calendar.DAY_OF_MONTH).toString()
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
    }

    fun getOnCalendarClickDate() {
        binding.dayOfMonthView.text = calTwo.get(Calendar.DAY_OF_MONTH).toString()
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
    }

    private fun setCalendar(cal: Calendar) {
        day = cal.get(Calendar.DAY_OF_MONTH).toString()
        month = (cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
    }

    fun getOnResumeShiftDuty(shift: String) {
        setCalendar(calOne)
        daysInMonth = calOne.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.d(TAG, "getOnResumeShiftDuty: $day/$month/$year")

        computeShiftDuty(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))
        computeShiftDutyDays(ShiftDuty.setShiftDuty(shift))

        binding.workingDaysView.text = "${DateFormatter.month(dateTwo)} has $count working days."
        count = 0
    }

    fun getOnCalendarClickShiftDuty(shift: String) {
        setCalendar(calTwo)
        daysInMonth = calTwo.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.d(TAG, "getOnCalendarClickShiftDuty: $day/$month/$year")

        computeShiftDuty(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))

        if (count == 0) {
            computeShiftDutyDays(ShiftDuty.setShiftDuty(shift))
            binding.workingDaysView.text = "${DateFormatter.month(dateTwo)} has $count working days."
            count = 0
        }
    }

    private fun computeShiftDuty(dateDifference: Int, collection: List<String>) {

        if (dateDifference == 0) {
            binding.shiftView.text = collection[0]
        }
        if (dateDifference == 1) {
            binding.shiftView.text = collection[1]
        }
        if (dateDifference == 2) {
            binding.shiftView.text = collection[2]
        }
        if (dateDifference == 3) {
            binding.shiftView.text = collection[3]
        }
        if (dateDifference == 4) {
            binding.shiftView.text = collection[4]
        }
        if (dateDifference == 5) {
            binding.shiftView.text = collection[5]
        }
        if (dateDifference == 6) {
            binding.shiftView.text = collection[6]
        }
        if (dateDifference == 7) {
            binding.shiftView.text = collection[7]
        }
        if (dateDifference == 8) {
            binding.shiftView.text = collection[8]
        }
    }

    private fun computeShiftDutyDays(collection: List<String>) {

        for (i in 1..daysInMonth) {
            val dateDifference = ShiftDuty.setFormula(i.toString(), month, year)

            if (dateDifference == 0 && collection[0] != FIRST_OFF && collection[0] != SECOND_OFF && collection[0] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[0]}")
                count++
            }
            if (dateDifference == 1 && collection[1] != FIRST_OFF && collection[1] != SECOND_OFF && collection[1] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[1]}")
                count++
            }
            if (dateDifference == 2 && collection[2] != FIRST_OFF && collection[2] != SECOND_OFF && collection[2] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[2]}")
                count++
            }
            if (dateDifference == 3 && collection[3] != FIRST_OFF && collection[3] != SECOND_OFF && collection[3] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[3]}")
                count++
            }
            if (dateDifference == 4 && collection[4] != FIRST_OFF && collection[4] != SECOND_OFF && collection[4] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[4]}")
                count++
            }
            if (dateDifference == 5 && collection[5] != FIRST_OFF && collection[5] != SECOND_OFF && collection[5] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[5]}")
                count++
            }
            if (dateDifference == 6 && collection[6] != FIRST_OFF && collection[6] != SECOND_OFF && collection[6] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[6]}")
                count++
            }
            if (dateDifference == 7 && collection[7] != FIRST_OFF && collection[7] != SECOND_OFF && collection[7] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[7]}")
                count++
            }
            if (dateDifference == 8 && collection[8] != FIRST_OFF && collection[8] != SECOND_OFF && collection[8] != THIRD_OFF) {
                Log.d(TAG, "computeShiftDutyDays: ${collection[8]}")
                count++
            }
        }
        Log.d(TAG, "computeShiftDutyDays: $count")
    }


}