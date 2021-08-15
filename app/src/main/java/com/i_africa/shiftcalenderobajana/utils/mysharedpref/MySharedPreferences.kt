package com.i_africa.shiftcalenderobajana.utils.mysharedpref

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import com.i_africa.shiftcalenderobajana.utils.Constant

class MySharedPreferences(application: Application) {

    private val preferences: SharedPreferences = application.applicationContext.getSharedPreferences(
        Constant.MY_PREF, 0)
    @SuppressLint("CommitPrefEdits")
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

    fun clearSelectedShiftPreferences() {
        editor.remove(Constant.FIRST_TIME_LOADING).apply()
        editor.remove(Constant.SHIFT_PREFERENCE_KEY).apply()
    }

}