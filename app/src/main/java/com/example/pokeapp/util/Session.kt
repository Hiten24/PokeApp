package com.example.pokeapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Session(context: Context) {

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = pref.edit()

    fun setDarkLightMode(isDark: Boolean) {
        editor.putBoolean("isDark",isDark).commit()
    }

    fun getDarkLightMode(): Boolean {
        return pref.getBoolean("isDark", false)
    }
}