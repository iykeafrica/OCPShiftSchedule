package com.i_africa.shiftcalenderobajana.utils.shift_calendar

object ShiftDuty {
    fun computeShiftDuty(dateDifference: Int, collection: List<String>, loop: Int) : String {
        var shiftDuty = ""

        for (i in 0..loop) {

            if (dateDifference == i)
                shiftDuty = collection[i]
        }
        return shiftDuty
    }
}