package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection {
    private val plantShiftA = arrayListOf(
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING
    )

    private val plantShiftB = arrayListOf(
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT
    )

    private val plantShiftC = arrayListOf(
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF
    )

    private val cmtceShiftA = arrayListOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF
    )

    private val cmtceShiftB = arrayListOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING
    )

    private val cmtceShiftC = arrayListOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT
    )

    private val securityShiftA = arrayListOf(
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING
    )

    private val securityShiftB = arrayListOf(
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT
    )

    private val securityShiftC = arrayListOf(
        Constant.THIRD_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF
    )

    private val shiftCollection = listOf(
        Constant.PLANT_SHIFT_A,
        Constant.PLANT_SHIFT_B,
        Constant.PLANT_SHIFT_C,
        Constant.CMTCE_SHIFT_A,
        Constant.CMTCE_SHIFT_B,
        Constant.CMTCE_SHIFT_C,
        Constant.SECURITY_SHIFT_A,
        Constant.SECURITY_SHIFT_B,
        Constant.SECURITY_SHIFT_C
    )

    private fun listOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(plantShiftA)
        listOLists.add(plantShiftB)
        listOLists.add(plantShiftC)
        listOLists.add(cmtceShiftA)
        listOLists.add(cmtceShiftB)
        listOLists.add(cmtceShiftC)
        listOLists.add(securityShiftA)
        listOLists.add(securityShiftB)
        listOLists.add(securityShiftC)

        return listOLists
    }

    fun setCollection(shift: String): List<String> {
        var collection: List<String> = ArrayList()

        for (i in 0..8){
            if (shift == shiftCollection[i]) {
                collection = listOfLists()[i]
            }
        }
        return collection
    }

}