package u.akita.tennis_note

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import u.akita.tennis_note.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // NavControllerの取得
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        Log.d("MainActivity", "NavController: $navController")
        Log.d("MainActivity", "NavController Current Destination: ${navController.currentDestination?.id}")
        //ボタンセット
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_checklist, R.id.navigation_note_list, R.id.navigation_schedule))

        // ActionBarとNavControllerのセットアップ
        setupActionBarWithNavController(navController, appBarConfiguration)

        // BottomNavigationViewとNavControllerのセットアップ
        navView.setupWithNavController(navController)
    }
}