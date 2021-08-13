package com.i_africa.shiftcalenderobajana.screens.viewmvc.shift.utils

object ShiftDuty {

    fun computeShiftDuty(dateDifference: Int, collection: List<String>) : String {

        var shiftDuty = ""

        if (dateDifference == 0) {
             shiftDuty = collection[0]
        }
        if (dateDifference == 1) {
            shiftDuty = collection[1]
        }
        if (dateDifference == 2) {
            shiftDuty = collection[2]
        }
        if (dateDifference == 3) {
            shiftDuty = collection[3]
        }
        if (dateDifference == 4) {
            shiftDuty = collection[4]
        }
        if (dateDifference == 5) {
            shiftDuty = collection[5]
        }
        if (dateDifference == 6) {
            shiftDuty = collection[6]
        }
        if (dateDifference == 7) {
            shiftDuty = collection[7]
        }
        if (dateDifference == 8) {
            shiftDuty = collection[8]
        }

        return shiftDuty
    }

}