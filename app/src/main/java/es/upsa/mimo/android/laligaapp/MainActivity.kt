package es.upsa.mimo.android.laligaapp

import es.upsa.mimo.android.laligaapp.R
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
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

        navView.setOnItemSelectedListener { item ->
            Log.d("Navigation", "BottomNavigationView item selected: ${item.title}")
            if (item.itemId == R.id.navigation_teams && navController.currentDestination?.id != R.id.navigation_teams) {
                navController.navigate(R.id.navigation_teams)
            } else {
                NavigationUI.onNavDestinationSelected(item, navController)
            }
            true
        }

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
