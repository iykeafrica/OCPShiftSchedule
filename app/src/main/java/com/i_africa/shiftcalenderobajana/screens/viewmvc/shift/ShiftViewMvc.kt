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

    private var date = Date()
    private val calendar = Calendar.getInstance()
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
        calendar.time = date

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            date = calendar.time

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

    fun getDayOfMonth() {
        binding.dayOfMonthView.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun getWeekDay() {
        binding.weekdayView.text = DateFormatter.weekDay(date)
    }

    private fun setCalendar(cal: Calendar) {
        day = cal.get(Calendar.DAY_OF_MONTH).toString()
        month = (cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getShiftDuty(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftDuty: $day/$month/$year")

        val shiftDuty = computeShiftDuty(setFormula(day, month, year), setCollection(shift))
        binding.shiftView.text = shiftDuty
    }

    fun getShiftMonthlyWorkingDays(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftMonthlyWorkingDays: $day/$month/$year")

        count = computeShiftDutyDays(daysInMonth, month, year, setCollection(shift))
        binding.workingDaysView.text = "${DateFormatter.month(date)} has $count working days."
    }

}