package com.example.navigationcomponentexample

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.navigationcomponentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Layout'u binding ile ayarla
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavHostFragment üzerinden NavController'ı al
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // ActionBarDrawerToggle ile açma-kapama işlemi
        toggle = ActionBarDrawerToggle(
            this, binding.drawerlayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        // Üst çubukta geri simgesi görünmesi için
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // NavigationView üzerindeki itemlere tıklama olayları
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nawWelcome -> {
                    Toast.makeText(this, "Welcome Seçildi", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.welcomeFragment)
                }
                R.id.nawLogin -> {
                    Toast.makeText(this, "Login Seçildi", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.loginFragment)
                }
                R.id.nawRegister -> {
                    Toast.makeText(this, "Register Seçildi", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.signupFragment)
                }
            }
            binding.drawerlayout.closeDrawer(GravityCompat.START) // Menü kapatılır
            true
        }
    }

    // Menü simgesi ile çekmece açma/kapatma desteği
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
