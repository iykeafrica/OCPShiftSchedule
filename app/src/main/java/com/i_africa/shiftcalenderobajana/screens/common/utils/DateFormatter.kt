package com.i_africa.shiftcalenderobajana.screens.common.utils

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val dayFormatter = SimpleDateFormat("EEEE")
    private val monthFormatter: Format = SimpleDateFormat("MMMM")

    fun weekDay(date: Date): String {
        return dayFormatter.format(date)
    }

    fun month(date: Date): String {
        return monthFormatter.format(date)
    }
}