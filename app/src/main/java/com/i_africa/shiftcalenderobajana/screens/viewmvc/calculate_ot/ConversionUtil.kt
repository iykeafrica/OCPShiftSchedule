package com.i_africa.shiftcalenderobajana.screens.viewmvc.calculate_ot

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

object ConversionUtil {
    private var decimalFormat: DecimalFormat = DecimalFormat("###,###,###.00")
    private const val SCALE = 3

    fun computeOTCalculation(
        basic: Int,
        maxMonthlyHours: Int,
        otMultiplier: Double,
        overtimeHours: Int
    ): String {

        val basicBD = BigDecimal.valueOf(basic.toDouble())
        val maxMonthlyHoursBD = BigDecimal.valueOf(maxMonthlyHours.toDouble())
        val otMultiplierBD = BigDecimal.valueOf(otMultiplier)
        val overtimeHoursBD = BigDecimal.valueOf(overtimeHours.toDouble())

//      overtime = ((basic / MONTHLY_HOURS) * OT_MULTIPLIER) * overtimeHours

        val basicDivideByMonthlyHours = basicBD.divide(maxMonthlyHoursBD, SCALE, RoundingMode.CEILING)
        val otMultiplierMultiplyByOvertimeHours = otMultiplierBD.multiply(overtimeHoursBD)
        val overtime = basicDivideByMonthlyHours.multiply(otMultiplierMultiplyByOvertimeHours)

        return (decimalFormat.format(overtime)).toString()
    }
}