package com.i_africa.shiftcalenderobajana.screens.viewmvc

import java.util.*

open class BaseViewMvc<LISTENER>() {

    val listeners = HashSet<LISTENER>()

    fun registerListener(listener: LISTENER) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER) {
        listeners.remove(listener)
    }
}