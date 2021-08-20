package com.i_africa.shiftcalenderobajana.screens.shift.utils

import java.text.SimpleDateFormat
import java.util.*

object ShiftUtil {

    private const val REFERENCE_DATE: String = "02/01/1970"
    private const val SHIFT_CYCLE_DAYS: Int = 9
    private val FORMAT: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")


    fun setFormula(day: String, month: String, year: String): Int {
        val clickedDate = "$day/$month/$year"

        val referenceDate: Date = FORMAT.parse(REFERENCE_DATE)!!
        val currentDate: Date = FORMAT.parse(clickedDate)!!

        val differenceDate = referenceDate.time - currentDate.time
        return (kotlin.math.abs((differenceDate / (24 * 60 * 60 * 1000)) % SHIFT_CYCLE_DAYS).toInt())
    }


}