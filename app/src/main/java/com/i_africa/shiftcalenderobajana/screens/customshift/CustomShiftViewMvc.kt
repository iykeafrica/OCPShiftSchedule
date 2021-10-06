package com.i_africa.shiftcalenderobajana.screens.customshift

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.CalendarCollection.setCalendarStartOfCollection
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.DateFormatter
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftMonthlyWorkDays.computeWorkingDays
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalculator.setFormula
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY_TEXT_COLOR
import com.i_africa.shiftcalenderobajana.utils.Constant.FOUR_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_4_4_4_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.LAST_THREE_SUBSTRING_2_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.LAST_THREE_SUBSTRING_4_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.THREE_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_3_3_3_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.TWO_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_2_2_2_DAYS
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalendarCellColor.updateShiftCalendarCellColor
import java.util.*


private val TAG = CustomShiftViewMvc::class.simpleName

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
        binding.weekdayView.text = DateFormatter.weekDay(calendar.time)
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

        val lastThreeCharacterInString = shift.substring(shift.length - 3)
        var shiftDuty = ""

        when(lastThreeCharacterInString){
            LAST_THREE_SUBSTRING_4_DAYS -> {
                val dateDifference = setFormula(day, month, year, SHIFT_4_4_4_DAYS)
                val collection = setCollection(shift, FOUR_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, FOUR_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_4_4_4_DAYS, FOUR_DAYS_LOOP)
            }
            LAST_THREE_SUBSTRING_2_DAYS -> {
                val dateDifference = setFormula(day, month, year, SHIFT_2_2_2_DAYS)
                val collection = setCollection(shift, TWO_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, TWO_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_2_2_2_DAYS, TWO_DAYS_LOOP)
            }
            else -> { //3 days
                val dateDifference = setFormula(day, month, year, SHIFT_3_3_3_DAYS)
                val collection = setCollection(shift, THREE_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, THREE_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_3_3_3_DAYS, THREE_DAYS_LOOP)
            }
        }

        binding.shiftView.text = shiftDuty
    }

    fun showShiftMonthlyWorkingDays(shift: String) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftMonthlyWorkingDays: $day/$month/$year")

        val count: Int = when(shift.substring(shift.length - 3)){
            LAST_THREE_SUBSTRING_4_DAYS -> {
                val collection = setCollection(shift, FOUR_DAYS_LOOP)
                computeWorkingDays(daysInMonth, month, year, collection, SHIFT_4_4_4_DAYS, FOUR_DAYS_LOOP)
            }
            LAST_THREE_SUBSTRING_2_DAYS -> {
                val collection = setCollection(shift, TWO_DAYS_LOOP)
                computeWorkingDays(daysInMonth, month, year, collection, SHIFT_2_2_2_DAYS, TWO_DAYS_LOOP)
            }
            else -> { //3 days
                val collection = setCollection(shift, THREE_DAYS_LOOP)
                computeWorkingDays(daysInMonth, month, year, collection, SHIFT_3_3_3_DAYS, THREE_DAYS_LOOP)
            }
        }

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
        hideFourthAndFifthRow()
        calendar.set(year, month, dayOfMonth)
        reverseTextAndBackgroundColor(binding)
        date = calendar.time
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        isCurrentDate = true
        currentDay = dayOfMonth
        updateCell()
    }

    fun showShift(shift: String) {
        binding.shift.text = shift
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
                        binding.one.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.two.text = setCalendarStartOfCollection(startFirstDay)[1]
                if (binding.two.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[1]) == currentDay)
                        binding.two.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.three.text = setCalendarStartOfCollection(startFirstDay)[2]
                if (binding.three.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[2]) == currentDay)
                        binding.three.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.four.text = setCalendarStartOfCollection(startFirstDay)[3]
                if (binding.four.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[3]) == currentDay)
                        binding.four.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.five.text = setCalendarStartOfCollection(startFirstDay)[4]
                if (binding.five.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[4]) == currentDay)
                        binding.five.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.six.text = setCalendarStartOfCollection(startFirstDay)[5]
                if (binding.six.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[5]) == currentDay)
                        binding.six.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                }

                binding.seven.text = setCalendarStartOfCollection(startFirstDay)[6]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[6]) == currentDay)
                    binding.seven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.eight.text = setCalendarStartOfCollection(startFirstDay)[7]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[7]) == currentDay)
                    binding.eight.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.nine.text = setCalendarStartOfCollection(startFirstDay)[8]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[8]) == currentDay)
                    binding.nine.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.ten.text = setCalendarStartOfCollection(startFirstDay)[9]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[9]) == currentDay)
                    binding.ten.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.eleven.text = setCalendarStartOfCollection(startFirstDay)[10]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[10]) == currentDay)
                    binding.eleven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twelve.text = setCalendarStartOfCollection(startFirstDay)[11]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[11]) == currentDay)
                    binding.twelve.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.thirteen.text = setCalendarStartOfCollection(startFirstDay)[12]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[12]) == currentDay)
                    binding.thirteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.fourteen.text = setCalendarStartOfCollection(startFirstDay)[13]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[13]) == currentDay)
                    binding.fourteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.fifteen.text = setCalendarStartOfCollection(startFirstDay)[14]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[14]) == currentDay)
                    binding.fifteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.sixteen.text = setCalendarStartOfCollection(startFirstDay)[15]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[15]) == currentDay)
                    binding.sixteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.seventeen.text = setCalendarStartOfCollection(startFirstDay)[16]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[16]) == currentDay)
                    binding.seventeen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.eighteen.text = setCalendarStartOfCollection(startFirstDay)[17]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[17]) == currentDay)
                    binding.eighteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.nineteen.text = setCalendarStartOfCollection(startFirstDay)[18]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[18]) == currentDay)
                    binding.nineteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twenty.text = setCalendarStartOfCollection(startFirstDay)[19]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[19]) == currentDay)
                    binding.twenty.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentyone.text = setCalendarStartOfCollection(startFirstDay)[20]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[20]) == currentDay)
                    binding.twentyone.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentytwo.text = setCalendarStartOfCollection(startFirstDay)[21]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[21]) == currentDay)
                    binding.twentytwo.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentythree.text = setCalendarStartOfCollection(startFirstDay)[22]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[22]) == currentDay)
                    binding.twentythree.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentyfour.text = setCalendarStartOfCollection(startFirstDay)[23]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[23]) == currentDay)
                    binding.twentyfour.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentyfive.text = setCalendarStartOfCollection(startFirstDay)[24]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[24]) == currentDay)
                    binding.twentyfive.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentysix.text = setCalendarStartOfCollection(startFirstDay)[25]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[25]) == currentDay)
                    binding.twentysix.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentyseven.text = setCalendarStartOfCollection(startFirstDay)[26]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[26]) == currentDay)
                    binding.twentyseven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))

                binding.twentyeight.text = setCalendarStartOfCollection(startFirstDay)[27]
                if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[27]) == currentDay)
                    binding.twentyeight.setTextColor(Color.parseColor(DAY_TEXT_COLOR))


                binding.twentynine.text = setCalendarStartOfCollection(startFirstDay)[28]
                if (binding.twentynine.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.twentynine.text.toString().trim()) > daysInMonth) {
                        binding.twentynine.text = ""
                    } else {
                        binding.fourthRow.visibility = View.VISIBLE
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[28]) == currentDay)
                            binding.twentynine.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirty.text = setCalendarStartOfCollection(startFirstDay)[29]
                if (binding.thirty.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirty.text.toString().trim()) > daysInMonth) {
                        binding.thirty.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[29]) == currentDay)
                            binding.thirty.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtyone.text = setCalendarStartOfCollection(startFirstDay)[30]
                if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtyone.text.toString().trim()) > daysInMonth) {
                        binding.thirtyone.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[30]) == currentDay)
                            binding.thirtyone.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtytwo.text = setCalendarStartOfCollection(startFirstDay)[31]
                if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtytwo.text.toString().trim()) > daysInMonth) {
                        binding.thirtytwo.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[31]) == currentDay)
                            binding.thirtytwo.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtythree.text = setCalendarStartOfCollection(startFirstDay)[32]
                if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtythree.text.toString().trim()) > daysInMonth) {
                        binding.thirtythree.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[32]) == currentDay)
                            binding.thirtythree.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtyfour.text = setCalendarStartOfCollection(startFirstDay)[33]
                if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtyfour.text.toString().trim()) > daysInMonth) {
                        binding.thirtyfour.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[33]) == currentDay)
                            binding.thirtyfour.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtyfive.text = setCalendarStartOfCollection(startFirstDay)[34]
                if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtyfive.text.toString().trim()) > daysInMonth) {
                        binding.thirtyfive.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[34]) == currentDay)
                            binding.thirtyfive.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtysix.text = setCalendarStartOfCollection(startFirstDay)[35]
                if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtysix.text.toString().trim()) > daysInMonth) {
                        binding.thirtysix.text = ""
                    } else {
                        binding.fifthRow.visibility = View.VISIBLE
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[35]) == currentDay)
                            binding.thirtysix.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
                }

                binding.thirtyseven.text = setCalendarStartOfCollection(startFirstDay)[36]
                if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
                    if (Integer.parseInt(binding.thirtyseven.text.toString().trim()) > daysInMonth) {
                        binding.thirtyseven.text = ""
                    } else {
                        if (Integer.parseInt(setCalendarStartOfCollection(startFirstDay)[36]) == currentDay)
                            binding.thirtyseven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
                    }
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
            if (binding.one.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.oneClick()
                }
            }
        }

        binding.two.setOnClickListener {
            if (binding.two.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.twoClick()
                }
            }
        }

        binding.three.setOnClickListener {
            if (binding.three.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.threeClick()
                }
            }
        }

        binding.four.setOnClickListener {
            if (binding.four.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.fourClick()
                }
            }
        }

        binding.five.setOnClickListener {
            if (binding.five.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.fiveClick()
                }
            }
        }

        binding.six.setOnClickListener {
            if (binding.six.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.sixClick()
                }
            }
        }

        binding.seven.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.sevenClick()
            }

        }

        binding.eight.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.eightClick()
            }
        }

        binding.nine.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.nineClick()
            }
        }

        binding.ten.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.tenClick()
            }
        }

        binding.eleven.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.elevenClick()
            }
        }

        binding.twelve.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twelveClick()
            }
        }

        binding.thirteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.thirteenClick()
            }
        }

        binding.fourteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.fourteenClick()
            }
        }

        binding.fifteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.fifteenClick()
            }
        }

        binding.sixteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.sixteenClick()
            }
        }

        binding.seventeen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.seventeenClick()
            }
        }

        binding.eighteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.eighteenClick()
            }
        }

        binding.nineteen.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.nineteenClick()
            }
        }

        binding.twenty.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyClick()
            }
        }

        binding.twenty.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyClick()
            }
        }

        binding.twentyone.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyOneClick()
            }
        }

        binding.twentytwo.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyTwoClick()
            }
        }

        binding.twentythree.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyThreeClick()
            }
        }

        binding.twentyfour.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyFourClick()
            }
        }

        binding.twentyfive.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyFiveClick()
            }
        }

        binding.twentysix.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentySixClick()
            }
        }

        binding.twentyseven.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentySevenClick()
            }
        }

        binding.twentyeight.setOnClickListener {
            reverseTextAndBackgroundColor(binding)
            for (listener in listeners) {
                listener.twentyEightClick()
            }
        }

        binding.twentynine.setOnClickListener {
            if (binding.twentynine.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.twentyNineClick()
                }
            }
        }

        binding.thirty.setOnClickListener {
            if (binding.thirty.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyClick()
                }
            }
        }

        binding.thirtyone.setOnClickListener {
            if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyOneClick()
                }
            }
        }

        binding.thirtytwo.setOnClickListener {
            if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyTwoClick()
                }
            }
        }

        binding.thirtythree.setOnClickListener {
            if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyThreeClick()
                }
            }
        }

        binding.thirtyfour.setOnClickListener {
            if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyFourClick()
                }
            }
        }

        binding.thirtyfive.setOnClickListener {
            if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtyClick()
                }
            }
        }

        binding.thirtysix.setOnClickListener {
            if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtySixClick()
                }
            }
        }

        binding.thirtyseven.setOnClickListener {
            if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
                reverseTextAndBackgroundColor(binding)
                for (listener in listeners) {
                    listener.thirtySevenClick()
                }
            }
        }
    }

    fun setNextMonth() {
        currentDay = 1
        reverseTextAndBackgroundColor(binding)
        calendar.add(Calendar.MONTH, +1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        date = calendar.time
        updateCell()
    }

    fun setPreviousMonth() {
        currentDay = 1
        reverseTextAndBackgroundColor(binding)
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        date = calendar.time
        updateCell()
    }

    fun set1() {
        if (binding.one.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.one.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.one.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set2() {
        if (binding.two.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.two.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.two.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set3() {
        if (binding.three.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.three.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.three.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set4() {
        if (binding.four.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.four.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.four.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set5() {
        if (binding.five.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.five.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.five.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set6() {
        if (binding.six.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.six.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.six.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set7() {
        currentDay = Integer.parseInt(binding.seven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.seven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set8() {
        currentDay = Integer.parseInt(binding.eight.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eight.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set9() {
        currentDay = Integer.parseInt(binding.nine.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.nine.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set10() {
        currentDay = Integer.parseInt(binding.ten.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.ten.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set11() {
        currentDay = Integer.parseInt(binding.eleven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eleven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set12() {
        currentDay = Integer.parseInt(binding.twelve.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twelve.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set13() {
        currentDay = Integer.parseInt(binding.thirteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.thirteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set14() {
        currentDay = Integer.parseInt(binding.fourteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.fourteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set15() {
        currentDay = Integer.parseInt(binding.fifteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.fifteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set16() {
        currentDay = Integer.parseInt(binding.sixteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.sixteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set17() {
        currentDay = Integer.parseInt(binding.seventeen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.seventeen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set18() {
        currentDay = Integer.parseInt(binding.eighteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eighteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set19() {
        currentDay = Integer.parseInt(binding.nineteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.nineteen.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set20() {
        currentDay = Integer.parseInt(binding.twenty.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twenty.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set21() {
        currentDay = Integer.parseInt(binding.twentyone.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyone.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set22() {
        currentDay = Integer.parseInt(binding.twentytwo.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentytwo.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set23() {
        currentDay = Integer.parseInt(binding.twentythree.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentythree.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set24() {
        currentDay = Integer.parseInt(binding.twentyfour.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyfour.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set25() {
        currentDay = Integer.parseInt(binding.twentyfive.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyfive.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set26() {
        currentDay = Integer.parseInt(binding.twentysix.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentysix.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set27() {
        currentDay = Integer.parseInt(binding.twentyseven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyseven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set28() {
        currentDay = Integer.parseInt(binding.twentyeight.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyeight.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
    }

    fun set29() {
        if (binding.twentynine.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.twentynine.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.twentynine.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set30() {
        if (binding.thirty.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirty.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirty.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set31() {
        if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyone.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyone.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set32() {
        if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtytwo.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtytwo.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set33() {
        if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtythree.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtythree.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set34() {
        if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyfour.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyfour.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set35() {
        if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyfive.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyfive.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set36() {
        if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtysix.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtysix.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
        }
    }

    fun set37() {
        if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyseven.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyseven.setTextColor(Color.parseColor(DAY_TEXT_COLOR))
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