package com.i_africa.shiftcalenderobajana.screens.customshift

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivityCustomShiftBinding
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.CalendarCollection.setCalendarStartOfCollection
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.DateFormatter
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCollection.setCollection
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftDuty.computeShiftDuty
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftMonthlyWorkDays.computeWorkingDays
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalculator.setFormula
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.Constant.DATE_TEXT_COLOR_STRING_KEY
import com.i_africa.shiftcalenderobajana.utils.Constant.DAY_TEXT_COLOR
import com.i_africa.shiftcalenderobajana.utils.Constant.FOUR_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_4_4_4_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.LAST_THREE_SUBSTRING_2_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.LAST_THREE_SUBSTRING_4_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.THREE_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_3_3_3_DAYS
import com.i_africa.shiftcalenderobajana.utils.Constant.TWO_DAYS_LOOP
import com.i_africa.shiftcalenderobajana.utils.Constant.SHIFT_2_2_2_DAYS
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ConvertToIntegerResource.convertColorIntToResources
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.HighlightCurrentDay.checkCurrentDayAndHighlight
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.OnClickAndOnLongClick
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ReverseTextAndBackgroundColor
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.ShiftCalendarCellColor.updateShiftCalendarCellColor
import java.util.*


private val TAG = CustomShiftViewMvc::class.simpleName

