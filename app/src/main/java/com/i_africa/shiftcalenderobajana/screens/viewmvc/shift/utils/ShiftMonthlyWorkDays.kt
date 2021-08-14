package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils

import android.util.Log
import com.i_africa.shiftcalenderobajana.utils.Constant

private val TAG = ShiftMonthlyWorkDays::class.simpleName

object ShiftMonthlyWorkDays {

    private var count: Int = 0

//    fun computeWorkingDays(
//        daysInMonth: Int,
//        month: String,
//        year: String,
//        collection: List<String>
//    ): Int {
//        count = 0
//        for (i in 1..daysInMonth) {
//
//            val dateDifference = ShiftUtil.setFormula(i.toString(), month, year)
//
//            if (dateDifference == 0 && collection[0] != Constant.FIRST_OFF && collection[0] != Constant.SECOND_OFF && collection[0] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[0]}")
//                count++
//            }
//            if (dateDifference == 1 && collection[1] != Constant.FIRST_OFF && collection[1] != Constant.SECOND_OFF && collection[1] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[1]}")
//                count++
//            }
//            if (dateDifference == 2 && collection[2] != Constant.FIRST_OFF && collection[2] != Constant.SECOND_OFF && collection[2] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[2]}")
//                count++
//            }
//            if (dateDifference == 3 && collection[3] != Constant.FIRST_OFF && collection[3] != Constant.SECOND_OFF && collection[3] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[3]}")
//                count++
//            }
//            if (dateDifference == 4 && collection[4] != Constant.FIRST_OFF && collection[4] != Constant.SECOND_OFF && collection[4] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[4]}")
//                count++
//            }
//            if (dateDifference == 5 && collection[5] != Constant.FIRST_OFF && collection[5] != Constant.SECOND_OFF && collection[5] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[5]}")
//                count++
//            }
//            if (dateDifference == 6 && collection[6] != Constant.FIRST_OFF && collection[6] != Constant.SECOND_OFF && collection[6] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[6]}")
//                count++
//            }
//            if (dateDifference == 7 && collection[7] != Constant.FIRST_OFF && collection[7] != Constant.SECOND_OFF && collection[7] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[7]}")
//                count++
//            }
//            if (dateDifference == 8 && collection[8] != Constant.FIRST_OFF && collection[8] != Constant.SECOND_OFF && collection[8] != Constant.THIRD_OFF) {
//                Log.d(TAG, "computeShiftDutyDays: ${collection[8]}")
//                count++
//            }
//        }
//        Log.d(TAG, "computeShiftDutyDays: $count")
//        return count
//    }


    fun computeWorkingDays(
        daysInMonth: Int,
        month: String,
        year: String,
        collection: List<String>
    ): Int {
        count = 0
        for (i in 1..daysInMonth) {
            val dateDifference = ShiftUtil.setFormula(i.toString(), month, year)

            for (j in 0..8) {
                if (dateDifference == j && collection[j] != Constant.FIRST_OFF && collection[j] != Constant.SECOND_OFF && collection[j] != Constant.THIRD_OFF) {
                    Log.d(TAG, "computeShiftDutyDays: ${collection[j]}")
                    count++
                }
            }
        }
        Log.d(TAG, "computeShiftDutyDays: $count")
        return count
    }

}