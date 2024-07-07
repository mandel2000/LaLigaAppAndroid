package es.upsa.mimo.android.laligaapp.services

import android.app.Activity
import android.content.Context
import java.util.Locale

class LocaleService (private val context: Context){

    fun setLanguage(selectedLanguage : String): Locale {
        val locale = when (selectedLanguage) {
            "English" -> Locale.ENGLISH
            "Inglés" -> Locale.ENGLISH
            "Spanish" -> Locale.forLanguageTag("es")
            "Español" -> Locale.forLanguageTag("es")
            else -> Locale.getDefault()
        }

        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        context.resources.configuration.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        return locale
    }

    fun getLanguage(): String {
        val optionSelected =
            when(Locale.getDefault()){
                Locale.ENGLISH -> "English"
                Locale.forLanguageTag("es") -> "Spanish"
                else -> "English"
        }
        return optionSelected

    }

}