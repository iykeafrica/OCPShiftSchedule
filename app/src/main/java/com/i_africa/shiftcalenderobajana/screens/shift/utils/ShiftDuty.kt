package com.i_africa.shiftcalenderobajana.screens.shift.utils

object ShiftDuty {

    fun computeShiftDuty(dateDifference: Int, collection: List<String>) : String {
        var shiftDuty = ""

        for (i in 0..8) {
            if (dateDifference == i)
                shiftDuty = collection[i]
        }
        return shiftDuty
    }

    fun computeShiftDuty2(dateDifference: Int, collection: List<String>) : String {
        var shiftDuty = ""

        for (i in 0..5) {
            if (dateDifference == i)
                shiftDuty = collection[i]
        }
        return shiftDuty
    }

    fun computeShiftDuty4(dateDifference: Int, collection: List<String>) : String {
        var shiftDuty = ""

        for (i in 0..11) {
            if (dateDifference == i)
                shiftDuty = collection[i]
        }
        return shiftDuty
    }

}