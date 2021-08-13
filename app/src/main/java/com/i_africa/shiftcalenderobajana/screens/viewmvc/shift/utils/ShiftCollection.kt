package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection {
    val plantA = listOf(
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING)

    val plantB = listOf(
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT)

    val plantC = listOf(
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF)

    val cmtceA = listOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF)

    val cmtceB = listOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING)

    val cmtceC = listOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT)

    val securityA = listOf(
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING)

    val securityB = listOf(
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT)

    val securityC = listOf(
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
            collection = plantA
        if (shift == Constant.PLANT_SHIFT_B)
            collection = plantB
        if (shift == Constant.PLANT_SHIFT_C)
            collection = plantC
        if (shift == Constant.CMTCE_SHIFT_A)
            collection = cmtceA
        if (shift == Constant.CMTCE_SHIFT_B)
            collection = cmtceB
        if (shift == Constant.CMTCE_SHIFT_C)
            collection = cmtceC
        if (shift == Constant.SECURITY_SHIFT_A)
            collection = securityA
        if (shift == Constant.SECURITY_SHIFT_B)
            collection = securityB
        if (shift == Constant.SECURITY_SHIFT_C)
            collection = securityC

        return collection
    }


}