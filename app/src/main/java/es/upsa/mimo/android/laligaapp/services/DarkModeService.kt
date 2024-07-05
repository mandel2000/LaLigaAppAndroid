package es.upsa.mimo.android.laligaapp.services

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class DarkModeService (context: Context){

    private val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    fun isDarkModeEnabled(): Boolean {

        return  prefs.getBoolean("dark_mode", false)
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
        applyTheme()
    }

    private fun applyTheme(){
        val value = isDarkModeEnabled()
        if (value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}