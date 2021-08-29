package com.i_africa.shiftcalenderobajana.screens.shift.utils

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection2 {
    private val plantShiftA = arrayListOf(
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING
    )

    private val plantShiftB = arrayListOf(
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT
    )

    private val plantShiftC = arrayListOf(
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF
    )

    private val plantShiftD = arrayListOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF
    )

    private val plantShiftE = arrayListOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING
    )

    private val plantShiftF = arrayListOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT
    )

    private val shiftCollection = listOf(
        Constant.PLANT_SHIFT_A_TWO,
        Constant.PLANT_SHIFT_B_TWO,
        Constant.PLANT_SHIFT_C_TWO,
        Constant.PLANT_SHIFT_D_TWO,
        Constant.PLANT_SHIFT_E_TWO,
        Constant.PLANT_SHIFT_F_TWO
    )

    private fun listOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(plantShiftA)
        listOLists.add(plantShiftB)
        listOLists.add(plantShiftC)
        listOLists.add(plantShiftD)
        listOLists.add(plantShiftE)
        listOLists.add(plantShiftF)

        return listOLists
    }

    fun setCollection2(shift: String): List<String> {
        var collection: List<String> = ArrayList()

        for (i in 0..5){
            if (shift == shiftCollection[i]) {
                collection = listOfLists()[i]
            }
        }
        return collection
    }

}