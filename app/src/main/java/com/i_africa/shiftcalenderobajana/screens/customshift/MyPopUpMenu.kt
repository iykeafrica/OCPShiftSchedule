package com.i_africa.shiftcalenderobajana.screens.customshift

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc
import com.i_africa.shiftcalenderobajana.utils.Constant
import com.i_africa.shiftcalenderobajana.utils.mysharedpref.MySharedPreferences
import kotlin.properties.Delegates

class MyPopUpMenu(private val activity: AppCompatActivity) : BaseViewMvc<MyPopUpMenu.Listener>() {

    interface Listener {
        fun refresh()
        fun changeShift()
        fun calculateOvertime()
        fun update()
        fun settings()
    }

    private var popup: PopupMenu? = null

    fun popup(v: View, state: Boolean, mySharedPreferences: MySharedPreferences) {
        popup = PopupMenu(activity, v)
        val inflater = popup!!.menuInflater
        inflater.inflate(R.menu.shift_menu, popup!!.menu)

        val dayOfMonthColor = mySharedPreferences.getStoredInt(Constant.DATE_TEXT_COLOR_RESOURCE_KEY)

        popup!!.menu.findItem(R.id.update).isVisible = state != false

        popup!!.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.refresh -> {
                    for (listener in listeners) {
                        listener.refresh()
                    }
                }
                R.id.select_shift -> {
                    for (listener in listeners) {
                        listener.changeShift()
                    }
                }
                R.id.calculate_ot -> {
                    for (listener in listeners) {
                        listener.calculateOvertime()
                    }
                }
                R.id.update -> {
                    for (listener in listeners) {
                        listener.update()
                    }
                }
                R.id.settings -> {
                    for (listener in listeners) {
                        listener.settings()
                    }
                }

            }
            true

        }
        popup!!.show()
    }

    fun dismiss () {
        popup?.dismiss()
    }

}