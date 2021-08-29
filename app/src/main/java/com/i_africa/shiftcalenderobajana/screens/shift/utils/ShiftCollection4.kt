package com.i_africa.shiftcalenderobajana.screens.shift.utils

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection4 {
    private val plantShiftA = arrayListOf(
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING
    )

    private val plantShiftB = arrayListOf(
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT
    )

    private val plantShiftC = arrayListOf(
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF
    )

    private val plantShiftD = arrayListOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,

    )

    private val plantShiftE = arrayListOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING
    )

    private val plantShiftF = arrayListOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
    )

    private val plantShiftG = arrayListOf(
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING
    )

    private val plantShiftH = arrayListOf(
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT
    )

    private val plantShiftI = arrayListOf(
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF
    )

    private val plantShiftJ = arrayListOf(
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING
    )

    private val plantShiftK = arrayListOf(
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF,
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT
    )

    private val plantShiftL = arrayListOf(
        Constant.FOURTH_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.THIRD_MORNING,
        Constant.FOURTH_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.THIRD_NIGHT,
        Constant.FOURTH_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.THIRD_OFF
    )

    private val shiftCollection = listOf(
        Constant.PLANT_SHIFT_A_FOUR,
        Constant.PLANT_SHIFT_B_FOUR,
        Constant.PLANT_SHIFT_C_FOUR,
        Constant.PLANT_SHIFT_D_FOUR,
        Constant.PLANT_SHIFT_E_FOUR,
        Constant.PLANT_SHIFT_F_FOUR,
        Constant.PLANT_SHIFT_G_FOUR,
        Constant.PLANT_SHIFT_H_FOUR,
        Constant.PLANT_SHIFT_I_FOUR,
        Constant.PLANT_SHIFT_J_FOUR,
        Constant.PLANT_SHIFT_K_FOUR,
        Constant.PLANT_SHIFT_L_FOUR,

    )

    private fun listOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(plantShiftA)
        listOLists.add(plantShiftB)
        listOLists.add(plantShiftC)
        listOLists.add(plantShiftD)
        listOLists.add(plantShiftE)
        listOLists.add(plantShiftF)
        listOLists.add(plantShiftG)
        listOLists.add(plantShiftH)
        listOLists.add(plantShiftI)
        listOLists.add(plantShiftJ)
        listOLists.add(plantShiftK)
        listOLists.add(plantShiftL)

        return listOLists
    }

    fun setCollection4(shift: String): List<String> {
        var collection: List<String> = ArrayList()

        for (i in 0..11){
            if (shift == shiftCollection[i]) {
                collection = listOfLists()[i]
            }
        }
        return collection
    }

}