package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection {
    private val plantShiftA = listOf(
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING)

    private val plantShiftB = listOf(
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT)

    private val plantShiftC = listOf(
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF)

    private val cmtceShiftA = listOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF)

    private val cmtceShiftB = listOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING)

    private val cmtceShiftC = listOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT)

    private val securityShiftA = listOf(
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING)

    private val securityShiftB = listOf(
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT)

    private val securityShiftC = listOf(
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF)

    fun setCollection(shift: String): List<String> {
        var collection: List<String> = ArrayList()

        if (shift == Constant.PLANT_SHIFT_A)
            collection = plantShiftA
        if (shift == Constant.PLANT_SHIFT_B)
            collection = plantShiftB
        if (shift == Constant.PLANT_SHIFT_C)
            collection = plantShiftC
        if (shift == Constant.CMTCE_SHIFT_A)
            collection = cmtceShiftA
        if (shift == Constant.CMTCE_SHIFT_B)
            collection = cmtceShiftB
        if (shift == Constant.CMTCE_SHIFT_C)
            collection = cmtceShiftC
        if (shift == Constant.SECURITY_SHIFT_A)
            collection = securityShiftA
        if (shift == Constant.SECURITY_SHIFT_B)
            collection = securityShiftB
        if (shift == Constant.SECURITY_SHIFT_C)
            collection = securityShiftC

        return collection
    }


}