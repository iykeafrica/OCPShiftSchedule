package com.i_africa.shiftcalenderobajana.mysharedpref

import android.app.Application
import android.content.SharedPreferences
import com.i_africa.shiftcalenderobajana.screens.common.constant.Constant

class MySharedPreferences(application: Application) {

    private val preferences: SharedPreferences = application.applicationContext.getSharedPreferences(Constant.MY_PREF, 0)
    private val editor = preferences.edit()!!

    fun storeStringValue(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun storeBooleanValue(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }


    fun getStoredString(key: String): String {
        return preferences.getString(key,"")!!
    }

    fun getStoredBoolean(key: String): Boolean {
        return preferences.getBoolean(key, true)
    }

    fun clearPreferences() {
        editor.clear().apply()
    }

}