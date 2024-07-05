package es.upsa.mimo.android.laligaapp.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.google.android.material.materialswitch.MaterialSwitch
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.services.DarkModeService
import es.upsa.mimo.android.laligaapp.services.LocaleService

class SettingsActivity : AppCompatActivity() {

    private lateinit var darkModeService : DarkModeService
    private lateinit var localeService: LocaleService

    private var isLocaleUpdated = false
    private var isInitialSelection = true

    private var previousLanguage : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        darkModeService = DarkModeService(this)

        val switch: SwitchCompat = findViewById(R.id.notificationSwitch)
        switch.isChecked = darkModeService.isDarkModeEnabled()
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                darkModeService.setDarkModeEnabled(true)
            } else {
                darkModeService.setDarkModeEnabled(false)

            }
        }

        val languageSpinner: Spinner = findViewById(R.id.languageSpinner)

        if (isLocaleUpdated) {
            isLocaleUpdated = false
            //previousLanguage = languageSpinner.getItemAtPosition(localeService.getLanguage())?.toString()

        }

        localeService = LocaleService(context = this)
        languageSpinner.setSelection(localeService.getLanguage())

        languageSpinner.onItemSelectedListener = object : AdapterView
        .OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isInitialSelection) {
                    isInitialSelection = false // Reset the flag
                    return // Ignore initial selection
                }
                val language = parent?.getItemAtPosition(position) as String

                if(language != previousLanguage) {
                    changeLocale(language)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    fun changeLocale(language : String){
        localeService.setLanguage(language)
        previousLanguage = language
    }

    fun closeSettings(view: View) {
        finish()
    }
}