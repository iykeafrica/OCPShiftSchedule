package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.DateFormatter
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftCollection
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftDuty
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftUtil
import com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils.ShiftUtil.setFormula
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
    }

    fun getShiftDutyInfoOnResume(shift: String) {
        setCalendar(calOne)
        daysInMonth = calOne.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.d(TAG, "getShiftDutyInfoOnResume: $day/$month/$year")

        setShiftDuty(shift)

        computeShiftDutyDays(setCollection(shift))

        binding.workingDaysView.text = "${DateFormatter.month(dateTwo)} has $count working days."
        count = 0
    }

    fun getShiftDutyInfoOnCalenderClick(shift: String) {
        setCalendar(calTwo)
        daysInMonth = calTwo.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.d(TAG, "getOnCalendarClickShiftDuty: $day/$month/$year")

        setShiftDuty(shift)

        if (count == 0) {
            computeShiftDutyDays(setCollection(shift))
            binding.workingDaysView.text =
                "${DateFormatter.month(dateTwo)} has $count working days."
            count = 0
        }
    }

    private fun setShiftDuty(shift: String) {
        binding.shiftView.text = computeShiftDuty(setFormula(day, month, year), setCollection(shift))
    }

    private fun computeShiftDutyDays(collection: List<String>) {

        for (i in 1..daysInMonth) {
            val dateDifference = ShiftUtil.setFormula(i.toString(), month, year)

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