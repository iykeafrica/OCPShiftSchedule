package com.i_africa.shiftcalenderobajana.screens.customshift

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.screens.customshift.CalendarCollection.setCalendarStartOfCollection
import com.i_africa.shiftcalenderobajana.screens.shift.utils.DateFormatter
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftMonthlyWorkDays.computeWorkingDays
import com.i_africa.shiftcalenderobajana.screens.shift.utils.ShiftUtil.setFormula
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import java.util.*

private const val TAG = "CustomShiftViewMvc"

class CustomShiftViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<CustomShiftViewMvc.Listener>() {

    private val binding = ActivityCustomShiftBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private var date = Date()
    private val calendar = Calendar.getInstance()
    private lateinit var day: String
    private lateinit var month: String
    private lateinit var year: String
    private var startFirstDay: Int = 0
    private var daysInMonth: Int = 0
    private var currentDay: Int = 1
    private var count = 0
    private var isCurrentDate = true

    init {
        hideFourthAndFifthRow()
        calendar.time = date
        currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        updateCell()

        binding.front.setOnClickListener {
            hideFourthAndFifthRow()
            for (listener in listeners) {
                listener.nextMonthClick()
            }
        }
        binding.back.setOnClickListener {
            hideFourthAndFifthRow()
            for (listener in listeners) {
                listener.previousMonthClick()
            }
        }

        cellClick()

        binding.menu.setOnClickListener { v ->
            for (listener in listeners) {
                listener.popUpMenuClick(v)
            }
        }
    }

    private fun hideFourthAndFifthRow() {
        binding.fourthRow.visibility = View.GONE
        binding.fifthRow.visibility = View.GONE
    }

