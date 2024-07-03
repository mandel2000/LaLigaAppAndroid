package es.upsa.mimo.android.laligaapp

import es.upsa.mimo.android.laligaapp.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.upsa.mimo.android.laligaapp.db.AppDatabase
import es.upsa.mimo.android.laligaapp.ui.theme.LaLigaAppTheme
import es.upsa.mimo.android.laligaapp.viewmodel.SharedViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        val database: AppDatabase by lazy {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "LaLigaAppDb"
            ).build()
        }

        val sharedViewModel: SharedViewModel by viewModels()
        sharedViewModel.database = database

    }

}
