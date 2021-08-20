package com.i_africa.shiftcalenderobajana.screens.shift_materialcalender

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftMaterialCalendarBinding
import com.i_africa.shiftcalenderobajana.screens.shift.ShiftViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.shift.utils.DateFormatter
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftMonthlyWorkDays.computeWorkingDays
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftUtil.setFormula
import java.util.*

private val TAG = ShiftMaterialCalendarViewMvc::class.simpleName

class ShiftMaterialCalendarViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ShiftMaterialCalendarViewMvc.Listener>() {

    private val binding = ActivityShiftMaterialCalendarBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var date = Date()
    private val calendar = Calendar.getInstance()
    private lateinit var calendar2: Calendar
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
        calendar2 = binding.calendarView.currentPageDate
        calendar2.time = date

        binding.calendarView.setOnDayClickListener { day ->
            calendar2 =  day.calendar
            for (listener in listeners) {
                listener.calendarClick()
            }
        }

        binding.calendarView.setOnForwardPageChangeListener {

        }

        binding.calendarView.setOnPreviousPageChangeListener {

        }

        binding.menu.setOnClickListener { v ->
            for (listener in listeners) {
                listener.popUpMenuClick(v)
            }
        }
    }

    fun showDayOfMonth() {
        binding.dayOfMonthView.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun showWeekDay() {
        binding.weekdayView.text = DateFormatter.weekDay(date)
    }

//    private fun setCalendar(cal: Calendar) {
//        day = cal.get(Calendar.DAY_OF_MONTH).toString()
//        month = (cal.get(Calendar.MONTH) + 1).toString()
//        year = cal.get(Calendar.YEAR).toString()
//        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
//    }

    private fun setCalendar(cal: Calendar) {
        day = calendar2.get(Calendar.DAY_OF_MONTH).toString()
        month = (calendar2.get(Calendar.MONTH) + 1).toString()
        year = calendar2.get(Calendar.YEAR).toString()
        daysInMonth = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun showShiftDuty(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftDuty: $day/$month/$year")

        val shiftDuty = computeShiftDuty(setFormula(day, month, year), setCollection(shift))
        binding.shiftView.text = shiftDuty
    }

    fun showShiftMonthlyWorkingDays(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftMonthlyWorkingDays: $day/$month/$year")

        count = computeWorkingDays(daysInMonth, month, year, setCollection(shift))
        binding.workingDaysView.text = "${DateFormatter.month(date)} has $count working days."
    }

    fun getDay() : Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getMonth() : Int {
        return calendar.get(Calendar.MONTH)
    }

    fun getYear() : Int {
        return calendar.get(Calendar.YEAR)
    }

    fun restoreState(dayOfMonth: Int, month: Int, year: Int) {
        calendar.set(year, month, dayOfMonth)
        date = calendar.time
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        binding.calendarView.setDate(date)
    }

    fun showShift(shift: String) {
        binding.shift.text = shift
    }

}