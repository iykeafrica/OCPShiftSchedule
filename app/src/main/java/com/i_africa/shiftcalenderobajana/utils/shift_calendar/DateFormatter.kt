package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val dayFormatter = SimpleDateFormat("EEEE")
    private val monthFormatter = SimpleDateFormat("MMMM")
    private val monthYearHeader = SimpleDateFormat("MMMM yyyy")
    private val dateCatalog  = SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss")

    fun weekDay(date: Date): String {
        return dayFormatter.format(date)
    }

    fun month(date: Date): String {
        return monthFormatter.format(date)
    }

    fun monthYearHeader(date: Date): String {
        return monthYearHeader.format(date)
    }

    fun dateCatalog(date: Date): String {
        return monthYearHeader.format(date)
    }

    fun dateCatalog(calendar: Calendar): String {
        return dateCatalog.format(calendar.time)
    }


}