package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import android.util.Log
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.FOUR_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.THREE_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.TWO_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalculator.setFormula

private val TAG = ShiftMonthlyWorkDays::class.simpleName

object ShiftMonthlyWorkDays {

    private var count: Int = 0

    fun computeWorkingDays(
        daysInMonth: Int,
        month: String,
        year: String,
        collection: List<String>,
        shiftDays: Int,
        loop: Int
    ): Int {
        count = 0
        for (i in 1..daysInMonth) {
            val dateDifference = setFormula(i.toString(), month, year, shiftDays)

            for (j in 0..loop) {
                if(loop == TWO_DAYS_LOOP){
                    if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF) {
                        Log.d(TAG, "computeShiftDutyDays: ${collection[j]}")
                        count++
                    }
                }
                if (loop == THREE_DAYS_LOOP){
                    if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF && collection[j] != Constant.THIRD_OFF) {
                        Log.d(TAG, "computeShiftDutyDays: ${collection[j]}")
                        count++
                    }
                }
                if (loop == FOUR_DAYS_LOOP) {
                    if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF
                        && collection[j] != Constant.THIRD_OFF && collection[j] != Constant.FOURTH_OFF) {
                        Log.d(TAG, "computeShiftDutyDays4: ${collection[j]}")
                        count++
                    }
                }
            }

        }
        Log.d(TAG, "computeShiftDutyDays: $count")
        return count
    }

}