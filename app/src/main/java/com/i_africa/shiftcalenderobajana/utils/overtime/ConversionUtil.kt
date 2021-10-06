package com.i_africa.shiftcalenderobajana.utils.overtime

import com.i_africa.shiftcalenderobajana.utils.Constant.MAX_SHIFT_WORK_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.MONTHLY_HOURS
import com.i_africa.shiftcalenderobajana.utils.Constant.OT_MULTIPLIER
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

object ConversionUtil {
    private var decimalFormat: DecimalFormat = DecimalFormat("###,###,###.00")
    private const val SCALE = 3

    fun computeOTCalculation(
        basic: Int,
        overtimeHours: Int,
        maxOvertimeHours: Int,
        workDaysDuringLeave: Int,
        daysOfLeave: Int
    ): String {

        val basicBD = BigDecimal.valueOf(basic.toDouble())
        val maxMonthlyHoursBD = BigDecimal.valueOf(MONTHLY_HOURS.toDouble())
        val otMultiplierBD = BigDecimal.valueOf(OT_MULTIPLIER)
        val overtimeHoursBD = BigDecimal.valueOf(overtimeHours.toDouble())
        val maxOvertimeHoursBD = BigDecimal.valueOf(maxOvertimeHours.toDouble())
        val workDaysDuringLeaveBD = BigDecimal.valueOf(workDaysDuringLeave.toDouble())
        val maxShiftWorkDaysBD = BigDecimal.valueOf(MAX_SHIFT_WORK_DAYS.toDouble())

        val ot: String

        val basicDivideByMonthlyHours =
            basicBD.divide(maxMonthlyHoursBD, SCALE, RoundingMode.CEILING)

        ot = if (daysOfLeave == 0) {  //overtime = ((basic / MONTHLY_HOURS) * OT_MULTIPLIER) * overtimeHours
            val otMultiplierMultiplyByOvertimeHours = otMultiplierBD.multiply(overtimeHoursBD)
            val overtime = basicDivideByMonthlyHours.multiply(otMultiplierMultiplyByOvertimeHours)

            decimalFormat.format(overtime).toString()

        } else {
            //overtime = ((basic / MONTHLY_HOURS) * OT_MULTIPLIER) * overtimeHours

            val otMultiplierMultiplyByMaxOvertimeHours = otMultiplierBD.multiply(maxOvertimeHoursBD)
            val maxOvertime = basicDivideByMonthlyHours.multiply(otMultiplierMultiplyByMaxOvertimeHours)
            val oneDayOvertime = maxOvertime.divide(maxShiftWorkDaysBD, SCALE, RoundingMode.CEILING)

            val overtime = oneDayOvertime.multiply(workDaysDuringLeaveBD)
            decimalFormat.format(overtime).toString()
        }

        return ot
    }
}