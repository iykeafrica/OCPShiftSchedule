package com.i_africa.shiftcalenderobajana.screens.shift.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val dayFormatter = SimpleDateFormat("EEEE")
    private val monthFormatter = SimpleDateFormat("MMMM")
    private val monthYearHeader = SimpleDateFormat("MMMM yyyy")

    fun weekDay(date: Date): String {
        return dayFormatter.format(date)
    }

    fun month(date: Date): String {
        return monthFormatter.format(date)
    }

    fun monthYearHeader(date: Date): String {
        return monthYearHeader.format(date)
    }


}