package com.i_africa.shiftcalenderobajana.screens.settings

import android.app.Activity
import android.content.Intent
import android.graphics.ColorFilter
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.i_africa.shiftcalenderobajana.BuildConfig
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.databinding.ActivitySettingsBinding
import com.i_africa.shiftcalenderobajana.screens.selectshiftall.SelectShiftAllActivity
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.utils.shift_calendar.DateFormatter
import java.text.SimpleDateFormat
import java.util.*

class SettingsViewMvc(
    val activity: Activity,
    parent: ViewGroup?
) : BaseViewMvc<SettingsViewMvc.Listener>() {

    private var day = 0
    private var night = 0
    private var off = 0

    interface Listener {
        fun backPressed()
        fun colorPickerDropDown()
        fun changeDayColor()
        fun changeNightColor()
        fun changeOffColor()
        fun changeDefaultColor()
        fun saveColor()
        fun licenseAgreementClick()
        fun termsOfUseDropDown()
        fun contactUsClick()
    }

    private val binding = ActivitySettingsBinding.inflate(activity.layoutInflater, parent, false)
    val rootView = binding.root


    init {
        binding.version.text = BuildConfig.VERSION_NAME
        binding.catalogDate.text = DateFormatter.dateCatalog(Calendar.getInstance())

        binding.colorPicker.visibility = View.GONE
        binding.save.isEnabled = false

        binding.backButton.setOnClickListener {
            for (listener in listeners) {
                listener.backPressed()
            }
        }

        binding.colorPickerDropDownArrow.setOnClickListener {
            for (listener in listeners) {
                listener.colorPickerDropDown()
            }
        }

        binding.changeColorDay.setOnClickListener {
            for (listener in listeners) {
                listener.changeDayColor()
            }
        }

        binding.changeColorNight.setOnClickListener {
            for (listener in listeners) {
                listener.changeNightColor()
            }
        }

        binding.changeColorOff.setOnClickListener {
            for (listener in listeners) {
                listener.changeOffColor()
            }
        }

        binding.changeColorOff.setOnClickListener {
            for (listener in listeners) {
                listener.changeOffColor()
            }
        }

        binding.restoreDefaultSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            for (listener in listeners) {
                if (buttonView.isShown) {
                    if (buttonView.isChecked){
                        listener.changeDefaultColor()
                    }
                }
            }
        }

        binding.save.setOnClickListener {
            for (listener in listeners) {
                listener.saveColor()
            }
        }

        binding.license.setOnClickListener {
            for (listener in listeners) {
                listener.licenseAgreementClick()
            }
        }

        binding.termsOfUseDownArrow.setOnClickListener {
            for (listener in listeners) {
                listener.termsOfUseDropDown()
            }
        }

        binding.contact.setOnClickListener {
            for (listener in listeners) {
                listener.contactUsClick()
            }
        }

    }

    private fun resetButtonAlpha() {
        binding.changeColorDay.alpha = 1F
        binding.changeColorNight.alpha = 1F
        binding.changeColorOff.alpha = 1F
    }

    private fun hideAllCheckColor() {
        binding.colorCheck1.visibility = View.GONE
        binding.colorCheck2.visibility = View.GONE
        binding.colorCheck3.visibility = View.GONE
        binding.colorCheck4.visibility = View.GONE
        binding.colorCheck5.visibility = View.GONE
        binding.colorCheck6.visibility = View.GONE
        binding.colorCheck7.visibility = View.GONE
        binding.colorCheck8.visibility = View.GONE
        binding.colorCheck9.visibility = View.GONE
        binding.colorCheck10.visibility = View.GONE
        binding.colorCheck11.visibility = View.GONE
        binding.colorCheck12.visibility = View.GONE
    }

    private fun disableColors() {
        binding.color1.isEnabled = false
        binding.color2.isEnabled = false
        binding.color3.isEnabled = false
        binding.color4.isEnabled = false
        binding.color5.isEnabled = false
        binding.color6.isEnabled = false
        binding.color7.isEnabled = false
        binding.color8.isEnabled = false
        binding.color9.isEnabled = false
        binding.color10.isEnabled = false
        binding.color11.isEnabled = false
        binding.color12.isEnabled = false
    }

    private fun enableColors() {
        binding.color1.isEnabled = true
        binding.color2.isEnabled = true
        binding.color3.isEnabled = true
        binding.color4.isEnabled = true
        binding.color5.isEnabled = true
        binding.color6.isEnabled = true
        binding.color7.isEnabled = true
        binding.color8.isEnabled = true
        binding.color9.isEnabled = true
        binding.color10.isEnabled = true
        binding.color11.isEnabled = true
        binding.color12.isEnabled = true
    }

    fun colorPickerDropDown() {
        if (!binding.colorPicker.isVisible)
            binding.colorPicker.visibility = View.VISIBLE
        else
            binding.colorPicker.visibility = View.GONE
    }

    fun dayCellColor(): Int {
        return day
    }
    fun nightCellColor(): Int {
        return night
    }
    fun offCellColor(): Int {
        return off
    }


    fun changeDayColor() {
        hideAllCheckColor()
        resetButtonAlpha()
        binding.changeColorDay.alpha = 0.6F
        changeDayColorCell()
    }

    fun changeNightColor() {
        hideAllCheckColor()
        resetButtonAlpha()
        binding.changeColorNight.alpha = 0.6F
        changeNightColorCell()
    }

    fun changeOffColor() {
        hideAllCheckColor()
        resetButtonAlpha()
        binding.changeColorOff.alpha = 0.6F
        changeOffColorCell()
    }

    fun restoreDefaultCellBackgroundColor(dayColorResource: Int, nightColorResource: Int, offColorResource: Int) {
        day = dayColorResource
        night = nightColorResource
        off = offColorResource
        binding.colorDay.setColorFilter(activity.resources.getColor(convertColorIntToResources(dayColorResource)))
        binding.colorNight.setColorFilter(activity.resources.getColor(convertColorIntToResources(nightColorResource)))
        binding.colorOff.setColorFilter(activity.resources.getColor(convertColorIntToResources(offColorResource)))
        Handler().postDelayed(Runnable {binding.restoreDefaultSwitch.isChecked = false}, 2000)
    }

    private fun customToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun saveColor() {
        resetButtonAlpha()
        hideAllCheckColor()
        binding.save.isEnabled = false
        disableColors()
        customToast("Background cell color saved")
    }

    fun termsOfUseDropDown() {
        if (!binding.termsOfUse.isVisible)
            binding.termsOfUse.visibility = View.VISIBLE
        else
            binding.termsOfUse.visibility = View.GONE
    }

    fun onResumeRestoreCellBackgroundColor(dayColorResource: Int, nightColorResource: Int, offColorResource: Int) {
        day = dayColorResource
        night = nightColorResource
        off = offColorResource
        binding.colorDay.setColorFilter(activity.resources.getColor(convertColorIntToResources(dayColorResource)))
        binding.colorNight.setColorFilter(activity.resources.getColor(convertColorIntToResources(nightColorResource)))
        binding.colorOff.setColorFilter(activity.resources.getColor(convertColorIntToResources(offColorResource)))
    }

    private fun convertColorIntToResources(colorResource: Int): Int {
        var integerResource = 0

        if (colorResource == 1)
            integerResource = R.color.color1
        if (colorResource == 2)
            integerResource = R.color.color2
        if (colorResource == 3)
            integerResource = R.color.color3
        if (colorResource == 4)
            integerResource = R.color.color4
        if (colorResource == 5)
            integerResource = R.color.color5
        if (colorResource == 6)
            integerResource = R.color.color6
        if (colorResource == 7)
            integerResource = R.color.color7
        if (colorResource == 8)
            integerResource = R.color.color8
        if (colorResource == 9)
            integerResource = R.color.color9
        if (colorResource == 10)
            integerResource = R.color.color10
        if (colorResource == 11)
            integerResource = R.color.color11
        if (colorResource == 12)
            integerResource = R.color.color12

        return integerResource
    }

    private fun sameBackgroundError() {
        customToast("Two cells cannot use the same background")
    }

    private fun changeDayColorCell() {
        enableColors()
        binding.color1.setOnClickListener {
            if (1 == night || 1 == off)
                sameBackgroundError()
            else {
                day = 1
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck1.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color1))
            }
        }
        binding.color2.setOnClickListener {
            if (2 == night || 2 == off)
                sameBackgroundError()
            else {
                day = 2
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck2.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color2))
            }
