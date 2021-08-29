package com.i_africa.shiftcalenderobajana.screens.shift.utils

import android.util.Log
import com.i_africa.shiftcalenderobajana.utils.Constant

private val TAG = ShiftMonthlyWorkDays4::class.simpleName

object ShiftMonthlyWorkDays4 {

    private var count: Int = 0

    fun computeWorkingDays4(
        daysInMonth: Int,
        month: String,
        year: String,
        collection: List<String>
    ): Int {
        count = 0
        for (i in 1..daysInMonth) {
            val dateDifference = ShiftUtil.setFormula4(i.toString(), month, year)

            for (j in 0..11) {
                if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF
                    && collection[j] != Constant.THIRD_OFF && collection[j] != Constant.FOURTH_OFF) {
                    Log.d(TAG, "computeShiftDutyDays4: ${collection[j]}")
                    count++
                }
            }
        }
        Log.d(TAG, "computeShiftDutyDays4: $count")
        return count
    }

}