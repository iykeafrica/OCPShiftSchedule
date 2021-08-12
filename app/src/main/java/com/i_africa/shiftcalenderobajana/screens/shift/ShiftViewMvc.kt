package com.i_africa.shiftcalenderobajana.screens.shift

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityShiftBinding
import com.i_africa.shiftcalenderobajana.screens.common.BaseViewMvc
import com.i_africa.shiftcalenderobajana.screens.common.utils.DateFormatter
import java.text.SimpleDateFormat
import java.util.*

class ShiftViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ShiftViewMvc.Listener>() {

    private val binding = ActivityShiftBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var dateOne: Date = Date()
    private var dateTwo = Date()
    private var dateThree = Date()
    private val calOne = Calendar.getInstance()
    private val calTwo = Calendar.getInstance()

    private lateinit var day: String
    private lateinit var month: String
    private lateinit var year: String

    private val REFERENCE_DATE: String = "02/01/1970"
    private val SHIFT_CYCLE_DAYS: Int = 9
    private val FORMAT: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")


    interface Listener {
        fun calendarClick()
        fun popUpMenu(v: View)
    }

    init {
        calOne.time = dateOne

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateOne = Date(year, month, (dayOfMonth - 1))
            dateTwo = Date(year, month, dayOfMonth)
            calTwo.time = dateTwo

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

    fun setOnResumeDate() {
        binding.dayOfMonthView.text = calOne.get(Calendar.DAY_OF_MONTH).toString()
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
        binding.workingDaysView.text = DateFormatter.month(dateTwo)

        calOne.time = dateOne
    }

    fun setOnCreateDate() {
        binding.dayOfMonthView.text = calTwo.get(Calendar.DAY_OF_MONTH).toString()
        binding.weekdayView.text = DateFormatter.weekDay(dateOne)
        binding.workingDaysView.text = DateFormatter.month(dateTwo)
    }

    fun setShiftDutyOnResume() {
        day = calOne.get(Calendar.DAY_OF_MONTH).toString()
        month = (calOne.get(Calendar.MONTH) + 1).toString()
        year = calOne.get(Calendar.YEAR).toString()

        computeShiftDuty(calculateDifference(day, month, year))
    }

    fun setShiftDutyOnCreate() {
        day = calTwo.get(Calendar.DAY_OF_MONTH).toString()
        month = (calTwo.get(Calendar.MONTH) + 1).toString()
        year = calTwo.get(Calendar.YEAR).toString()

        computeShiftDuty(calculateDifference(day, month, year))
    }

    private fun calculateDifference(day: String, month: String, year: String): Int {
        val clickedDate = "$day/$month/$year"

        val referenceDate: Date = FORMAT.parse(REFERENCE_DATE)
        val currentDate: Date = FORMAT.parse(clickedDate)

        val differenceDate = referenceDate.time - currentDate.time
        return ((differenceDate / (24 * 60 * 60 * 1000))/SHIFT_CYCLE_DAYS).toInt()
    }

    private fun computeShiftDuty(dateDifference: Int) {

    }


}