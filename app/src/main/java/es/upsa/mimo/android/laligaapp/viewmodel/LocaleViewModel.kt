package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

class LocaleViewModel : ViewModel() {

    private val _currentLocale = MutableLiveData<Locale>()
    val currentLocale: LiveData<Locale> = _currentLocale

    fun setLocale(newLocale: Locale) {
        _currentLocale.postValue(newLocale)
        var observer = currentLocale.hasObservers()
    }

}