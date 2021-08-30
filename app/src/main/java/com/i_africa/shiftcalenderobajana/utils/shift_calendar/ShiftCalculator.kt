package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_4_4_4_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_3_3_3_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_2_2_2_DAYS
import java.text.SimpleDateFormat
import java.util.*

object ShiftCalculator {

    private const val REFERENCE_DATE: String = "02/01/1970"
    private const val SHIFT_CYCLE_THREE_DAYS: Int = 9
    private const val SHIFT_CYCLE_TWO_DAYS: Int = 6
    private const val SHIFT_CYCLE_FOUR_DAYS: Int = 12
    private val FORMAT: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")


    fun setFormula(day: String, month: String, year: String, shiftDays: Int): Int {
        val clickedDate = "$day/$month/$year"

        val referenceDate: Date = FORMAT.parse(REFERENCE_DATE)!!
        val currentDate: Date = FORMAT.parse(clickedDate)!!

        val differenceDate = referenceDate.time - currentDate.time

        var dateDifference = 0

        if (shiftDays == SHIFT_2_2_2_DAYS){
            dateDifference = (kotlin.math.abs((differenceDate / (24 * 60 * 60 * 1000)) % SHIFT_CYCLE_TWO_DAYS).toInt())
        }

        if (shiftDays == SHIFT_3_3_3_DAYS){
            dateDifference = (kotlin.math.abs((differenceDate / (24 * 60 * 60 * 1000)) % SHIFT_CYCLE_THREE_DAYS).toInt())
        }

        if (shiftDays == SHIFT_4_4_4_DAYS){
            dateDifference = (kotlin.math.abs((differenceDate / (24 * 60 * 60 * 1000)) % SHIFT_CYCLE_FOUR_DAYS).toInt())
        }

        return dateDifference
    }

}