    fun showDayOfMonth() {
        binding.dayOfMonthView.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun showWeekDay() {
        binding.weekdayView.text = DateFormatter.weekDay(date)
    }

    private fun setCalendar(cal: Calendar) {
        day = cal.get(Calendar.DAY_OF_MONTH).toString()
        month = (cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun showShiftDuty(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftDuty: $day/$month/$year")

        val shiftDuty = computeShiftDuty(setFormula(day, month, year), setCollection(shift))
        binding.shiftView.text = shiftDuty
    }

    fun showShiftMonthlyWorkingDays(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftMonthlyWorkingDays: $day/$month/$year")

        count = computeWorkingDays(daysInMonth, month, year, setCollection(shift))
        binding.workingDaysView.text = "${DateFormatter.month(date)} has $count working days."
    }

    fun getDay(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getMonth(): Int {
        return calendar.get(Calendar.MONTH)
    }

    fun getYear(): Int {
        return calendar.get(Calendar.YEAR)
    }

    fun restoreState(dayOfMonth: Int, month: Int, year: Int) {
        calendar.set(year, month, dayOfMonth)
        date = calendar.time
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        currentDay = dayOfMonth
        updateCell()
//        binding.calendarView.date = date.time
    }

    fun showShift(shift: String) {
        binding.shift.text = shift
    }

    fun reverseTextColor() {
        binding.one.setTextColor(Color.parseColor("#000000"))
        binding.two.setTextColor(Color.parseColor("#000000"))
        binding.three.setTextColor(Color.parseColor("#000000"))
        binding.four.setTextColor(Color.parseColor("#000000"))
        binding.five.setTextColor(Color.parseColor("#000000"))
        binding.six.setTextColor(Color.parseColor("#000000"))
        binding.seven.setTextColor(Color.parseColor("#000000"))
        binding.eight.setTextColor(Color.parseColor("#000000"))
        binding.nine.setTextColor(Color.parseColor("#000000"))
        binding.ten.setTextColor(Color.parseColor("#000000"))
        binding.eleven.setTextColor(Color.parseColor("#000000"))
        binding.twelve.setTextColor(Color.parseColor("#000000"))
        binding.thirteen.setTextColor(Color.parseColor("#000000"))
        binding.fourteen.setTextColor(Color.parseColor("#000000"))
        binding.fifteen.setTextColor(Color.parseColor("#000000"))
        binding.sixteen.setTextColor(Color.parseColor("#000000"))
        binding.seventeen.setTextColor(Color.parseColor("#000000"))
        binding.eighteen.setTextColor(Color.parseColor("#000000"))
        binding.nineteen.setTextColor(Color.parseColor("#000000"))
        binding.twenty.setTextColor(Color.parseColor("#000000"))
        binding.twentyone.setTextColor(Color.parseColor("#000000"))
        binding.twentytwo.setTextColor(Color.parseColor("#000000"))
        binding.twentythree.setTextColor(Color.parseColor("#000000"))
        binding.twentyfour.setTextColor(Color.parseColor("#000000"))
        binding.twentyfive.setTextColor(Color.parseColor("#000000"))
        binding.twentysix.setTextColor(Color.parseColor("#000000"))
        binding.twentyseven.setTextColor(Color.parseColor("#000000"))
        binding.twentyeight.setTextColor(Color.parseColor("#000000"))
        binding.twentynine.setTextColor(Color.parseColor("#000000"))
        binding.thirty.setTextColor(Color.parseColor("#000000"))
        binding.thirtyone.setTextColor(Color.parseColor("#000000"))
        binding.thirtytwo.setTextColor(Color.parseColor("#000000"))
        binding.thirtythree.setTextColor(Color.parseColor("#000000"))
        binding.thirtyfour.setTextColor(Color.parseColor("#000000"))
        binding.thirtyfive.setTextColor(Color.parseColor("#000000"))
        binding.thirtysix.setTextColor(Color.parseColor("#000000"))
        binding.thirtyseven.setTextColor(Color.parseColor("#000000"))
    }


    private fun updateCell() {
        setCalendar(calendar)
        calendar[Calendar.DAY_OF_MONTH] = 1
        startFirstDay = calendar[Calendar.DAY_OF_WEEK]
        binding.monthYearHeader.text = DateFormatter.monthYearHeader(date)

        for (i in 1..7) {
            if (startFirstDay == i) {

                binding.one.text = setCalendarStartOfCollection(startFirstDay)[0]
                if (binding.one.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[0]) == currentDay)
                        binding.one.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.two.text = setCalendarStartOfCollection(startFirstDay)[1]
                if (binding.two.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[1]) == currentDay)
                        binding.two.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.three.text = setCalendarStartOfCollection(startFirstDay)[2]
                if (binding.three.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[2]) == currentDay)
                        binding.three.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.four.text = setCalendarStartOfCollection(startFirstDay)[3]
                if (binding.four.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[3]) == currentDay)
                        binding.four.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.five.text = setCalendarStartOfCollection(startFirstDay)[4]
                if (binding.five.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[4]) == currentDay)
                        binding.five.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.six.text = setCalendarStartOfCollection(startFirstDay)[5]
                if (binding.six.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[5]) == currentDay)
                        binding.six.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.seven.text = setCalendarStartOfCollection(startFirstDay)[6]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[6]) == currentDay)
                    binding.seven.setTextColor(Color.parseColor("#FF6347"))

                binding.eight.text = setCalendarStartOfCollection(startFirstDay)[7]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[7]) == currentDay)
                    binding.eight.setTextColor(Color.parseColor("#FF6347"))

                binding.nine.text = setCalendarStartOfCollection(startFirstDay)[8]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[8]) == currentDay)
                    binding.nine.setTextColor(Color.parseColor("#FF6347"))

                binding.ten.text = setCalendarStartOfCollection(startFirstDay)[9]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[9]) == currentDay)
                    binding.ten.setTextColor(Color.parseColor("#FF6347"))

                binding.eleven.text = setCalendarStartOfCollection(startFirstDay)[10]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[10]) == currentDay)
                    binding.eleven.setTextColor(Color.parseColor("#FF6347"))

                binding.twelve.text = setCalendarStartOfCollection(startFirstDay)[11]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[11]) == currentDay)
                    binding.twelve.setTextColor(Color.parseColor("#FF6347"))

                binding.thirteen.text = setCalendarStartOfCollection(startFirstDay)[12]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[12]) == currentDay)
                    binding.thirteen.setTextColor(Color.parseColor("#FF6347"))

                binding.fourteen.text = setCalendarStartOfCollection(startFirstDay)[13]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[13]) == currentDay)
                    binding.fourteen.setTextColor(Color.parseColor("#FF6347"))

                binding.fifteen.text = setCalendarStartOfCollection(startFirstDay)[14]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[14]) == currentDay)
                    binding.fifteen.setTextColor(Color.parseColor("#FF6347"))

                binding.sixteen.text = setCalendarStartOfCollection(startFirstDay)[15]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[15]) == currentDay)
                    binding.sixteen.setTextColor(Color.parseColor("#FF6347"))

                binding.seventeen.text = setCalendarStartOfCollection(startFirstDay)[16]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[16]) == currentDay)
                    binding.seventeen.setTextColor(Color.parseColor("#FF6347"))

                binding.eighteen.text = setCalendarStartOfCollection(startFirstDay)[17]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[17]) == currentDay)
                    binding.eighteen.setTextColor(Color.parseColor("#FF6347"))

                binding.nineteen.text = setCalendarStartOfCollection(startFirstDay)[18]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[18]) == currentDay)
                    binding.nineteen.setTextColor(Color.parseColor("#FF6347"))

                binding.twenty.text = setCalendarStartOfCollection(startFirstDay)[19]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[19]) == currentDay)
                    binding.twenty.setTextColor(Color.parseColor("#FF6347"))

                binding.twentyone.text = setCalendarStartOfCollection(startFirstDay)[20]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[20]) == currentDay)
                    binding.twentyone.setTextColor(Color.parseColor("#FF6347"))

                binding.twentytwo.text = setCalendarStartOfCollection(startFirstDay)[21]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[21]) == currentDay)
                    binding.twentytwo.setTextColor(Color.parseColor("#FF6347"))

                binding.twentythree.text = setCalendarStartOfCollection(startFirstDay)[22]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[22]) == currentDay)
                    binding.twentythree.setTextColor(Color.parseColor("#FF6347"))

                binding.twentyfour.text = setCalendarStartOfCollection(startFirstDay)[23]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[23]) == currentDay)
                    binding.twentyfour.setTextColor(Color.parseColor("#FF6347"))

                binding.twentyfive.text = setCalendarStartOfCollection(startFirstDay)[24]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[24]) == currentDay)
                    binding.twentyfive.setTextColor(Color.parseColor("#FF6347"))

                binding.twentysix.text = setCalendarStartOfCollection(startFirstDay)[25]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[25]) == currentDay)
                    binding.twentysix.setTextColor(Color.parseColor("#FF6347"))

                binding.twentyseven.text = setCalendarStartOfCollection(startFirstDay)[26]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[26]) == currentDay)
                    binding.twentyseven.setTextColor(Color.parseColor("#FF6347"))

                binding.twentyeight.text = setCalendarStartOfCollection(startFirstDay)[27]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[27]) == currentDay)
                    binding.twentyeight.setTextColor(Color.parseColor("#FF6347"))


                binding.twentynine.text = setCalendarStartOfCollection(startFirstDay)[28]
                if (binding.twentynine.text.toString().trim().isNotEmpty()) {
                    binding.fourthRow.visibility = View.VISIBLE
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[28]) == currentDay)
                        binding.twentynine.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirty.text = setCalendarStartOfCollection(startFirstDay)[29]
                if (binding.thirty.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[29]) == currentDay)
                        binding.thirty.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtyone.text = setCalendarStartOfCollection(startFirstDay)[30]
                if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[30]) == currentDay)
                        binding.thirtyone.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtytwo.text = setCalendarStartOfCollection(startFirstDay)[31]
                if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[31]) == currentDay)
                        binding.thirtytwo.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtythree.text = setCalendarStartOfCollection(startFirstDay)[32]
                if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[32]) == currentDay)
                        binding.thirtythree.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtyfour.text = setCalendarStartOfCollection(startFirstDay)[33]
                if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[33]) == currentDay)
                        binding.thirtyfour.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtyfive.text = setCalendarStartOfCollection(startFirstDay)[34]
                if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[34]) == currentDay)
                        binding.thirtyfive.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtysix.text = setCalendarStartOfCollection(startFirstDay)[35]
                if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
                    binding.fifthRow.visibility = View.VISIBLE
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[35]) == currentDay)
                        binding.thirtysix.setTextColor(Color.parseColor("#FF6347"))
                }

                binding.thirtyseven.text = setCalendarStartOfCollection(startFirstDay)[36]
                if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[36]) == currentDay)
                        binding.thirtyseven.setTextColor(Color.parseColor("#FF6347"))
                }
            }
        }

        if (isCurrentDate) {
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            isCurrentDate = false
        }

    }


    private fun cellClick() {
        binding.one.setOnClickListener {
            if (binding.one.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.oneClick()
                }
            }
        }

        binding.two.setOnClickListener {
            if (binding.two.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.twoClick()
                }
            }
        }

        binding.three.setOnClickListener {
            if (binding.three.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.threeClick()
                }
            }
        }

        binding.four.setOnClickListener {
            if (binding.four.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.fourClick()
                }
            }
        }

        binding.five.setOnClickListener {
            if (binding.five.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.fiveClick()
                }
            }
        }

        binding.six.setOnClickListener {
            if (binding.six.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.sixClick()
                }
            }
        }

        binding.seven.setOnClickListener {
            for (listener in listeners) {
                listener.sevenClick()
            }

        }

        binding.eight.setOnClickListener {
            for (listener in listeners) {
                listener.eightClick()
            }
        }

        binding.nine.setOnClickListener {
            for (listener in listeners) {
                listener.nineClick()
            }
        }

        binding.ten.setOnClickListener {
            for (listener in listeners) {
                listener.tenClick()
            }
        }

        binding.eleven.setOnClickListener {
            for (listener in listeners) {
                listener.elevenClick()
            }
        }

        binding.twelve.setOnClickListener {
            for (listener in listeners) {
                listener.twelveClick()
            }
        }

        binding.thirteen.setOnClickListener {
            for (listener in listeners) {
                listener.thirteenClick()
            }
        }

        binding.fourteen.setOnClickListener {
            for (listener in listeners) {
                listener.fourteenClick()
            }
        }

        binding.fifteen.setOnClickListener {
            for (listener in listeners) {
                listener.fifteenClick()
            }
        }

        binding.sixteen.setOnClickListener {
            for (listener in listeners) {
                listener.sixteenClick()
            }
        }

        binding.seventeen.setOnClickListener {
            for (listener in listeners) {
                listener.seventeenClick()
            }
        }

        binding.eighteen.setOnClickListener {
            for (listener in listeners) {
                listener.eighteenClick()
            }
        }

        binding.nineteen.setOnClickListener {
            for (listener in listeners) {
                listener.nineteenClick()
            }
        }

        binding.twenty.setOnClickListener {
            for (listener in listeners) {
                listener.twentyClick()
            }
        }

        binding.twenty.setOnClickListener {
            for (listener in listeners) {
                listener.twentyClick()
            }
        }

        binding.twentyone.setOnClickListener {
            for (listener in listeners) {
                listener.twentyOneClick()
            }
        }

        binding.twentytwo.setOnClickListener {
            for (listener in listeners) {
                listener.twentyTwoClick()
            }
        }

        binding.twentythree.setOnClickListener {
            for (listener in listeners) {
                listener.twentyThreeClick()
            }
        }

        binding.twentyfour.setOnClickListener {
            for (listener in listeners) {
                listener.twentyFourClick()
            }
        }

        binding.twentyfive.setOnClickListener {
            for (listener in listeners) {
                listener.twentyFiveClick()
            }
        }

        binding.twentysix.setOnClickListener {
            for (listener in listeners) {
                listener.twentySixClick()
            }
        }

        binding.twentyseven.setOnClickListener {
            for (listener in listeners) {
                listener.twentySevenClick()
            }
        }

        binding.twentyeight.setOnClickListener {
            for (listener in listeners) {
                listener.twentyEightClick()
            }
        }

        binding.twentynine.setOnClickListener {
            if (binding.twentynine.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.twentyNineClick()
                }
            }
        }

        binding.thirty.setOnClickListener {
            if (binding.thirty.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyClick()
                }
            }
        }

        binding.thirtyone.setOnClickListener {
            if (binding.thirtyone.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyOneClick()
                }
            }
        }

        binding.thirtytwo.setOnClickListener {
            if (binding.thirtytwo.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyTwoClick()
                }
            }
        }

        binding.thirtythree.setOnClickListener {
            if (binding.thirtythree.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyThreeClick()
                }
            }
        }

        binding.thirtyfour.setOnClickListener {
            if (binding.thirtyfour.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyFourClick()
                }
            }
        }

        binding.thirtyfive.setOnClickListener {
            if (binding.thirtyfive.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtyClick()
                }
            }
        }

        binding.thirtysix.setOnClickListener {
            if (binding.thirtysix.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtySixClick()
                }
            }
        }

        binding.thirtyseven.setOnClickListener {
            if (binding.thirtyseven.text.toString().isNotEmpty()) {
                for (listener in listeners) {
                    listener.thirtySevenClick()
                }
            }
        }
    }

    fun setNextMonth() {
        currentDay = 1
        reverseTextColor()
        calendar.add(Calendar.MONTH, +1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        date = calendar.time
        updateCell()
    }

    fun setPreviousMonth() {
        currentDay = 1
        reverseTextColor()
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        date = calendar.time
        updateCell()
    }

    fun set1() {
        if (binding.one.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.one.text.toString()))
        }
    }

    fun set2() {
        if (binding.two.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.two.text.toString()))
        }
    }

    fun set3() {
        if (binding.three.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.three.text.toString()))
        }
    }

    fun set4() {
        if (binding.four.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.four.text.toString()))
        }
    }

    fun set5() {
        if (binding.five.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.five.text.toString()))
        }
    }

    fun set6() {
        if (binding.six.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.six.text.toString()))
        }
    }

    fun set7() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.seven.text.toString()))
    }

    fun set8() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.eight.text.toString()))
    }

    fun set9() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.nine.text.toString()))
    }

    fun set10() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.ten.text.toString()))
    }

    fun set11() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.eleven.text.toString()))
    }

    fun set12() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twelve.text.toString()))
    }

    fun set13() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.thirteen.text.toString()))
    }

    fun set14() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.fourteen.text.toString()))
    }

    fun set15() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.fifteen.text.toString()))
    }

    fun set16() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.sixteen.text.toString()))
    }

    fun set17() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.seventeen.text.toString()))
    }

    fun set18() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.eighteen.text.toString()))
    }

    fun set19() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.nineteen.text.toString()))
    }

    fun set20() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twenty.text.toString()))
    }

    fun set21() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentyone.text.toString()))
    }

    fun set22() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentytwo.text.toString()))
    }

    fun set23() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentythree.text.toString()))
    }

    fun set24() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentyfour.text.toString()))
    }

    fun set25() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentyfive.text.toString()))
    }

    fun set26() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentysix.text.toString()))
    }

    fun set27() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentyseven.text.toString()))
    }

    fun set28() {
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.twentyeight.text.toString()))
    }

    fun set29() {
        if (binding.twentynine.text.toString().isNotEmpty()) {
            calendar.set(
                Calendar.DAY_OF_MONTH,
                Integer.parseInt(binding.twentynine.text.toString())
            )
        }
    }

    fun set30() {
        if (binding.thirty.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.thirty.text.toString()))
        }
    }

    fun set31() {
        if (binding.thirtyone.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.thirtyone.text.toString()))
        }
    }

    fun set32() {
        if (binding.thirtytwo.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.thirtytwo.text.toString()))
        }
    }

    fun set33() {
        if (binding.thirtythree.text.toString().isNotEmpty()) {
            calendar.set(
                Calendar.DAY_OF_MONTH,
                Integer.parseInt(binding.thirtythree.text.toString())
            )
        }
    }

    fun set34() {
        if (binding.thirtyfour.text.toString().isNotEmpty()) {
            calendar.set(
                Calendar.DAY_OF_MONTH,
                Integer.parseInt(binding.thirtyfour.text.toString())
            )
        }
    }

    fun set35() {
        if (binding.thirtyfive.text.toString().isNotEmpty()) {
            calendar.set(
                Calendar.DAY_OF_MONTH,
                Integer.parseInt(binding.thirtyfive.text.toString())
            )
        }
    }

    fun set36() {
        if (binding.thirtysix.text.toString().isNotEmpty()) {
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(binding.thirtysix.text.toString()))
        }
    }

    fun set37() {
        if (binding.thirtyseven.text.toString().isNotEmpty()) {
            calendar.set(
                Calendar.DAY_OF_MONTH,
                Integer.parseInt(binding.thirtyseven.text.toString())
            )
        }
    }


    interface Listener {
        fun nextMonthClick()
        fun previousMonthClick()
        fun popUpMenuClick(v: View)

        //Date click
        fun oneClick()
        fun twoClick()
        fun threeClick()
        fun fourClick()
        fun fiveClick()
        fun sixClick()
        fun sevenClick()
        fun eightClick()
        fun nineClick()
        fun tenClick()
        fun elevenClick()
        fun twelveClick()
        fun thirteenClick()
        fun fourteenClick()
        fun fifteenClick()
        fun sixteenClick()
        fun seventeenClick()
        fun eighteenClick()
        fun nineteenClick()
        fun twentyClick()
        fun twentyOneClick()
        fun twentyTwoClick()
        fun twentyThreeClick()
        fun twentyFourClick()
        fun twentyFiveClick()
        fun twentySixClick()
        fun twentySevenClick()
        fun twentyEightClick()
        fun twentyNineClick()
        fun thirtyClick()
        fun thirtyOneClick()
        fun thirtyTwoClick()
        fun thirtyThreeClick()
        fun thirtyFourClick()
        fun thirtyFiveClick()
        fun thirtySixClick()
        fun thirtySevenClick()
    }


}