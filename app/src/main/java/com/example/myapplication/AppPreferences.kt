package com.example.myapplication


import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    val IS_CELSIUS = "isCelsius"
    val LAST_CITY_NAME = "lastCityName"
    val PREFS_KEY = "preferences"

    var preferences: SharedPreferences = context.getSharedPreferences(PREFS_KEY, 0)

    var isCelsius: Boolean
        get() = preferences!!.getBoolean(IS_CELSIUS, false)
        set(value) = preferences!!.edit().putBoolean(IS_CELSIUS, value).apply()

    var lastCityName: Int
        get() = preferences!!.getInt(LAST_CITY_NAME, 298740)
        set(value) = preferences!!.edit().putInt(LAST_CITY_NAME, value).apply()
}