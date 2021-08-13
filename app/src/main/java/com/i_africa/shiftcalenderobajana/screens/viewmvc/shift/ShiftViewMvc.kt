package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.DateFormatter
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftMonthlyWorkDays.computeShiftDutyDays
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftUtil.setFormula
import java.util.*

private const val TAG = "ShiftViewMvc"

class ShiftViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ShiftViewMvc.Listener>() {

    private val binding = ActivityShiftBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var dateOne = Date()
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
        fun popUpMenuClick(v: View)
    }

    init {
        calOne.time = dateOne

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateOne = Date(year, month, (dayOfMonth - 1))
            dateTwo = Date(year, month, dayOfMonth)
            calTwo.set(year, month, dayOfMonth)

            for (listener in listeners) {
                listener.calendarClick()
            }
        }

        binding.menu.setOnClickListener { v ->
            for (listener in listeners) {
                listener.popUpMenuClick(v)
            }
        }
    }

    fun getDayOfMonthOnResume() {
        binding.dayOfMonthView.text = calOne.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun getDayOfMonthOnCalendarClick() {
        binding.dayOfMonthView.text = calTwo.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun getWeekDay() {
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
    }

    private fun setCalendar(cal: Calendar) {
        day = cal.get(Calendar.DAY_OF_MONTH).toString()
        month = (cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getShiftDutyInfoOnResume(shift: String) {
        setCalendar(calOne)
        Log.d(TAG, "getShiftDutyInfoOnResume: $day/$month/$year")

        setShiftDutyInfo(shift)
    }

    fun getShiftDutyInfoOnCalenderClick(shift: String) {
        setCalendar(calTwo)
        Log.d(TAG, "getOnCalendarClickShiftDuty: $day/$month/$year")

        setShiftDutyInfo(shift)
    }

    private fun setShiftDutyInfo(shift: String) {
        binding.shiftView.text = computeShiftDuty(setFormula(day, month, year), setCollection(shift))
        count = computeShiftDutyDays(daysInMonth, month, year, setCollection(shift))
        binding.workingDaysView.text = "${DateFormatter.month(dateTwo)} has $count working days."
    }

}