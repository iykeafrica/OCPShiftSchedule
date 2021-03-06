package com.i_africa.shiftcalenderobajana.screens.common

import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc

class MyPopUpMenu(private val activity: AppCompatActivity) : BaseViewMvc<MyPopUpMenu.Listener>() {

    interface Listener {
        fun refresh()
        fun changeShift()
        fun calculateOvertime()
        fun about()
    }

    fun popup(v: View) {
        val popup = PopupMenu(activity, v)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.shift_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
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
                R.id.about -> {
                    for (listener in listeners) {
                        listener.about()
                    }
                }
            }
            true
        }
        popup.show()
    }
}