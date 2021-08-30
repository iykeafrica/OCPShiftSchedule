package com.i_africa.shiftcalenderobajana.utils.shift_calendar

import com.i_africa.shiftcalenderobajana.utils.Constant

object ShiftCollection {

    //TWO DAYS SHIFT
    private val twoDaysShiftA = arrayListOf(
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING
    )

    private val twoDaysShiftB = arrayListOf(
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT
    )

    private val twoDaysShiftC = arrayListOf(
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF
    )

    private val twoDaysShiftD = arrayListOf(
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF
    )

    private val twoDaysShiftE = arrayListOf(
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT,
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING
    )

    private val twoDaysShiftF = arrayListOf(
        Constant.FIRST_OFF,
        Constant.SECOND_OFF,
        Constant.FIRST_MORNING,
        Constant.SECOND_MORNING,
        Constant.FIRST_NIGHT,
        Constant.SECOND_NIGHT
    )

    private val twoDaysShiftCollection = listOf(
        Constant.PLANT_SHIFT_A_TWO,
        Constant.PLANT_SHIFT_B_TWO,
        Constant.PLANT_SHIFT_C_TWO,
        Constant.PLANT_SHIFT_D_TWO,
        Constant.PLANT_SHIFT_E_TWO,
        Constant.PLANT_SHIFT_F_TWO
    )

    private fun twoDaysListOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(twoDaysShiftA)
        listOLists.add(twoDaysShiftB)
        listOLists.add(twoDaysShiftC)
        listOLists.add(twoDaysShiftD)
        listOLists.add(twoDaysShiftE)
        listOLists.add(twoDaysShiftF)

        return listOLists
    }


    //THREE DAYS SHIFT
    private val threeDaysShiftA = arrayListOf(
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

    private val threeDaysShiftB = arrayListOf(
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

    private val threeDaysShiftC = arrayListOf(
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

    private val threeDaysCmtceShiftA = arrayListOf(
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

    private val threeDaysCmtceShiftB = arrayListOf(
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

    private val threeDaysCmtceShiftC = arrayListOf(
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

    private val threeDaysSecurityShiftA = arrayListOf(
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

    private val threeDaysSecurityShiftB = arrayListOf(
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

    private val threeDaysSecurityShiftC = arrayListOf(
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

    private val threeDaysShiftCollection = listOf(
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

    private fun threeDaysListOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(threeDaysShiftA)
        listOLists.add(threeDaysShiftB)
        listOLists.add(threeDaysShiftC)
        listOLists.add(threeDaysCmtceShiftA)
        listOLists.add(threeDaysCmtceShiftB)
        listOLists.add(threeDaysCmtceShiftC)
        listOLists.add(threeDaysSecurityShiftA)
        listOLists.add(threeDaysSecurityShiftB)
        listOLists.add(threeDaysSecurityShiftC)

        return listOLists
    }


    //FOUR DAYS SHIFT
    private val fourDaysShiftA = arrayListOf(
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

    private val fourDaysShiftB = arrayListOf(
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

    private val fourDaysShiftC = arrayListOf(
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

    private val fourDaysShiftD = arrayListOf(
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

    private val fourDaysShiftE = arrayListOf(
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

    private val fourDaysShiftF = arrayListOf(
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

    private val fourDaysShiftG = arrayListOf(
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

    private val fourDaysShiftH = arrayListOf(
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

    private val fourDaysShiftI = arrayListOf(
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

    private val fourDaysShiftJ = arrayListOf(
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

    private val fourDaysShiftK = arrayListOf(
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

    private val fourDaysShiftL = arrayListOf(
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

    private val fourDaysShiftCollection = listOf(
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

    private fun fourDaysListOfLists() : ArrayList<ArrayList<String>> {
        val listOLists = ArrayList<ArrayList<String>>()
        listOLists.add(fourDaysShiftA)
        listOLists.add(fourDaysShiftB)
        listOLists.add(fourDaysShiftC)
        listOLists.add(fourDaysShiftD)
        listOLists.add(fourDaysShiftE)
        listOLists.add(fourDaysShiftF)
        listOLists.add(fourDaysShiftG)
        listOLists.add(fourDaysShiftH)
        listOLists.add(fourDaysShiftI)
        listOLists.add(fourDaysShiftJ)
        listOLists.add(fourDaysShiftK)
        listOLists.add(fourDaysShiftL)

        return listOLists
    }


    //SET COLLECTION
    fun setCollection(shift: String, loop: Int): List<String> {
        var collection: List<String> = ArrayList()

        for (i in 0..loop){
            if(loop == Constant.TWO_DAYS_LOOP){
                if (shift == twoDaysShiftCollection[i]) {
                    collection = twoDaysListOfLists()[i]
                }
            }
            if (loop == Constant.THREE_DAYS_LOOP){
                if (shift == threeDaysShiftCollection[i]) {
                    collection = threeDaysListOfLists()[i]
                }
            }
            if (loop == Constant.FOUR_DAYS_LOOP) {
                if (shift == fourDaysShiftCollection[i]) {
                    collection = fourDaysListOfLists()[i]
                }
            }
        }
        return collection
    }

}