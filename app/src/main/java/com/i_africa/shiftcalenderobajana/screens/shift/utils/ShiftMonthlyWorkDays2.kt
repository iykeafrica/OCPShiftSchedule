package com.i_africa.shiftcalenderobajana.screens.shift.utils

import android.util.Log
import com.i_africa.shiftcalenderobajana.utils.Constant

private val TAG = ShiftMonthlyWorkDays2::class.simpleName

object ShiftMonthlyWorkDays2 {

    private var count: Int = 0

    fun computeWorkingDays2(
        daysInMonth: Int,
        month: String,
        year: String,
        collection: List<String>
    ): Int {
        count = 0
        for (i in 1..daysInMonth) {
            val dateDifference = ShiftUtil.setFormula2(i.toString(), month, year)

            for (j in 0..5) {
                if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF) {
                    Log.d(TAG, "computeShiftDutyDays: ${collection[j]}")
                    count++
                }
            }
        }
        Log.d(TAG, "computeShiftDutyDays: $count")
        return count
    }

}