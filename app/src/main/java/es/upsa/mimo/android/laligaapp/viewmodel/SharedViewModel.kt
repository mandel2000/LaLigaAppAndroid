package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import es.upsa.mimo.android.laligaapp.db.AppDatabase

class SharedViewModel : ViewModel(){
    lateinit var database: AppDatabase
}