package com.example.pokeapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.pokeapp.R
import com.example.pokeapp.util.Session

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDarkLightMode()

        val actionBar = supportActionBar
        actionBar?.displayOptions = DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar)
        actionBar?.setDisplayShowCustomEnabled(true)

    }

    fun toggleDarkLightMode(view: View) {
        val session = Session(this)
        val isDark = session.getDarkLightMode()
        if(isDark) {
            lightMode()
            session.setDarkLightMode(false)
        }else {
            darkMode()
            session.setDarkLightMode(true)
        }
        overridePendingTransition(0, 0)
    }

    private fun setDarkLightMode() {
        if(Session(this).getDarkLightMode()) {
            darkMode()
        }else {
            lightMode()
        }
    }

    private fun darkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun lightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}