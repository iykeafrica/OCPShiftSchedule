package com.i_africa.shiftcalenderobajana.screens.customshift

import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.i_africa.shiftcalenderobajana.R
import com.i_africa.shiftcalenderobajana.screens.viewmvc.BaseViewMvc


class MyPopUpMenu2(private val activity: AppCompatActivity) : BaseViewMvc<MyPopUpMenu2.Listener>() {

    interface Listener {
        fun setAlarm()
        fun createNote()
    }

    var popup2: PopupMenu? = null

    fun popup2(v: View) {
        popup2 = PopupMenu(activity, v)
        val inflater = popup2!!.menuInflater
        inflater.inflate(R.menu.shift_menu2, popup2!!.menu)

        popup2!!.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.set_alarm -> {
                    for (listener in listeners) {
                        listener.setAlarm()
                    }
                }
                R.id.create_note -> {
                    for (listener in listeners) {
                        listener.createNote()
                    }
                }
            }
            true

        }
        popup2!!.show()
    }

    fun dismiss () {
        popup2?.dismiss()
    }

}