class CustomShiftViewMvc(
    val activity: Activity,
    parent: ViewGroup?
) : BaseViewMvc<CustomShiftViewMvc.Listener>() {
    private val binding = ActivityCustomShiftBinding.inflate(activity.layoutInflater, parent, false)
    val rootView = binding.root

    private
    var date = Date()
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

        binding.front.setOnClickListener { //next month
            hideFourthAndFifthRow()
            for (listener in listeners) {
                listener.nextMonthClick()
            }
        }
        binding.back.setOnClickListener { //previous month
            hideFourthAndFifthRow()
            for (listener in listeners) {
                listener.previousMonthClick()
            }
        }

        cellClick()
        cellLongClick()

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

    fun showDayOfMonth(mySharedPreferences: MySharedPreferences) {
        binding.dayOfMonthView.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        val dayOfMonthTextColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        binding.dayOfMonthView.setTextColor(Color.parseColor(dayOfMonthTextColor))
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

    fun showShiftDuty(shift: String, mySharedPreferences: MySharedPreferences) {
        setCalendar(calendar)
        Log.d(TAG, "getShiftDuty: $day/$month/$year")

        val lastThreeCharacterInString = shift.substring(shift.length - 3)
        var shiftDuty = ""

        when(lastThreeCharacterInString){
            LAST_THREE_SUBSTRING_4_DAYS -> {
                val dateDifference = setFormula(day, month, year, SHIFT_4_4_4_DAYS)
                val collection = setCollection(shift, FOUR_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, FOUR_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_4_4_4_DAYS, FOUR_DAYS_LOOP, mySharedPreferences)
            }
            LAST_THREE_SUBSTRING_2_DAYS -> {
                val dateDifference = setFormula(day, month, year, SHIFT_2_2_2_DAYS)
                val collection = setCollection(shift, TWO_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, TWO_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_2_2_2_DAYS, TWO_DAYS_LOOP, mySharedPreferences)
            }
            else -> { //3 days
                val dateDifference = setFormula(day, month, year, SHIFT_3_3_3_DAYS)
                val collection = setCollection(shift, THREE_DAYS_LOOP)
                shiftDuty = computeShiftDuty(dateDifference, collection, THREE_DAYS_LOOP)
                updateShiftCalendarCellColor(binding, month, year, collection, SHIFT_3_3_3_DAYS, THREE_DAYS_LOOP, mySharedPreferences)
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

    fun makeNotificationIconInvisible() {
        binding.notification?.visibility = View.GONE
    }

    fun makeNotificationIconVisible(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredInt(Constant.DATE_TEXT_COLOR_RESOURCE_KEY)
        binding.notification?.setColorFilter(activity.resources.getColor(convertColorIntToResources(dayOfMonthColor)))
        binding.notification?.visibility = View.VISIBLE
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
                binding.two.text = setCalendarStartOfCollection(startFirstDay)[1]
                binding.three.text = setCalendarStartOfCollection(startFirstDay)[2]
                binding.four.text = setCalendarStartOfCollection(startFirstDay)[3]
                binding.five.text = setCalendarStartOfCollection(startFirstDay)[4]
                binding.six.text = setCalendarStartOfCollection(startFirstDay)[5]
                binding.seven.text = setCalendarStartOfCollection(startFirstDay)[6]
                binding.eight.text = setCalendarStartOfCollection(startFirstDay)[7]
                binding.nine.text = setCalendarStartOfCollection(startFirstDay)[8]
                binding.ten.text = setCalendarStartOfCollection(startFirstDay)[9]
                binding.eleven.text = setCalendarStartOfCollection(startFirstDay)[10]
                binding.twelve.text = setCalendarStartOfCollection(startFirstDay)[11]
                binding.thirteen.text = setCalendarStartOfCollection(startFirstDay)[12]
                binding.fourteen.text = setCalendarStartOfCollection(startFirstDay)[13]
                binding.fifteen.text = setCalendarStartOfCollection(startFirstDay)[14]
                binding.sixteen.text = setCalendarStartOfCollection(startFirstDay)[15]
                binding.seventeen.text = setCalendarStartOfCollection(startFirstDay)[16]
                binding.eighteen.text = setCalendarStartOfCollection(startFirstDay)[17]
                binding.nineteen.text = setCalendarStartOfCollection(startFirstDay)[18]
                binding.twenty.text = setCalendarStartOfCollection(startFirstDay)[19]
                binding.twentyone.text = setCalendarStartOfCollection(startFirstDay)[20]
                binding.twentytwo.text = setCalendarStartOfCollection(startFirstDay)[21]
                binding.twentythree.text = setCalendarStartOfCollection(startFirstDay)[22]
                binding.twentyfour.text = setCalendarStartOfCollection(startFirstDay)[23]
                binding.twentyfive.text = setCalendarStartOfCollection(startFirstDay)[24]
                binding.twentysix.text = setCalendarStartOfCollection(startFirstDay)[25]
                binding.twentyseven.text = setCalendarStartOfCollection(startFirstDay)[26]
                binding.twentyeight.text = setCalendarStartOfCollection(startFirstDay)[27]
                binding.twentynine.text = setCalendarStartOfCollection(startFirstDay)[28]
                binding.thirty.text = setCalendarStartOfCollection(startFirstDay)[29]
                binding.thirtyone.text = setCalendarStartOfCollection(startFirstDay)[30]
                binding.thirtytwo.text = setCalendarStartOfCollection(startFirstDay)[31]
                binding.thirtythree.text = setCalendarStartOfCollection(startFirstDay)[32]
                binding.thirtyfour.text = setCalendarStartOfCollection(startFirstDay)[33]
                binding.thirtyfive.text = setCalendarStartOfCollection(startFirstDay)[34]
                binding.thirtysix.text = setCalendarStartOfCollection(startFirstDay)[35]
                binding.thirtyseven.text = setCalendarStartOfCollection(startFirstDay)[36]
            }
        }

        if (isCurrentDate) {
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            isCurrentDate = false
        }

    }

    fun checkCellWithCurrentDate(mySharedPreferences: MySharedPreferences) {
        checkCurrentDayAndHighlight(mySharedPreferences, binding, currentDay, daysInMonth)
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

    fun set1(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.one.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.one.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.one.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set2(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.two.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.two.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.two.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set3(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.three.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.three.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.three.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set4(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.four.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.four.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.four.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set5(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.five.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.five.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.five.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set6(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.six.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.six.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.six.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set7(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.seven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.seven.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set8(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.eight.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eight.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set9(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.nine.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.nine.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set10(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.ten.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.ten.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set11(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.eleven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eleven.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set12(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twelve.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twelve.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set13(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.thirteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.thirteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set14(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.fourteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.fourteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set15(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.fifteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.fifteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set16(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.sixteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.sixteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set17(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.seventeen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.seventeen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set18(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.eighteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.eighteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set19(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.nineteen.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.nineteen.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set20(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twenty.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twenty.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set21(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentyone.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyone.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set22(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentytwo.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentytwo.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set23(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentythree.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentythree.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set24(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentyfour.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyfour.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set25(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentyfive.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyfive.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set26(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentysix.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentysix.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set27(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentyseven.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyseven.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set28(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        currentDay = Integer.parseInt(binding.twentyeight.text.toString())
        calendar.set(Calendar.DAY_OF_MONTH, currentDay)
        binding.twentyeight.setTextColor(Color.parseColor(dayOfMonthColor))
    }

    fun set29(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.twentynine.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.twentynine.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.twentynine.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set30(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirty.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirty.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirty.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set31(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyone.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyone.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set32(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtytwo.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtytwo.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set33(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtythree.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtythree.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set34(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyfour.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyfour.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set35(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyfive.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyfive.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set36(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtysix.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtysix.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }

    fun set37(mySharedPreferences: MySharedPreferences) {
        val dayOfMonthColor = mySharedPreferences.getStoredString(DATE_TEXT_COLOR_STRING_KEY)
        if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
            currentDay = Integer.parseInt(binding.thirtyseven.text.toString())
            calendar.set(Calendar.DAY_OF_MONTH, currentDay)
            binding.thirtyseven.setTextColor(Color.parseColor(dayOfMonthColor))
        }
    }




    private fun cellClick() {
        binding.one.setOnClickListener {
            if (binding.one.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.oneClick()
                }
            }
        }

        binding.two.setOnClickListener {
            if (binding.two.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.twoClick()
                }
            }
        }

        binding.three.setOnClickListener {
            if (binding.three.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.threeClick()
                }
            }
        }

        binding.four.setOnClickListener {
            if (binding.four.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.fourClick()
                }
            }
        }

        binding.five.setOnClickListener {
            if (binding.five.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.fiveClick()
                }
            }
        }

        binding.six.setOnClickListener {
            if (binding.six.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.sixClick()
                }
            }
        }

        binding.seven.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.sevenClick()
            }

        }

        binding.eight.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.eightClick()
            }
        }

        binding.nine.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.nineClick()
            }
        }

        binding.ten.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.tenClick()
            }
        }

        binding.eleven.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.elevenClick()
            }
        }

        binding.twelve.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twelveClick()
            }
        }

        binding.thirteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.thirteenClick()
            }
        }

        binding.fourteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.fourteenClick()
            }
        }

        binding.fifteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.fifteenClick()
            }
        }

        binding.sixteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.sixteenClick()
            }
        }

        binding.seventeen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.seventeenClick()
            }
        }

        binding.eighteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.eighteenClick()
            }
        }

        binding.nineteen.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.nineteenClick()
            }
        }

        binding.twenty.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyClick()
            }
        }

        binding.twentyone.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyOneClick()
            }
        }

        binding.twentytwo.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyTwoClick()
            }
        }

        binding.twentythree.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyThreeClick()
            }
        }

        binding.twentyfour.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyFourClick()
            }
        }

        binding.twentyfive.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyFiveClick()
            }
        }

        binding.twentysix.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentySixClick()
            }
        }

        binding.twentyseven.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentySevenClick()
            }
        }

        binding.twentyeight.setOnClickListener {
            ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.twentyEightClick()
            }
        }

        binding.twentynine.setOnClickListener {
            if (binding.twentynine.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.twentyNineClick()
                }
            }
        }

        binding.thirty.setOnClickListener {
            if (binding.thirty.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyClick()
                }
            }
        }

        binding.thirtyone.setOnClickListener {
            if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyOneClick()
                }
            }
        }

        binding.thirtytwo.setOnClickListener {
            if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyTwoClick()
                }
            }
        }

        binding.thirtythree.setOnClickListener {
            if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyThreeClick()
                }
            }
        }

        binding.thirtyfour.setOnClickListener {
            if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyFourClick()
                }
            }
        }

        binding.thirtyfive.setOnClickListener {
            if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtyFiveClick()
                }
            }
        }

        binding.thirtysix.setOnClickListener {
            if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtySixClick()
                }
            }
        }

        binding.thirtyseven.setOnClickListener {
            if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.thirtySevenClick()
                }
            }
        }
    }

    private fun cellLongClick() {
        binding.one.setOnLongClickListener {
            val returnValue: Boolean = if (binding.one.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.two.setOnLongClickListener {
            val returnValue: Boolean = if (binding.two.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.three.setOnLongClickListener {
            val returnValue: Boolean = if (binding.three.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.four.setOnLongClickListener {
            val returnValue: Boolean = if (binding.four.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.five.setOnLongClickListener {
            val returnValue: Boolean = if (binding.five.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.six.setOnLongClickListener {
            val returnValue: Boolean = if (binding.six.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.seven.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.eight.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.nine.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.ten.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.eleven.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twelve.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.thirteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.fourteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.fifteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.sixteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.seventeen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.eighteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.nineteen.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twenty.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentyone.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentytwo.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentythree.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentyfour.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentyfive.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentysix.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentyseven.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentyeight.setOnLongClickListener {
            for (listener in OnClickAndOnLongClick.listeners) {
                listener.popUpMenuClick2(it)
            }
            true
        }

        binding.twentynine.setOnLongClickListener {
            val returnValue: Boolean = if (binding.twentynine.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirty.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirty.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtyone.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtyone.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtytwo.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtytwo.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtythree.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtythree.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtyfour.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtyfour.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtyfive.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtyfive.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtysix.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtysix.text.toString().trim().isNotEmpty()) {
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }

        binding.thirtyseven.setOnLongClickListener {
            val returnValue: Boolean = if (binding.thirtyseven.text.toString().trim().isNotEmpty()) {
                ReverseTextAndBackgroundColor.reverseTextAndBackgroundColor(binding)
                for (listener in OnClickAndOnLongClick.listeners) {
                    listener.popUpMenuClick2(it)
                }
                true
            } else false
            returnValue
        }
    }

    interface Listener {
        fun nextMonthClick()
        fun previousMonthClick()
        fun popUpMenuClick(v: View)
        fun popUpMenuClick2(v: View)

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