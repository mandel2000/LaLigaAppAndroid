package es.upsa.mimo.android.laligaapp.activities

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import es.upsa.mimo.android.laligaapp.viewmodel.LocaleViewModel

class SettingsActivity : AppCompatActivity() {

    val localeViewModel: LocaleViewModel by viewModels()

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

        localeService = LocaleService(applicationContext)

        val languageRadioGroup : RadioGroup = findViewById(R.id.languageRadioGroup)

        when (localeService.getLanguage()) {
            "English" -> {
                languageRadioGroup.check(R.id.englishRadioButton)
            }

            "Spanish" -> {
                languageRadioGroup.check(R.id.spanishRadioButton)
            }
        }

        languageRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.englishRadioButton -> {
                    // Handle English selection
                    changeLocale("English") // Or however you handle locale changes
                }
                R.id.spanishRadioButton -> {
                    // Handle Spanish selection
                    changeLocale("Spanish")
                }
                // Add more cases for other languages
            }
        }

    }

    private fun changeLocale(language : String){
        val newLocale = localeService.setLanguage(language)
        localeViewModel.setLocale(newLocale)
        (this as Activity).recreate()
    }

    fun closeSettings(view: View) {
        finish()
    }
}