//            binding.colorDay.setColorFilter(ContextCompat.getColor(activity, R.color.color2), android.graphics.PorterDuff.Mode.MULTIPLY)
        }
        binding.color3.setOnClickListener {
            if (3 == night || 3 == off)
                sameBackgroundError()
            else {
                day = 3
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck3.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color3))
            }
        }
        binding.color4.setOnClickListener {
            if (4 == night || 4 == off)
                sameBackgroundError()
            else {
                day = 4
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck4.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color4))
            }
        }
        binding.color5.setOnClickListener {
            if (5 == night || 5 == off)
                sameBackgroundError()
            else {
                day = 5
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck5.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color5))
            }
        }
        binding.color6.setOnClickListener {
            if (6 == night || 6 == off)
                sameBackgroundError()
            else {
                day = 6
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck6.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color6))
            }
        }
        binding.color7.setOnClickListener {
            if (7 == night || 7 == off)
                sameBackgroundError()
            else {
                day = 7
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck7.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color7))
            }
        }
        binding.color8.setOnClickListener {
            if (8 == night || 8 == off)
                sameBackgroundError()
            else {
                day = 8
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck8.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color8))
            }
        }
        binding.color9.setOnClickListener {
            if (9 == night || 9 == off)
                sameBackgroundError()
            else {
                day = 9
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck9.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color9))
            }
        }
        binding.color10.setOnClickListener {
            if (10 == night || 10 == off)
                sameBackgroundError()
            else {
                day = 10
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck10.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color10))
            }
        }
        binding.color11.setOnClickListener {
            if (11 == night || 11 == off)
                sameBackgroundError()
            else {
                day = 11
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck11.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color11))
            }
        }
        binding.color12.setOnClickListener {
            if (12 == night || 12 == off)
                sameBackgroundError()
            else {
                day = 12
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck12.visibility = View.VISIBLE
                binding.colorDay.setColorFilter(activity.resources.getColor(R.color.color12))
            }
        }
    }

    private fun changeNightColorCell() {
        enableColors()

        binding.color1.setOnClickListener {
            if (1 == day || 1 == off)
                sameBackgroundError()
            else {
                night = 1
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck1.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color1))
            }
        }
        binding.color2.setOnClickListener {
            if (2 == day || 2 == off)
                sameBackgroundError()
            else {
                night = 2
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck2.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color2))
            }
        }
        binding.color3.setOnClickListener {
            if (3 == day || 3 == off)
                sameBackgroundError()
            else {
                night = 3
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck3.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color3))
            }
        }
        binding.color4.setOnClickListener {
            if (4 == day || 4 == off)
                sameBackgroundError()
            else {
                night = 4
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck4.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color4))
            }
        }
        binding.color5.setOnClickListener {
            if (5 == day || 5 == off)
                sameBackgroundError()
            else {
                night = 5
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck5.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color5))
            }
        }
        binding.color6.setOnClickListener {
            if (6 == day || 6 == off)
                sameBackgroundError()
            else {
                night = 6
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck6.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color6))
            }
        }
        binding.color7.setOnClickListener {
            if (7 == day || 7 == off)
                sameBackgroundError()
            else {
                night = 7
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck7.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color7))
            }
        }
        binding.color8.setOnClickListener {
            if (8 == day || 8 == off)
                sameBackgroundError()
            else {
                night = 8
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck8.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color8))
            }
        }
        binding.color9.setOnClickListener {
            if (9 == day || 9 == off)
                sameBackgroundError()
            else {
                night = 9
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck9.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color9))
            }
        }
        binding.color10.setOnClickListener {
            if (10 == day || 10 == off)
                sameBackgroundError()
            else {
                night = 10
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck10.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color10))
            }
        }
        binding.color11.setOnClickListener {
            if (11 == day || 11 == off)
                sameBackgroundError()
            else {
                night = 11
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck11.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color11))
            }
        }
        binding.color12.setOnClickListener {
            if (12 == day || 12 == off)
                sameBackgroundError()
            else {
                night = 12
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck12.visibility = View.VISIBLE
                binding.colorNight.setColorFilter(activity.resources.getColor(R.color.color12))
            }
        }
    }


    private fun changeOffColorCell() {
        enableColors()

        binding.color1.setOnClickListener {
            if (1 == day || 1 == night)
                sameBackgroundError()
            else {
                off = 1
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck1.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color1))
            }
        }
        binding.color2.setOnClickListener {
            if (2 == day || 2 == night)
                sameBackgroundError()
            else {
                off = 2
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck2.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color2))
            }
        }
        binding.color3.setOnClickListener {
            if (3 == day || 3 == night)
                sameBackgroundError()
            else {
                off = 3
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck3.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color3))
            }
        }
        binding.color4.setOnClickListener {
            if (4 == day || 4 == night)
                sameBackgroundError()
            else {
                off = 4
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck4.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color4))
            }
        }
        binding.color5.setOnClickListener {
            if (5 == day || 5 == night)
                sameBackgroundError()
            else {
                off = 5
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck5.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color5))
            }
        }
        binding.color6.setOnClickListener {
            if (6 == day || 6 == night)
                sameBackgroundError()
            else {
                off = 6
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck6.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color6))
            }
        }
        binding.color7.setOnClickListener {
            if (7 == day || 7 == night)
                sameBackgroundError()
            else {
                off = 7
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck7.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color7))
            }
        }
        binding.color8.setOnClickListener {
            if (8 == day || 8 == night)
                sameBackgroundError()
            else {
                off = 8
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck8.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color8))
            }
        }
        binding.color9.setOnClickListener {
            if (9 == day || 9 == night)
                sameBackgroundError()
            else {
                off = 9
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck9.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color9))
            }
        }
        binding.color10.setOnClickListener {
            if (10 == day || 10 == night)
                sameBackgroundError()
            else {
                off = 10
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck10.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color10))
            }
        }
        binding.color11.setOnClickListener {
            if (11 == day || 11 == night)
                sameBackgroundError()
            else {
                off = 11
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck11.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color11))
            }
        }
        binding.color12.setOnClickListener {
            if (12 == day || 12 == night)
                sameBackgroundError()
            else {
                off = 12
                binding.save.isEnabled = true
                hideAllCheckColor()
                binding.colorCheck12.visibility = View.VISIBLE
                binding.colorOff.setColorFilter(activity.resources.getColor(R.color.color12))
            }
        }
    }


}