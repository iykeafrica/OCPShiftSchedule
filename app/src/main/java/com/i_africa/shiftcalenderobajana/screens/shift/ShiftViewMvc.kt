package com.i_africa.shiftcalenderobajana.screens.shift

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.common.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.common.utils.DateFormatter
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
        binding.workingDaysView.text = DateFormatter.month(dateTwo)
    }

    fun getOnCalendarClickDate() {
        binding.dayOfMonthView.text = calTwo.get(Calendar.DAY_OF_MONTH).toString()
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
        binding.workingDaysView.text = DateFormatter.month(dateTwo)
    }

    private fun setCalendar(cal: Calendar) {
        day = cal.get(Calendar.DAY_OF_MONTH).toString()
        month = (cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
    }

    fun getOnResumeShiftDuty(shift: String) {
        setCalendar(calOne)
        Log.d(TAG, "getOnResumeShiftDuty: $day/$month/$year")

        computeShiftDuty(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))
        computeShiftDutyDays(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))
    }

    fun getOnCalendarClickShiftDuty(shift: String) {
        setCalendar(calTwo)
        Log.d(TAG, "getOnCalendarClickShiftDuty: $day/$month/$year")

        computeShiftDuty(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))
        computeShiftDutyDays(ShiftDuty.setFormula(day, month, year), ShiftDuty.setShiftDuty(shift))
    }

    private fun computeShiftDuty(dateDifference: Int, collection: List<String>) {
        var count: Int = 0

        if (dateDifference == 0) {
            Log.d(TAG, "computeShiftDuty: $dateDifference")
            binding.shiftView.text = collection[0]

            if ()
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

    private fun computeShiftDutyDays(dateDifference: Int, collection: List<String>) {

    }


}